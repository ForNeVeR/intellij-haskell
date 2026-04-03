// This is a generated file. Not intended for manual editing.
package me.fornever.haskeletor.alex.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static me.fornever.haskeletor.alex.lang.psi.AlexTypes.*;
import me.fornever.haskeletor.alex.lang.psi.*;

public class AlexRuleDescriptionImpl extends AlexElementImpl implements AlexRuleDescription {

  public AlexRuleDescriptionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull AlexVisitor visitor) {
    visitor.visitRuleDescription(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof AlexVisitor) accept((AlexVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<AlexRegex> getRegexList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, AlexRegex.class);
  }

  @Override
  @Nullable
  public AlexRuleId getRuleId() {
    return findChildByClass(AlexRuleId.class);
  }

}
