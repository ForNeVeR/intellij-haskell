/*
 * SPDX-FileCopyrightText: 2014-2022 Rik van der Kleij
 * SPDX-FileCopyrightText: 2014-2022 intellij-haskell contributors <https://github.com/rikvdkleij/intellij-haskell>
 * SPDX-FileCopyrightText: 2026 haskeletor contributors <https://github.com/ForNeVeR/haskeletor>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package me.fornever.haskeletor.psi.impl;

import com.intellij.extapi.psi.StubBasedPsiElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import me.fornever.haskeletor.psi.HaskellNamedElement;
import org.jetbrains.annotations.NotNull;

public abstract class HaskellNamedStubBasedPsiElementBase<T extends StubElement<?>> extends StubBasedPsiElementBase<T> implements HaskellNamedElement {

    HaskellNamedStubBasedPsiElementBase(@NotNull T stub, IStubElementType nodeType) {
        super(stub, nodeType);
    }

    HaskellNamedStubBasedPsiElementBase(ASTNode node) {
        super(node);
    }
}
