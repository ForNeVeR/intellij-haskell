// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.psi.stubs.types

import com.intellij.psi.stubs.{IStubElementType, IndexSink, StubElement}
import intellij.haskell.HaskellLanguage
import intellij.haskell.psi.HaskellCompositeElement

abstract class HaskellStubElementType[S <: StubElement[T], T <: HaskellCompositeElement](debugName: String) extends IStubElementType[S, T](debugName, HaskellLanguage.Instance) {

  def indexStub(stub: S, sink: IndexSink): Unit

  def getExternalId: String = {
    "haskell." + super.toString
  }
}