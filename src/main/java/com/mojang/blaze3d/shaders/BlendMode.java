package com.mojang.blaze3d.shaders;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.Locale;

public class BlendMode {
    private static BlendMode lastApplied;
    private final int srcColorFactor;
    private final int srcAlphaFactor;
    private final int dstColorFactor;
    private final int dstAlphaFactor;
    private final int blendFunc;
    private final boolean separateBlend;
    private final boolean opaque;

    private BlendMode(boolean separateBlend, boolean opaque, int srcColor, int dstColor, int srcAlpha, int dstAlpha, int blendFunc) {
        this.separateBlend = separateBlend;
        this.srcColorFactor = srcColor;
        this.dstColorFactor = dstColor;
        this.srcAlphaFactor = srcAlpha;
        this.dstAlphaFactor = dstAlpha;
        this.opaque = opaque;
        this.blendFunc = blendFunc;
    }

    public BlendMode() {
        this(false, true, 1, 0, 1, 0, 32774);
    }

    public BlendMode(int src, int dst, int func) {
        this(false, false, src, dst, src, dst, func);
    }

    public BlendMode(int srcColor, int dstColor, int srcAlpha, int dstAlpha, int func) {
        this(true, false, srcColor, dstColor, srcAlpha, dstAlpha, func);
    }

    public void apply() {
        if (!this.equals(lastApplied)) {
            if (lastApplied == null || this.opaque != lastApplied.isOpaque()) {
                lastApplied = this;
                if (this.opaque) {
                    RenderSystem.disableBlend();
                    return;
                }
                RenderSystem.enableBlend();
            }
            RenderSystem.blendEquation(this.blendFunc);
            if (this.separateBlend) {
                RenderSystem.blendFuncSeparate(this.srcColorFactor, this.dstColorFactor, this.srcAlphaFactor, this.dstAlphaFactor);
            } else {
                RenderSystem.blendFunc(this.srcColorFactor, this.dstColorFactor);
            }
        }
    }

    public boolean isOpaque() { return this.opaque; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BlendMode)) return false;
        BlendMode other = (BlendMode) obj;
        return this.blendFunc == other.blendFunc
                && this.dstAlphaFactor == other.dstAlphaFactor
                && this.dstColorFactor == other.dstColorFactor
                && this.opaque == other.opaque
                && this.separateBlend == other.separateBlend
                && this.srcAlphaFactor == other.srcAlphaFactor
                && this.srcColorFactor == other.srcColorFactor;
    }

    @Override
    public int hashCode() {
        int h = this.srcColorFactor;
        h = 31 * h + this.srcAlphaFactor;
        h = 31 * h + this.dstColorFactor;
        h = 31 * h + this.dstAlphaFactor;
        h = 31 * h + this.blendFunc;
        h = 31 * h + (this.separateBlend ? 1 : 0);
        return 31 * h + (this.opaque ? 1 : 0);
    }

    public static int stringToBlendFunc(String name) {
        String s = name.trim().toLowerCase(Locale.ROOT);
        if ("add".equals(s)) return 32774;
        if ("subtract".equals(s)) return 32778;
        if ("reversesubtract".equals(s)) return 32779;
        if ("reverse_subtract".equals(s)) return 32779;
        if ("min".equals(s)) return 32775;
        if ("max".equals(s)) return 32776;
        return 32774;
    }

    public static int stringToBlendFactor(String name) {
        String s = name.trim().toLowerCase(Locale.ROOT)
                .replaceAll("_", "")
                .replaceAll("one", "1")
                .replaceAll("zero", "0")
                .replaceAll("minus", "-");
        if ("0".equals(s)) return 0;
        if ("1".equals(s)) return 1;
        if ("srccolor".equals(s)) return 768;
        if ("1-srccolor".equals(s)) return 769;
        if ("dstcolor".equals(s)) return 774;
        if ("1-dstcolor".equals(s)) return 775;
        if ("srcalpha".equals(s)) return 770;
        if ("1-srcalpha".equals(s)) return 771;
        if ("dstalpha".equals(s)) return 772;
        if ("1-dstalpha".equals(s)) return 773;
        return -1;
    }
}