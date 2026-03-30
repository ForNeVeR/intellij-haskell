// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.psi.stubs.types

import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.{StubElement, StubInputStream}
import intellij.haskell.psi.HaskellConsym
import intellij.haskell.psi.impl.HaskellConsymImpl
import intellij.haskell.psi.stubs.HaskellConsymStub

class HaskellConsymStubElementType(debugName: String) extends HaskellNamedStubElementType[HaskellConsymStub, HaskellConsym](debugName) {
  def createPsi(stub: HaskellConsymStub): HaskellConsym = {
    new HaskellConsymImpl(stub, this)
  }

  def createStub(psi: HaskellConsym, parentStub: StubElement[_ <: PsiElement]): HaskellConsymStub = {
    new HaskellConsymStub(parentStub, this, psi.getName)
  }

  def deserialize(dataStream: StubInputStream, parentStub: StubElement[_ <: PsiElement]): HaskellConsymStub = {
    new HaskellConsymStub(parentStub, this, dataStream.readName)
  }
}