// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell;

import com.intellij.lang.Language;
import org.jetbrains.annotations.NotNull;

public class HaskellLanguage extends Language {

    public static final HaskellLanguage Instance = new HaskellLanguage();

    public HaskellLanguage() {
        super("Haskell");
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "Haskell";
    }

    @Override
    public boolean isCaseSensitive() {
        return true;
    }
}
