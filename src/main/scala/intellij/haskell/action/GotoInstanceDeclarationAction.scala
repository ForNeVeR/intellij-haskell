// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.action

import com.intellij.codeInsight.navigation.NavigationUtil
import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent}
import com.intellij.openapi.util.text.StringUtil
import intellij.haskell.external.component.HaskellComponentsManager
import intellij.haskell.navigation.HaskellReference
import intellij.haskell.psi.HaskellPsiUtil
import intellij.haskell.util.HaskellEditorUtil

class GotoInstanceDeclarationAction extends AnAction {

  override def update(actionEvent: AnActionEvent): Unit = {
    HaskellEditorUtil.enableAction(onlyForSourceFile = false, actionEvent)
  }

  def actionPerformed(actionEvent: AnActionEvent): Unit = {
    ActionUtil.findActionContext(actionEvent).foreach(actionContext => {
      val editor = actionContext.editor
      val psiFile = actionContext.psiFile
      val project = actionContext.project
      val offset = editor.getCaretModel.getOffset
      Option(psiFile.findElementAt(offset)).flatMap(HaskellPsiUtil.findNamedElement).foreach(namedElement => {
        val instanceElements = HaskellComponentsManager.findNameInfo(namedElement) match {
          case Right(nameInfos) => HaskellReference.resolveInstanceReferences(project, namedElement, nameInfos)
          case Left(info) =>
            HaskellEditorUtil.showHint(editor, info.message)
            Seq()
        }

        if (instanceElements.nonEmpty) {
          val popup = NavigationUtil.getPsiElementPopup(instanceElements.toArray, "Goto instance declaration")
          popup.showInBestPositionFor(editor)
        } else {
          HaskellEditorUtil.showHint(editor, s"No instance declarations found for ${StringUtil.escapeXmlEntities(namedElement.getText)}")
        }
      })
    })
  }

}
