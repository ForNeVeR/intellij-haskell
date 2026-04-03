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

public class HaskellQConQualifierImpl extends HaskellCompositeElementImpl implements HaskellQConQualifier {

  public HaskellQConQualifierImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull HaskellVisitor visitor) {
    visitor.visitQConQualifier(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof HaskellVisitor) accept((HaskellVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public HaskellQConQualifier1 getQConQualifier1() {
    return PsiTreeUtil.getChildOfType(this, HaskellQConQualifier1.class);
  }

  @Override
  @Nullable
  public HaskellQConQualifier2 getQConQualifier2() {
    return PsiTreeUtil.getChildOfType(this, HaskellQConQualifier2.class);
  }

  @Override
  @Nullable
  public HaskellQConQualifier3 getQConQualifier3() {
    return PsiTreeUtil.getChildOfType(this, HaskellQConQualifier3.class);
  }

  @Override
  @Nullable
  public HaskellQConQualifier4 getQConQualifier4() {
    return PsiTreeUtil.getChildOfType(this, HaskellQConQualifier4.class);
  }

}
