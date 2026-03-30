// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.psi;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.parser.GeneratedParserUtilBase;
import com.intellij.psi.TokenType;

import static intellij.haskell.psi.HaskellTypes.*;

public class HaskellParserUtil extends GeneratedParserUtilBase {

    public static boolean containsSpaces(PsiBuilder builder, int level) {
        return (builder.rawLookup(0) == HS_NEWLINE && (builder.rawLookup(1) == TokenType.WHITE_SPACE || builder.rawLookup(1) == HS_COMMENT) || builder.rawLookup(1) == HS_NCOMMENT || builder.rawLookup(1) == HS_DIRECTIVE || builder.rawLookup(1) == HS_HADDOCK || builder.rawLookup(1) == HS_NHADDOCK) ||
                (builder.rawLookup(0) == HS_NEWLINE && builder.rawLookup(1) == HS_NEWLINE && builder.rawLookup(2) == TokenType.WHITE_SPACE) ||
                (builder.rawLookup(0) == HS_NEWLINE && builder.rawLookup(1) == HS_NEWLINE && builder.rawLookup(2) == HS_NEWLINE && builder.rawLookup(3) == TokenType.WHITE_SPACE) ||
                (builder.rawLookup(0) == HS_NEWLINE && builder.rawLookup(1) == HS_NEWLINE && builder.rawLookup(2) == HS_NEWLINE && builder.rawLookup(3) == HS_NEWLINE && builder.rawLookup(4) == TokenType.WHITE_SPACE) ||
                (builder.rawLookup(0) == HS_NEWLINE && builder.rawLookup(1) == HS_NEWLINE && builder.rawLookup(2) == HS_NEWLINE && builder.rawLookup(3) == HS_NEWLINE && builder.rawLookup(4) == HS_NEWLINE && builder.rawLookup(5) == TokenType.WHITE_SPACE) ||
                (builder.rawLookup(0) == HS_NEWLINE && builder.rawLookup(1) == HS_NEWLINE && builder.rawLookup(2) == HS_NEWLINE && builder.rawLookup(3) == HS_NEWLINE && builder.rawLookup(4) == HS_NEWLINE && builder.rawLookup(5) == HS_NEWLINE && builder.rawLookup(6) == TokenType.WHITE_SPACE) ||
                (builder.rawLookup(0) == HS_NEWLINE && builder.rawLookup(1) == HS_NEWLINE && builder.rawLookup(2) == HS_NEWLINE && builder.rawLookup(3) == HS_NEWLINE && builder.rawLookup(4) == HS_NEWLINE && builder.rawLookup(5) == HS_NEWLINE && builder.rawLookup(6) == HS_NEWLINE && builder.rawLookup(7) == TokenType.WHITE_SPACE);
    }

    public static boolean noSpaceAfterQualifier(PsiBuilder builder, int level) {
        return (builder.rawLookup(0) == HS_CON_ID && builder.rawLookup(1) == HS_DOT);
    }

}
