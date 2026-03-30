// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.external.component

import com.github.blemale.scaffeine.{AsyncLoadingCache, Scaffeine}
import com.intellij.openapi.project.Project
import intellij.haskell.external.component.HaskellComponentsManager.ComponentTarget
import intellij.haskell.util.{HaskellProjectUtil, ScalaFutureUtil}

import scala.concurrent.duration._
import scala.concurrent.{Await, Future, blocking}

private[component] object StackComponentGlobalInfoComponent {

  import scala.concurrent.ExecutionContext.Implicits.global

  private case class Key(stackComponentInfo: ComponentTarget)

  private type Result = Option[StackComponentGlobalInfo]

  private final val Cache: AsyncLoadingCache[Key, Result] = Scaffeine().buildAsync((k: Key) => createStackInfo(k))

  def findStackComponentGlobalInfo(stackComponentInfo: ComponentTarget): Option[StackComponentGlobalInfo] = {
    val key = Key(stackComponentInfo)
    ScalaFutureUtil.waitForValue(stackComponentInfo.module.getProject, Cache.get(key), "Getting global info").flatten match {
      case result@Some(_) => result
      case _ =>
        Cache.synchronous().invalidate(key)
        None
    }
  }

  private def createStackInfo(key: Key): Result = {
    val project = key.stackComponentInfo.module.getProject
    val stackComponentInfo = key.stackComponentInfo
    findAvailableLibraryModuleNames(project, stackComponentInfo)
  }

  private def findAvailableLibraryModuleNames(project: Project, componentInfo: ComponentTarget): Result = {
    val projectPackageNames = HaskellProjectUtil.findProjectPackageNames(project)
    val buildDependsLibraryPackages = componentInfo.buildDepends.filterNot(projectPackageNames.contains) ++ Seq("ghc-prim")

    val libraryModuleNamesFutures = buildDependsLibraryPackages.grouped(5).map { packageNames =>
      Future {
        blocking {
          packageNames.flatMap { packageName =>
            if (project.isDisposed) {
              None
            } else {
              LibraryPackageInfoComponent.findLibraryPackageInfo(project, packageName)
            }
          }
        }
      }
    }

    val libraryModuleNames = Await.result(Future.sequence(libraryModuleNamesFutures), 60.second).flatten.toSeq

    Some(StackComponentGlobalInfo(componentInfo, libraryModuleNames))
  }

  def invalidate(project: Project): Unit = {
    val keys = Cache.synchronous().asMap().keys.filter(_.stackComponentInfo.module.getProject == project)
    keys.foreach(Cache.synchronous().invalidate)
  }
}

case class StackComponentGlobalInfo(stackComponentInfo: ComponentTarget, packageInfos: Seq[LibraryPackageInfo])
