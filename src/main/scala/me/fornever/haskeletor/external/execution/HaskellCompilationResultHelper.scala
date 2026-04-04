/*
 * SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
 * SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
 * SPDX-FileCopyrightText: 2026 haskeletor contributors <https://github.com/ForNeVeR/haskeletor>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package me.fornever.haskeletor.external.execution

import com.intellij.psi.PsiFile
import me.fornever.haskeletor.util.{HaskellFileUtil, StringUtil}

object HaskellCompilationResultHelper {

  private final val ProblemPattern = """((?:[A-Z]:\\)?[^:]+):([\d]+):([\d]+):(.+)""".r

  final val LayoutSpaceChar = '\u00A0'

  def createCompilationResult(currentPsiFile: PsiFile, errorLines: Seq[String], failed: Boolean): CompilationResult = {
    val currentFilePath = HaskellFileUtil.getAbsolutePath(currentPsiFile).getOrElse(throw new IllegalStateException(s"File `${currentPsiFile.getName}` exists only in memory"))

    val compilationProblems = errorLines.flatMap(parseErrorLine)

    val (currentFileProblems, otherFileProblems) = compilationProblems.partition(_.filePath == currentFilePath)

    CompilationResult(currentFileProblems, otherFileProblems, failed)
  }

  def parseErrorLine(errorLine: String): Option[CompilationProblem] = {
    errorLine match {
      case ProblemPattern(filePath, lineNr, columnNr, message) =>
        val displayMessage = message.trim.replaceAll("""(\s\s\s\s+)""", "\n" + "$1")
        Some(CompilationProblem(filePath, lineNr.toInt, columnNr.toInt, displayMessage))
      case _ => None
    }
  }
}

case class CompilationResult(currentFileProblems: Iterable[CompilationProblem], otherFileProblems: Iterable[CompilationProblem], failed: Boolean)

case class CompilationProblem(filePath: String, lineNr: Int, columnNr: Int, message: String) {

  import me.fornever.haskeletor.external.execution.HaskellCompilationResultHelper.LayoutSpaceChar

  def plainMessage: String = {
    message.split("\n").mkString.replaceAll("\\s+", " ")
  }

  def htmlMessage: String = {
    StringUtil.escapeString(message.replace(' ', LayoutSpaceChar))
  }

  def isWarning: Boolean = {
    message.startsWith("warning:") || message.startsWith("Warning:")
  }
}


