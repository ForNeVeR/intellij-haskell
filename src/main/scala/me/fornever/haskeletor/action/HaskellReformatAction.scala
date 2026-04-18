/*
 * SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
 * SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
 * SPDX-FileCopyrightText: 2026 haskeletor contributors <https://github.com/ForNeVeR/haskeletor>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package me.fornever.haskeletor.action

import com.intellij.codeInsight.actions.ReformatCodeAction
import com.intellij.openapi.actionSystem.{AnActionEvent, Presentation}
import com.intellij.openapi.project.Project
import me.fornever.haskeletor.external.component.StackProjectManager
import me.fornever.haskeletor.util.{HaskellEditorUtil, HaskellFileUtil}

class HaskellReformatAction extends ReformatCodeAction {

  private val presentation: Presentation = getTemplatePresentation
  presentation.setText("Reformat Code")

  override def update(actionEvent: AnActionEvent): Unit = {
    ActionUtil.findActionContext(actionEvent) match {
      case Some(actionContext) =>
        val psiFile = actionContext.psiFile
        if (HaskellFileUtil.isHaskellFile(psiFile)) {
          HaskellEditorUtil.enableExternalAction(actionEvent, (project: Project) => StackProjectManager.isOrmoluAvailable(project).isDefined)
        } else {
          super.update(actionEvent)
        }
      case None =>
    }
  }

  override def actionPerformed(actionEvent: AnActionEvent): Unit = {
    ActionUtil.findActionContext(actionEvent) match {
      case Some(actionContext) =>
        val psiFile = actionContext.psiFile
        if (HaskellFileUtil.isHaskellFile(psiFile)) {
          OrmoluReformatAction.reformat(psiFile)
        } else {
          super.actionPerformed(actionEvent)
        }
      case None =>
    }
  }
}

