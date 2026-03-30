// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.action

import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent}
import intellij.haskell.annotator.HaskellAnnotator
import intellij.haskell.util.HaskellEditorUtil

class ShowProblemMessageAction extends AnAction {

  override def update(actionEvent: AnActionEvent): Unit = {
    HaskellEditorUtil.enableAction(onlyForSourceFile = true, actionEvent)
  }

  override def actionPerformed(actionEvent: AnActionEvent): Unit = {
    ActionUtil.findActionContext(actionEvent).foreach(actionContext => {
      val project = actionContext.project
      val editor = actionContext.editor
      val offset = editor.getCaretModel.getOffset
      HaskellAnnotator.findHighlightInfo(project, offset, editor).foreach(info => {
        val message = info.getToolTip
        HaskellEditorUtil.showHint(editor, message)
      })
    })
  }
}
