// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.framework

import com.intellij.framework.FrameworkTypeEx
import com.intellij.framework.addSupport.{FrameworkSupportInModuleConfigurable, FrameworkSupportInModuleProvider}
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModel
import com.intellij.ide.util.projectWizard.ModuleBuilder
import com.intellij.openapi.module.{Module, ModuleType}
import com.intellij.openapi.roots.{ModifiableModelsProvider, ModifiableRootModel}
import intellij.haskell.module.HaskellModuleType
import javax.swing.JComponent

class HaskellFrameworkSupportProvider extends FrameworkSupportInModuleProvider {
  override def getFrameworkType: FrameworkTypeEx = HaskellFrameworkType.getInstance

  override def isEnabledForModuleType(moduleType: ModuleType[_ <: ModuleBuilder]): Boolean = moduleType.isInstanceOf[HaskellModuleType]

  override def createConfigurable(model: FrameworkSupportModel): FrameworkSupportInModuleConfigurable = {
    new FrameworkSupportInModuleConfigurable {
      override def createComponent(): JComponent = null

      override def addSupport(module: Module, rootModel: ModifiableRootModel, modifiableModelsProvider: ModifiableModelsProvider): Unit = {
      }
    }
  }
}
