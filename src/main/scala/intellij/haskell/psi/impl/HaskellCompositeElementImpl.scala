// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import intellij.haskell.psi._

class HaskellCompositeElementImpl(node: ASTNode) extends ASTWrapperPsiElement(node) with HaskellCompositeElement {

  override def toString: String = {
    getNode.getElementType.toString
  }
}

abstract class HaskellExpressionElementImpl private[impl](node: ASTNode) extends HaskellCompositeElementImpl(node) with HaskellExpressionElement

abstract class HaskellNamedElementImpl private[impl](node: ASTNode) extends HaskellCompositeElementImpl(node) with HaskellNamedElement

abstract class HaskellQualifierElementImpl private[impl](node: ASTNode) extends HaskellNamedElementImpl(node) with HaskellQualifierElement
