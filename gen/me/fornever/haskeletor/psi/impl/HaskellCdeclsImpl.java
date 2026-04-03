// This is a generated file. Not intended for manual editing.
package me.fornever.haskeletor.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import me.fornever.haskeletor.psi.HaskellCdecl;
import me.fornever.haskeletor.psi.HaskellCdecls;
import me.fornever.haskeletor.psi.HaskellPragma;
import me.fornever.haskeletor.psi.HaskellVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HaskellCdeclsImpl extends HaskellCompositeElementImpl implements HaskellCdecls {

    public HaskellCdeclsImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull HaskellVisitor visitor) {
        visitor.visitCdecls(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof HaskellVisitor) accept((HaskellVisitor) visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public List<HaskellCdecl> getCdeclList() {
        return PsiTreeUtil.getChildrenOfTypeAsList(this, HaskellCdecl.class);
    }

    @Override
    @NotNull
    public List<HaskellPragma> getPragmaList() {
        return PsiTreeUtil.getChildrenOfTypeAsList(this, HaskellPragma.class);
    }

}
