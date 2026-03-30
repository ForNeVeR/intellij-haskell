// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.psi.impl

import com.intellij.psi.tree.IElementType
import intellij.haskell.psi.stubs.types._

object HaskellElementTypeFactory {

  def factory(name: String): IElementType = {
    name match {
      case "HS_CONID" => new HaskellConidStubElementType(name)
      case "HS_VARID" => new HaskellVaridStubElementType(name)
      case "HS_VARSYM" => new HaskellVarsymStubElementType(name)
      case "HS_CONSYM" => new HaskellConsymStubElementType(name)
      case "HS_MODID" => new HaskellModidStubElementType(name)
      case _ => throw new IllegalStateException(s"Unknown element name: $name")
    }
  }
}
