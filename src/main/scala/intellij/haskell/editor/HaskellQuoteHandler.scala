// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.editor

import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler
import intellij.haskell.psi.HaskellTypes._

class HaskellQuoteHandler extends SimpleTokenSetQuoteHandler(HS_STRING_LITERAL, HS_BACKQUOTE, HS_QUOTE, HS_DOUBLE_QUOTES)
