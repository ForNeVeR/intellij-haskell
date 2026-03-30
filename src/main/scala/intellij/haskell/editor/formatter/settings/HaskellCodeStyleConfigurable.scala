// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.editor.formatter.settings

import com.intellij.application.options.{CodeStyleAbstractConfigurable, CodeStyleAbstractPanel, TabbedLanguageCodeStylePanel}
import com.intellij.psi.codeStyle.CodeStyleSettings
import intellij.haskell.HaskellLanguage
import org.jetbrains.annotations.NotNull

class HaskellCodeStyleConfigurable(@NotNull settings: CodeStyleSettings, originalSettings: CodeStyleSettings) extends CodeStyleAbstractConfigurable(settings, originalSettings, "Haskell") {

  protected def createPanel(settings: CodeStyleSettings): CodeStyleAbstractPanel = {
    new HaskellCodeStyleMainPanel(settings, originalSettings)
  }

  override def getHelpTopic: String = {
    null
  }

  class HaskellCodeStyleMainPanel(currentSettings: CodeStyleSettings, settings: CodeStyleSettings) extends TabbedLanguageCodeStylePanel(HaskellLanguage.Instance, currentSettings, settings) {
    protected override def initTabs(settings: CodeStyleSettings): Unit = {
      addIndentOptionsTab(settings)
    }
  }
}