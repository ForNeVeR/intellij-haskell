// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.external.execution

import org.junit.runner.RunWith
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen}
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class HaskellCompilationResultHelperSpec extends AnyFunSpec with Matchers with GivenWhenThen with BeforeAndAfterEach {

  describe("LoadComponent") {
    it("should parse `:load` output") {
      Given("output load")
      val output = "/file/path/HaskellFile.hs:1:11:parse error on input     and so on"

      When("parsed to problem")
      val problem = HaskellCompilationResultHelper.parseErrorLine(output).get

      Then("it should contain right data")
      problem.lineNr should equal(1)
      problem.columnNr should equal(11)
      problem.plainMessage should equal(s"parse error on input and so on")
    }
  }
}
