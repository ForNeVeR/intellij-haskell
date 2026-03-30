// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.psi

import com.intellij.psi.tree.IElementType
import intellij.haskell.HaskellLanguage
import org.jetbrains.annotations.{NonNls, NotNull}

class HaskellTokenType(@NotNull @NonNls debugName: String) extends IElementType(debugName, HaskellLanguage.Instance) {

  override def toString: String = {
    "HaskellTokenType." + super.toString
  }

  def getName: String = {
    super.toString.toLowerCase
  }
}