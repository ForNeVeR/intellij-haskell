// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.external.repl

import com.intellij.openapi.project.Project
import intellij.haskell.external.repl.StackRepl.StackReplOutput
import intellij.haskell.util.ScalaFutureUtil

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._

case class GlobalStackRepl(project: Project, replTimeout: Int) extends StackRepl(project, None, Seq("--no-package-hiding", "--no-load"), replTimeout) {

  @volatile
  private[this] var loadedModuleName: Option[String] = None

  def clearLoadedModules(): Unit = {
    loadedModuleName = None
  }

  def getModuleIdentifiers(moduleName: String): Option[StackReplOutput] = {
    ScalaFutureUtil.waitForValue(project, Future {
      blocking {
        synchronized {
          // To get qualified identifiers no modules should be loaded
          execute(s""":load""") // First unload current module
          val result = execute(s":browse! $moduleName")
          result
        }
      }
    }, ":browse in GlobalStackRepl").flatten
  }

  def findInfo(moduleName: String, name: String): Option[StackReplOutput] = {
    ScalaFutureUtil.waitForValue(project, Future {
      blocking {
        synchronized {
          loadModule(moduleName)

          if (loadedModuleName.contains(moduleName)) {
            execute(s":info $name")
          } else {
            // No info means NEVER info because it's library
            Some(StackReplOutput())
          }
        }
      }
    }, ":info in GlobalStackRepl").flatten
  }

  override def restart(forceExit: Boolean): Unit = synchronized {
    if (available && !starting) {
      exit(forceExit)
      loadedModuleName = None
      Thread.sleep(1000)
      start()
    }
  }

  private def loadModule(moduleName: String): Unit = {
    if (!loadedModuleName.contains(moduleName)) {
      val output = execute(s":module $moduleName")
      if (output.exists(_.stderrLines.isEmpty)) {
        loadedModuleName = Some(moduleName)
      } else {
        loadedModuleName = None
      }
    }
  }
}
