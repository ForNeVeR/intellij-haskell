/*
 * SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
 * SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
 * SPDX-FileCopyrightText: 2026 haskeletor contributors <https://github.com/ForNeVeR/haskeletor>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package me.fornever.haskeletor.psi

import me.fornever.haskeletor.psi.impl.HaskellPsiImplUtil

/**
 * Workaround: Grammar-Kit Gradle plugin doesn't support psiImplUtilClass,
 * so we provide default implementations that delegate to HaskellPsiImplUtil.
 * Only HaskellQName implements this trait (per BNF), so the cast is safe.
 */
trait HaskellQualifiedNameElement extends HaskellCompositeElement {

  def getName: String = HaskellPsiImplUtil.getName(this.asInstanceOf[HaskellQName])

  def getIdentifierElement: HaskellNamedElement = HaskellPsiImplUtil.getIdentifierElement(this.asInstanceOf[HaskellQName])

  def getQualifierName: Option[String] = HaskellPsiImplUtil.getQualifierName(this.asInstanceOf[HaskellQName])
}
