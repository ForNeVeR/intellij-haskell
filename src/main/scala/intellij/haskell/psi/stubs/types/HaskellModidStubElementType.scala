// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.psi.stubs.types

import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.{StubElement, StubInputStream}
import intellij.haskell.psi.HaskellModid
import intellij.haskell.psi.impl.HaskellModidImpl
import intellij.haskell.psi.stubs.HaskellModidStub

class HaskellModidStubElementType(debugName: String) extends HaskellNamedStubElementType[HaskellModidStub, HaskellModid](debugName) {
  def createPsi(stub: HaskellModidStub): HaskellModid = {
    new HaskellModidImpl(stub, this)
  }

  def createStub(psi: HaskellModid, parentStub: StubElement[_ <: PsiElement]): HaskellModidStub = {
    new HaskellModidStub(parentStub, this, psi.getName)
  }

  def deserialize(dataStream: StubInputStream, parentStub: StubElement[_ <: PsiElement]): HaskellModidStub = {
    new HaskellModidStub(parentStub, this, dataStream.readName)
  }
}