// This is a generated file. Not intended for manual editing.
package me.fornever.haskeletor.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import me.fornever.haskeletor.psi.stubs.HaskellConidStub;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;

public interface HaskellConid extends HaskellNamedElement, StubBasedPsiElement<HaskellConidStub> {

  String getName();

  PsiElement setName(String newName);

  HaskellNamedElement getNameIdentifier();

  PsiReference getReference();

  ItemPresentation getPresentation();

}
