/*
 * SPDX-FileCopyrightText: 2012-2014 Sergey Ignatov
 * SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
 * SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
 * SPDX-FileCopyrightText: 2026 haskeletor contributors <https://github.com/ForNeVeR/haskeletor>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package me.fornever.haskeletor.editor;

import com.intellij.codeInsight.CodeInsightSettings;
import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.util.text.CharArrayCharSequence;
import me.fornever.haskeletor.HaskellFile;
import org.jetbrains.annotations.NotNull;

/**
 * Credits to Erlang plugin for the initial code that automatically closes paired braces.
 */
public class HaskellTypedHandler extends TypedHandlerDelegate {

    @NotNull
    @Override
    public Result charTyped(char c, @NotNull Project project, @NotNull Editor editor, @NotNull PsiFile psiFile) {
        if (!(psiFile instanceof HaskellFile)) return super.charTyped(c, project, editor, psiFile);

        if ((c != '-' && c != '#') || !CodeInsightSettings.getInstance().AUTOINSERT_PAIR_BRACKET) {
            return Result.CONTINUE;
        }

        insertMatchedEndComment(project, editor, psiFile, c);
        return Result.CONTINUE;
    }

    /**
     * This is originally copied from TypedHandler,
     *
     * @see com.intellij.codeInsight.editorActions.TypedHandler
     */
    private static void insertMatchedEndComment(Project project, Editor editor, PsiFile psiFile, char c) {
        if (!(psiFile instanceof HaskellFile)) return;

        PsiDocumentManager.getInstance(project).commitAllDocuments();

        int offset = editor.getCaretModel().getOffset();
        final CharSequence fileText = editor.getDocument().getCharsSequence();

        if ((offset > 1 && c == '-' && fileText.charAt(offset - 2) == '{') || ((offset > 2 && c == '#' && fileText.charAt(offset - 3) == '{'))) {
            editor.getDocument().insertString(offset, new CharArrayCharSequence(c));
        }
    }
}
