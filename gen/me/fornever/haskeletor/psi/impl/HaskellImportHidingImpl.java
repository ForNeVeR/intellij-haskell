// This is a generated file. Not intended for manual editing.
package me.fornever.haskeletor.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static me.fornever.haskeletor.psi.HaskellTypes.*;
import me.fornever.haskeletor.psi.*;

public class HaskellImportHidingImpl extends HaskellCompositeElementImpl implements HaskellImportHiding {

  public HaskellImportHidingImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull HaskellVisitor visitor) {
    visitor.visitImportHiding(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof HaskellVisitor) accept((HaskellVisitor)visitor);
    else super.accept(visitor);
  }

}
