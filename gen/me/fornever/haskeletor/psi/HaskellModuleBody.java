// This is a generated file. Not intended for manual editing.
package me.fornever.haskeletor.psi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface HaskellModuleBody extends HaskellCompositeElement {

    @Nullable
    HaskellImportDeclarations getImportDeclarations();

    @Nullable
    HaskellModuleDeclaration getModuleDeclaration();

    @NotNull
    List<HaskellPragma> getPragmaList();

    @NotNull
    List<HaskellTopDeclaration> getTopDeclarationList();

}
