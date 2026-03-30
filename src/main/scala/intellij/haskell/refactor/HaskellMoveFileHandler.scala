// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.refactor

import java.util

import com.intellij.psi.{PsiDirectory, PsiElement, PsiFile}
import com.intellij.refactoring.move.moveFilesOrDirectories.MoveFileHandler
import com.intellij.usageView.UsageInfo
import intellij.haskell.HaskellFile
import intellij.haskell.annotator.HaskellAnnotator
import intellij.haskell.external.component.HaskellComponentsManager
import intellij.haskell.psi.HaskellPsiUtil

class HaskellMoveFileHandler extends MoveFileHandler {
  override def prepareMovedFile(file: PsiFile, moveDestination: PsiDirectory, oldToNewMap: util.Map[PsiElement, PsiElement]): Unit = {}

  override def retargetUsages(usageInfos: util.List[UsageInfo], oldToNewMap: util.Map[PsiElement, PsiElement]): Unit = {}

  override def canProcessElement(psiFile: PsiFile): Boolean = {
    psiFile.isInstanceOf[HaskellFile]
  }

  override def findUsages(psiFile: PsiFile, newParent: PsiDirectory, searchInComments: Boolean, searchInNonJavaFiles: Boolean): util.List[UsageInfo] = {
    java.util.Collections.emptyList()
  }

  override def updateMovedFile(psiFile: PsiFile): Unit = {
    HaskellPsiUtil.invalidateModuleName(psiFile)
    HaskellComponentsManager.clearLoadedModule(psiFile)
    HaskellComponentsManager.invalidateFileInfos(psiFile)
    HaskellAnnotator.restartDaemonCodeAnalyzerForFile(psiFile)
  }

}