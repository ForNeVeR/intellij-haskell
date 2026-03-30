// SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
// SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
//
// SPDX-License-Identifier: Apache-2.0

package intellij.haskell.alex;

import com.intellij.lang.Language;
import org.jetbrains.annotations.NotNull;

/**
 * @author ice1000
 */
public class AlexLanguage extends Language {
    public static final AlexLanguage Instance = new AlexLanguage();

    public AlexLanguage() {
        super("Alex", "text/x");
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "Alex";
    }

    @Override
    public boolean isCaseSensitive() {
        return true;
    }
}
