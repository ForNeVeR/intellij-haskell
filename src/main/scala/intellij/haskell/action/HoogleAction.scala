// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.action

import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent}
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.text.StringUtil
import intellij.haskell.external.component.{HoogleComponent, StackProjectManager}
import intellij.haskell.util.HaskellEditorUtil

class HoogleAction extends AnAction {

  override def update(actionEvent: AnActionEvent): Unit = {
    HaskellEditorUtil.enableExternalAction(actionEvent, (project: Project) => StackProjectManager.isHoogleAvailable(project).isDefined && HoogleComponent.doesHoogleDatabaseExist(project))
  }

  def actionPerformed(actionEvent: AnActionEvent): Unit = {
    ActionUtil.findActionContext(actionEvent).foreach(actionContext => {
      val editor = actionContext.editor
      val psiFile = actionContext.psiFile
      val offset = editor.getCaretModel.getOffset

      val selectionModel = actionContext.selectionModel
      selectionModel.map(_.getSelectedText).orElse(Option(psiFile.findElementAt(offset)).map(_.getText)).foreach(text => {
        HoogleComponent.runHoogle(psiFile.getProject, text) match {
          case Some(results) => HaskellEditorUtil.showList(results, editor)
          case _ => HaskellEditorUtil.showHint(editor, s"No Hoogle result for ${StringUtil.escapeXmlEntities(text)}")
        }
      })
    })
  }
}
