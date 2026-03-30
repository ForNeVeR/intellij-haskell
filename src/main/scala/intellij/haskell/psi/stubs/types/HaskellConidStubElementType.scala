// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.psi.stubs.types

import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.{StubElement, StubInputStream}
import intellij.haskell.psi.HaskellConid
import intellij.haskell.psi.impl.HaskellConidImpl
import intellij.haskell.psi.stubs.HaskellConidStub

class HaskellConidStubElementType(debugName: String) extends HaskellNamedStubElementType[HaskellConidStub, HaskellConid](debugName) {
  def createPsi(stub: HaskellConidStub): HaskellConid = {
    new HaskellConidImpl(stub, this)
  }

  def createStub(psi: HaskellConid, parentStub: StubElement[_ <: PsiElement]): HaskellConidStub = {
    new HaskellConidStub(parentStub, this, psi.getName)
  }

  def deserialize(dataStream: StubInputStream, parentStub: StubElement[_ <: PsiElement]): HaskellConidStub = {
    new HaskellConidStub(parentStub, this, dataStream.readName)
  }
}