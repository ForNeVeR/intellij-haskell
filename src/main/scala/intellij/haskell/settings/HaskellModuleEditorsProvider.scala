// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.settings

import com.intellij.openapi.module.{ModuleConfigurationEditor, ModuleType}
import com.intellij.openapi.roots.ui.configuration.{ClasspathEditor, ContentEntriesEditor, DefaultModuleEditorsProvider, ModuleConfigurationState}
import intellij.haskell.module.HaskellModuleType

class HaskellModuleEditorsProvider extends DefaultModuleEditorsProvider {

  override def createEditors(state: ModuleConfigurationState): Array[ModuleConfigurationEditor] = {
    val module = state.getRootModel.getModule
    if (!ModuleType.get(module).isInstanceOf[HaskellModuleType]) {
      ModuleConfigurationEditor.EMPTY
    } else {
      Array[ModuleConfigurationEditor](new ContentEntriesEditor(module.getName, state), new ClasspathEditor(state))
    }
  }
}