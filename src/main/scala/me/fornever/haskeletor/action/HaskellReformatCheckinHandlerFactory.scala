/*
 * SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
 * SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
 * SPDX-FileCopyrightText: 2026 haskeletor contributors <https://github.com/ForNeVeR/haskeletor>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package me.fornever.haskeletor.action

import com.intellij.openapi.vcs.CheckinProjectPanel
import com.intellij.openapi.vcs.changes.CommitContext
import com.intellij.openapi.vcs.checkin.{CheckinHandler, CheckinHandlerFactory}
import me.fornever.haskeletor.util.HaskellProjectUtil

class HaskellReformatCheckinHandlerFactory extends CheckinHandlerFactory {
  override def createHandler(panel: CheckinProjectPanel, commitContext: CommitContext): CheckinHandler = {
    if (HaskellProjectUtil.isHaskellProject(panel.getProject)) {
      new HaskellReformatBeforeCheckinHandler(panel.getProject, panel)
    } else {
      CheckinHandler.DUMMY
    }
  }
}
