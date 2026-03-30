// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.cabal

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import javax.swing._
import org.jetbrains.annotations.NotNull

class CabalFile(viewProvider: FileViewProvider) extends PsiFileBase(viewProvider, CabalLanguage.Instance) {

  @NotNull
  def getFileType: FileType = {
    CabalFileType.INSTANCE
  }

  override def toString: String = {
    "Cabal file"
  }

  override def getIcon(flags: Int): Icon = {
    super.getIcon(flags)
  }
}
