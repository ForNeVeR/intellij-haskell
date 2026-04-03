// This is a generated file. Not intended for manual editing.
package me.fornever.haskeletor.psi;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface HaskellImportHidingSpec extends HaskellCompositeElement {

    @NotNull
    HaskellImportHiding getImportHiding();

    @NotNull
    List<HaskellImportId> getImportIdList();

    @NotNull
    List<HaskellPragma> getPragmaList();

}
