// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.action

import com.intellij.ide.actions.GotoActionBase
import com.intellij.ide.util.EditSourceUtil
import com.intellij.ide.util.gotoByName.{ChooseByNameFilter, ChooseByNameLanguageFilter, ChooseByNamePopup, GotoClassSymbolConfiguration}
import com.intellij.lang.Language
import com.intellij.navigation.{ChooseByNameContributor, NavigationItem}
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDocumentManager
import intellij.haskell.external.component.{HoogleComponent, StackProjectManager}
import intellij.haskell.navigation.{GotoByHoogleModel, HoogleByNameContributor}
import intellij.haskell.util.HaskellEditorUtil

class HoogleNavigationAction extends GotoActionBase {

  private val contributors = Array[ChooseByNameContributor](new HoogleByNameContributor)

  override def update(actionEvent: AnActionEvent): Unit = {
    HaskellEditorUtil.enableExternalAction(actionEvent, (project: Project) => !StackProjectManager.isInitializing(project) && StackProjectManager.isHoogleAvailable(project).isDefined && HoogleComponent.doesHoogleDatabaseExist(project))
  }

  def gotoActionPerformed(actionEvent: AnActionEvent): Unit = {
    ActionUtil.findActionContext(actionEvent).foreach(context => {

      val project = context.project
      val model = new GotoByHoogleModel(project, contributors)

      PsiDocumentManager.getInstance(project).commitAllDocuments()

      showNavigationPopup(actionEvent, model, new GotoActionBase.GotoActionCallback[Language]() {
        override protected def createFilter(popup: ChooseByNamePopup): ChooseByNameFilter[Language] = {
          new ChooseByNameLanguageFilter(popup, model, GotoClassSymbolConfiguration.getInstance(project), project).asInstanceOf[ChooseByNameFilter[Language]]
        }

        def elementChosen(popup: ChooseByNamePopup, element: Any): Unit = {
          EditSourceUtil.navigate(element.asInstanceOf[NavigationItem], true, popup.isOpenInCurrentWindowRequested)
        }
      }, "Hoogle for words or type signature", true)
    })
  }
}