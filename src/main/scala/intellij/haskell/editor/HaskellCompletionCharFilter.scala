// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.editor

import com.intellij.codeInsight.lookup.CharFilter.Result
import com.intellij.codeInsight.lookup.{CharFilter, Lookup}
import intellij.haskell.HaskellFile

class HaskellCompletionCharFilter extends CharFilter {
  def acceptChar(c: Char, prefixLength: Int, lookup: Lookup): CharFilter.Result = {
    if (lookup == null || lookup.getPsiElement == null) return null
    val file = lookup.getPsiFile
    if (!file.isInstanceOf[HaskellFile]) return null

    c match {
      case ' ' |',' | ';' | ':' | '(' | ')' | '[' | ']' | '{' | '}' => Result.SELECT_ITEM_AND_FINISH_LOOKUP
      case _ => Result.ADD_TO_PREFIX
    }
  }
}