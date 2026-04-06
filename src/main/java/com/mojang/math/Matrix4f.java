package com.mojang.math;

import java.nio.FloatBuffer;

public class Matrix4f {
    public float[] m = new float[16];

    public Matrix4f() { setIdentity(); }

    public void setIdentity() {
        for (int i = 0; i < 16; i++) m[i] = 0;
        m[0] = m[5] = m[10] = m[15] = 1.0f;
    }

    public void store(FloatBuffer buffer) {
        for (float v : m) buffer.put(v);
    }

    public void load(FloatBuffer buffer) {
        for (int i = 0; i < 16; i++) m[i] = buffer.get();
    }

    public Matrix4f copy() {
        Matrix4f copy = new Matrix4f();
        System.arraycopy(m, 0, copy.m, 0, 16);
        return copy;
    }
}