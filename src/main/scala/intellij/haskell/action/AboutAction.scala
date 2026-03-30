// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.action

import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent}
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.util.SystemInfo
import intellij.haskell.external.component.{HLintComponent, HaskellComponentsManager, HoogleComponent, StackProjectManager}
import intellij.haskell.external.execution.StackCommandLine
import intellij.haskell.util.HaskellEditorUtil

import scala.collection.mutable.ArrayBuffer

class AboutAction extends AnAction {

  override def update(actionEvent: AnActionEvent): Unit = {
    HaskellEditorUtil.enableExternalAction(actionEvent, !StackProjectManager.isInitializing(_))
  }

  private def boldToolName(name: String): String = {
    if (SystemInfo.isMac) {
      s"<b>$name</b>"
    } else {
      name
    }
  }

  def actionPerformed(actionEvent: AnActionEvent): Unit = {
    val messages = new ArrayBuffer[String]
    val project = actionEvent.getProject
    messages.+=(s"${boldToolName("Stack")} version: " + StackCommandLine.run(project, Seq("--numeric-version"), enableExtraArguments = false).map(_.getStdout).getOrElse("-"))
    messages.+=(s"${boldToolName("GHC")}: " + HaskellComponentsManager.getGhcVersion(project).map(_.prettyString).getOrElse("-") + "\n")
    messages.+=(s"${boldToolName("HLint")}: " + HLintComponent.versionInfo(project))
    messages.+=(s"${boldToolName("Hoogle")}: " + HoogleComponent.versionInfo(project))
    messages.+=(s"${boldToolName("Ormolu")}: " + OrmoluReformatAction.versionInfo(project))
    messages.+=(s"${boldToolName("Stylish-haskell")}: " + StylishHaskellReformatAction.versionInfo(project))
    Messages.showInfoMessage(project, messages.mkString("\n"), "About Haskell Project")
  }
}
