// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.action

import com.intellij.codeInsight.actions.ReformatCodeAction
import com.intellij.openapi.actionSystem.{AnActionEvent, Presentation}
import com.intellij.openapi.project.Project
import intellij.haskell.external.component.StackProjectManager
import intellij.haskell.util.{HaskellEditorUtil, HaskellFileUtil}

class HaskellReformatAction extends ReformatCodeAction {

  private val presentation: Presentation = getTemplatePresentation
  presentation.setText("Reformat Code")

  override def update(actionEvent: AnActionEvent): Unit = {
    ActionUtil.findActionContext(actionEvent).foreach(actionContext => {
      val psiFile = actionContext.psiFile
      if (HaskellFileUtil.isHaskellFile(psiFile)) {
        HaskellEditorUtil.enableExternalAction(actionEvent, (project: Project) => StackProjectManager.isOrmoluAvailable(project).isDefined)
      } else {
        super.update(actionEvent)
      }
    })
  }

  override def actionPerformed(actionEvent: AnActionEvent): Unit = {
    ActionUtil.findActionContext(actionEvent).foreach(actionContext => {
      val psiFile = actionContext.psiFile
      if (HaskellFileUtil.isHaskellFile(psiFile)) {
        OrmoluReformatAction.reformat(psiFile)
      } else {
        super.actionPerformed(actionEvent)
      }
    })
  }
}

