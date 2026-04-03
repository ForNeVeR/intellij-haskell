// This is a generated file. Not intended for manual editing.
package me.fornever.haskeletor.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;

public interface HaskellQualifier extends HaskellQualifierElement, HaskellNamedElement {

  @NotNull
  List<HaskellConid> getConidList();

  String getName();

  PsiElement setName(String newName);

  HaskellNamedElement getNameIdentifier();

  PsiReference getReference();

  ItemPresentation getPresentation();

}
