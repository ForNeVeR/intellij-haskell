// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.psi.stubs.types

import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.{StubElement, StubInputStream}
import intellij.haskell.psi.HaskellVarid
import intellij.haskell.psi.impl.HaskellVaridImpl
import intellij.haskell.psi.stubs.HaskellVaridStub

class HaskellVaridStubElementType(debugName: String) extends HaskellNamedStubElementType[HaskellVaridStub, HaskellVarid](debugName) {
  def createPsi(stub: HaskellVaridStub): HaskellVarid = {
    new HaskellVaridImpl(stub, this)
  }

  def createStub(psi: HaskellVarid, parentStub: StubElement[_ <: PsiElement]): HaskellVaridStub = {
    new HaskellVaridStub(parentStub, this, psi.getName)
  }

  def deserialize(dataStream: StubInputStream, parentStub: StubElement[_ <: PsiElement]): HaskellVaridStub = {
    new HaskellVaridStub(parentStub, this, dataStream.readName)
  }
}