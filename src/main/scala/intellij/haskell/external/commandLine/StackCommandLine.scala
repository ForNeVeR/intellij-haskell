/*
 * Copyright 2016 Rik van der Kleij
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package intellij.haskell.external.commandLine

import com.intellij.execution.process.ProcessOutput
import com.intellij.openapi.project.Project
import intellij.haskell.HaskellNotificationGroup
import intellij.haskell.sdk.HaskellSdkType

import scala.concurrent.duration._

object StackCommandLine {

  private final val DefaultTimeout = 2.seconds.toMillis

  private final val BuildTimeout = 30.minutes

  def runCommand(command: Seq[String], project: Project, timeoutInMillis: Long = DefaultTimeout, captureOutputToLog: Boolean = false): Option[ProcessOutput] = {
    HaskellSdkType.getStackPath(project).flatMap(stackPath => {
      CommandLine.runCommand(
        project.getBasePath,
        stackPath,
        command,
        timeoutInMillis.toInt,
        captureOutputToLog)
    })
  }

  def executeBuild(project: Project, buildArguments: Seq[String], message: String): Unit = {
    HaskellNotificationGroup.logInfo(s"$message is starting")
    HaskellNotificationGroup.logInfo(s"""Build command is `stack ${buildArguments.mkString(" ")}`""")
    StackCommandLine.runCommand(buildArguments, project, BuildTimeout.toMillis, captureOutputToLog = true)
    HaskellNotificationGroup.logInfo(s"$message is finished")
  }
}