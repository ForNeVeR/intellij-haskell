// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.refactor

import com.intellij.lang.refactoring.RefactoringSupportProvider
import com.intellij.psi._
import intellij.haskell.util.HaskellProjectUtil

class HaskellRefactoringSupportProvider extends RefactoringSupportProvider {

  override def isMemberInplaceRenameAvailable(psiElement: PsiElement, context: PsiElement): Boolean = {
    !psiElement.isInstanceOf[PsiFile] && isDefinedInProject(psiElement)
  }

  private def isDefinedInProject(psiElement: PsiElement) = {
    Option(psiElement.getReference).flatMap(x => Option(x.resolve)) match {
      case Some(e) => Option(e.getContainingFile).map(_.getOriginalFile).exists(pf => HaskellProjectUtil.isSourceFile(pf))
      case _ => false
    }
  }
}