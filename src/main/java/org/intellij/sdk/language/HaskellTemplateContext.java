package org.intellij.sdk.language;

import com.intellij.codeInsight.template.TemplateContextType;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HaskellTemplateContext extends TemplateContextType {
    protected HaskellTemplateContext() {
        super("HASKELL", "Haskell");
    }

    @Override
    public boolean isInContext(@NotNull PsiFile file, int offset) {
        return file.getLanguage().isKindOf(HaskellLanguage.INSTANCE);
    }
}
