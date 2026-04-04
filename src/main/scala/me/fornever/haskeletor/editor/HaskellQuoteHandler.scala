/*
 * SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
 * SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
 * SPDX-FileCopyrightText: 2026 haskeletor contributors <https://github.com/ForNeVeR/haskeletor>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package me.fornever.haskeletor.editor

import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler
import me.fornever.haskeletor.psi.HaskellTypes._

class HaskellQuoteHandler extends SimpleTokenSetQuoteHandler(HS_STRING_LITERAL, HS_BACKQUOTE, HS_QUOTE, HS_DOUBLE_QUOTES)
