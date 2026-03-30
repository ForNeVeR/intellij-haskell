// SPDX-FileCopyrightText: 2000-2015 JetBrains s.r.o.
// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.action

import java.awt.GridLayout

import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.{DumbService, Project}
import com.intellij.openapi.vcs.CheckinProjectPanel
import com.intellij.openapi.vcs.checkin._
import com.intellij.openapi.vcs.ui.RefreshableOnComponent
import com.intellij.ui.NonFocusableCheckBox
import intellij.haskell.editor.HaskellImportOptimizer
import intellij.haskell.settings.HaskellSettingsState
import intellij.haskell.util.HaskellFileUtil
import javax.swing.{JCheckBox, JComponent, JPanel}

class HaskellOptimizeImportsBeforeCheckinHandler(project: Project, checkinProjectPanel: CheckinProjectPanel) extends CheckinHandler with CheckinMetaHandler {

  override def getBeforeCheckinConfigurationPanel: RefreshableOnComponent = {
    val optimizeBox = new NonFocusableCheckBox("Haskell optimize imports")
    disableWhenDumb(project, optimizeBox, "Impossible until indices are up-to-date")
    new RefreshableOnComponent() {
      override def getComponent: JComponent = {
        val panel = new JPanel(new GridLayout(1, 0))
        panel.add(optimizeBox)
        panel
      }

      override def refresh(): Unit = {
      }

      override def saveState(): Unit = {
        HaskellSettingsState.setOptimizeImportsBeforeCommit(optimizeBox.isSelected)
      }

      override def restoreState(): Unit = {
        optimizeBox.setSelected(HaskellSettingsState.isOptmizeImportsBeforeCommit)
      }
    }
  }

  override def runCheckinHandlers(finishAction: Runnable): Unit = {
    import scala.jdk.CollectionConverters._
    val virtualFiles = checkinProjectPanel.getVirtualFiles

    val performCheckoutAction: Runnable = () => {
      FileDocumentManager.getInstance.saveAllDocuments()
      finishAction.run()
    }

    if (HaskellSettingsState.isReformatCodeBeforeCommit && !DumbService.isDumb(project)) {
      val reformatResult = virtualFiles.asScala.forall(vf => HaskellFileUtil.convertToHaskellFileDispatchThread(project, vf).exists(HaskellImportOptimizer.removeRedundantImports))
      if (reformatResult) {
        performCheckoutAction.run()
      }
    } else {
      performCheckoutAction.run()
    }
  }


  private def disableWhenDumb(project: Project, checkBox: JCheckBox, tooltip: String): Unit = {
    val dumb = DumbService.isDumb(project)
    checkBox.setEnabled(!dumb)
    checkBox.setToolTipText(if (dumb) tooltip else "")
  }
}
