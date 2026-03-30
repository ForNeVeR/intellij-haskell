// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.editor.formatter.settings

import com.intellij.lang.Language
import com.intellij.openapi.options.Configurable
import com.intellij.psi.codeStyle.{CodeStyleSettings, CodeStyleSettingsProvider, CustomCodeStyleSettings}
import intellij.haskell.HaskellLanguage
import org.jetbrains.annotations.NotNull

class HaskellCodeStyleSettingsProvider extends CodeStyleSettingsProvider {
  override def getConfigurableDisplayName: String = {
    "Haskell"
  }

  @NotNull
  override def createSettingsPage(settings: CodeStyleSettings, originalSettings: CodeStyleSettings): Configurable = {
    new HaskellCodeStyleConfigurable(settings, originalSettings)
  }

  @NotNull
  override def createCustomSettings(settings: CodeStyleSettings): CustomCodeStyleSettings = {
    new HaskellCodeStyleSettings(settings)
  }

  override def getLanguage: Language = {
    HaskellLanguage.Instance
  }
}