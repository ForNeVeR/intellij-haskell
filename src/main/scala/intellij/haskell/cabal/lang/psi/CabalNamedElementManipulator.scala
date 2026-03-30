// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.cabal.lang.psi

import com.intellij.openapi.util.TextRange
import com.intellij.psi.AbstractElementManipulator

class CabalNamedElementManipulator extends AbstractElementManipulator[CabalNamedElement] {
  def handleContentChange(psi: CabalNamedElement, range: TextRange, newContent: String): CabalNamedElement = {
    psi.setName(newContent)
    psi
  }
}