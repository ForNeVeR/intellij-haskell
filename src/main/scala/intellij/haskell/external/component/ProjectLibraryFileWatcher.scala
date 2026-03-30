// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.external.component

import java.util

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.newvfs.BulkFileListener
import com.intellij.openapi.vfs.newvfs.events.{VFileContentChangeEvent, VFileEvent}
import intellij.haskell.external.repl.StackRepl.LibType
import intellij.haskell.util.{HaskellFileUtil, HaskellProjectUtil}

import scala.jdk.CollectionConverters._

class ProjectLibraryFileWatcher(project: Project) extends BulkFileListener {

  override def before(events: util.List[_ <: VFileEvent]): Unit = {}

  override def after(events: util.List[_ <: VFileEvent]): Unit = {
    if (!project.isDisposed) {
      val componentTargets = (for {
        virtualFile <- events.asScala.filter(e => e.isInstanceOf[VFileContentChangeEvent] && HaskellFileUtil.isHaskellFile(e.getFile) && HaskellProjectUtil.isSourceFile(project, e.getFile)).map(_.getFile)
        componentTarget <- HaskellComponentsManager.findComponentTarget(project, HaskellFileUtil.getAbsolutePath(virtualFile))
        if componentTarget.stanzaType == LibType
      } yield componentTarget).toSet

      if (componentTargets.nonEmpty) {
        ProjectLibraryBuilder.addBuild(project, componentTargets)
      }
    }
  }
}
