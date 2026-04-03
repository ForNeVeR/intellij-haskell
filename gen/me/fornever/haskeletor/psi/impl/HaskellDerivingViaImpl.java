// This is a generated file. Not intended for manual editing.
package me.fornever.haskeletor.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import me.fornever.haskeletor.psi.HaskellDerivingVia;
import me.fornever.haskeletor.psi.HaskellVisitor;
import org.jetbrains.annotations.NotNull;

public class HaskellDerivingViaImpl extends HaskellCompositeElementImpl implements HaskellDerivingVia {

    public HaskellDerivingViaImpl(ASTNode node) {
        super(node);
    }

    public void accept(@NotNull HaskellVisitor visitor) {
        visitor.visitDerivingVia(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof HaskellVisitor) accept((HaskellVisitor) visitor);
        else super.accept(visitor);
    }

}
