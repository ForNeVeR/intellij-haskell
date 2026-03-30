// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.editor.formatter.settings

import com.intellij.psi.codeStyle.{CodeStyleSettings, CustomCodeStyleSettings}

class HaskellCodeStyleSettings(settings: CodeStyleSettings) extends CustomCodeStyleSettings("HaskellCodeStyleSettings", settings) {
  settings.AUTODETECT_INDENTS = false
}