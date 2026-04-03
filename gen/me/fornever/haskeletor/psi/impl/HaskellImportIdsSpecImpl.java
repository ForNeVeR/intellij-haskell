// This is a generated file. Not intended for manual editing.
package me.fornever.haskeletor.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import me.fornever.haskeletor.psi.HaskellImportId;
import me.fornever.haskeletor.psi.HaskellImportIdsSpec;
import me.fornever.haskeletor.psi.HaskellPragma;
import me.fornever.haskeletor.psi.HaskellVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HaskellImportIdsSpecImpl extends HaskellCompositeElementImpl implements HaskellImportIdsSpec {

    public HaskellImportIdsSpecImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull HaskellVisitor visitor) {
        visitor.visitImportIdsSpec(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof HaskellVisitor) accept((HaskellVisitor) visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public List<HaskellImportId> getImportIdList() {
        return PsiTreeUtil.getChildrenOfTypeAsList(this, HaskellImportId.class);
    }

    @Override
    @NotNull
    public List<HaskellPragma> getPragmaList() {
        return PsiTreeUtil.getChildrenOfTypeAsList(this, HaskellPragma.class);
    }

}
