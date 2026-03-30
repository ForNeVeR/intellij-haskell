// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.module

import com.intellij.openapi.module.{ModuleType, ModuleTypeManager}
import icons.HaskellIcons
import javax.swing.Icon

class HaskellModuleType extends ModuleType[HaskellModuleBuilder](HaskellModuleType.Id) {

  def createModuleBuilder(): HaskellModuleBuilder = new HaskellModuleBuilder

  def getName: String = "Haskell module"

  def getDescription: String = "Haskell module for Haskell project"

  def getNodeIcon(isOpened: Boolean): Icon = HaskellIcons.HaskellLogo

  def getBigIcon: Icon = HaskellIcons.HaskellLogo
}

object HaskellModuleType {
  val Id = "HASKELL_MODULE"

  def getInstance: HaskellModuleType = {
    ModuleTypeManager.getInstance.findByID(Id).asInstanceOf[HaskellModuleType]
  }

}
