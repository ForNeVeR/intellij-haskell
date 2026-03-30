// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.action

import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent}
import intellij.haskell.external.component.NameInfoComponentResult._
import intellij.haskell.external.component._
import intellij.haskell.util.HaskellEditorUtil

class ShowNameInfoAction extends AnAction {

  override def update(actionEvent: AnActionEvent): Unit = {
    HaskellEditorUtil.enableAction(onlyForSourceFile = false, actionEvent)
  }

  def actionPerformed(actionEvent: AnActionEvent): Unit = {
    if (!StackProjectManager.isInitializing(actionEvent.getProject)) {
      ActionUtil.findActionContext(actionEvent).foreach(actionContext => {
        val editor = actionContext.editor
        val psiFile = actionContext.psiFile
        val offset = editor.getCaretModel.getOffset
        Option(psiFile.findElementAt(offset)).foreach(psiElement => {
          val result = HaskellComponentsManager.findNameInfo(psiElement)
          result match {
            case Right(nameInfos) => HaskellEditorUtil.showList(nameInfos.toSeq.map(createInfoText), editor)
            case Left(info) => HaskellEditorUtil.showList(Seq(info.message), editor)
          }
        })
      })
    } else {
      HaskellEditorUtil.showHaskellSupportIsNotAvailableWhileInitializing(actionEvent.getProject)
    }
  }

  private def createInfoText(nameInfo: NameInfo): String = {
    nameInfo match {
      case pi: ProjectNameInfo => s"${pi.declaration}   -- ${pi.filePath}"
      case li: LibraryNameInfo => s"${li.shortenedDeclaration}   -- ${li.moduleName}    ${li.packageName.getOrElse("")}"
      case ii: InfixInfo => ii.declaration
    }
  }

}
