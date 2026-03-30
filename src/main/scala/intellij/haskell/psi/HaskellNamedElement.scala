// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.psi

import com.intellij.navigation.NavigationItem
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.search.SearchScope

trait HaskellNamedElement extends HaskellCompositeElement with PsiNameIdentifierOwner with NavigationItem {
  def getUseScope: SearchScope
}
