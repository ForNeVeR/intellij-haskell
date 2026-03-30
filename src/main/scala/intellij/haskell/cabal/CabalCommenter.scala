// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.cabal

import com.intellij.lang.Commenter

class CabalCommenter extends Commenter {
  override def getCommentedBlockCommentPrefix: String = null

  override def getBlockCommentSuffix: String = null

  override def getBlockCommentPrefix: String = null

  override def getLineCommentPrefix: String = "--"

  override def getCommentedBlockCommentSuffix: String = null
}
