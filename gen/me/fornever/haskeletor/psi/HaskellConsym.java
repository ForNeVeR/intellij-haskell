// This is a generated file. Not intended for manual editing.
package me.fornever.haskeletor.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import me.fornever.haskeletor.psi.stubs.HaskellConsymStub;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;

public interface HaskellConsym extends HaskellNamedElement, StubBasedPsiElement<HaskellConsymStub> {

  String getName();

  PsiElement setName(String newName);

  HaskellNamedElement getNameIdentifier();

  PsiReference getReference();

  ItemPresentation getPresentation();

}
