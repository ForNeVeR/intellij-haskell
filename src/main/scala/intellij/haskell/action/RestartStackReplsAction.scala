// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.action

import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent}
import com.intellij.openapi.project.Project
import intellij.haskell.external.component.{ProjectLibraryBuilder, StackProjectManager}
import intellij.haskell.util.HaskellEditorUtil

class RestartStackReplsAction extends AnAction {

  override def update(actionEvent: AnActionEvent): Unit = {
    HaskellEditorUtil.enableExternalAction(actionEvent, (p: Project) => !StackProjectManager.isInitializing(p) &&
      !ProjectLibraryBuilder.isBuilding(p) &&
      !StackProjectManager.isHaddockBuilding(p) &&
      !StackProjectManager.isPreloadingAllLibraryIdentifiers(p))
  }

  override def actionPerformed(actionEvent: AnActionEvent): Unit = {
    ProjectLibraryBuilder.resetBuildStatus(actionEvent.getProject)
    StackProjectManager.restart(actionEvent.getProject)
  }
}