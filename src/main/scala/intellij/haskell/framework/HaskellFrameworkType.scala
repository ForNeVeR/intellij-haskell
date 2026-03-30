// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.framework

import com.intellij.framework.FrameworkTypeEx
import com.intellij.framework.addSupport.FrameworkSupportInModuleProvider
import icons.HaskellIcons
import javax.swing.Icon

class HaskellFrameworkType extends FrameworkTypeEx("haskell-framework-id") {

  override def createProvider(): FrameworkSupportInModuleProvider = new HaskellFrameworkSupportProvider

  override def getIcon: Icon = HaskellIcons.HaskellLogo

  override def getPresentableName: String = "Haskell"
}

object HaskellFrameworkType {
  def getInstance = FrameworkTypeEx.EP_NAME.findExtension(classOf[HaskellFrameworkType])
}

