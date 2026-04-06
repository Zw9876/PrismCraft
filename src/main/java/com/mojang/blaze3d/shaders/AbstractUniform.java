package com.mojang.blaze3d.shaders;

import com.mojang.math.Matrix4f;

public class AbstractUniform {
    public void set(float v) {}
    public void set(float v1, float v2) {}
    public void set(float v1, float v2, float v3) {}
    public void set(float v1, float v2, float v3, float v4) {}
    public void setSafe(float v1, float v2, float v3, float v4) {}
    public void setSafe(int v1, int v2, int v3, int v4) {}
    public void set(float[] values) {}
    public void set(Matrix4f matrix) {}
}