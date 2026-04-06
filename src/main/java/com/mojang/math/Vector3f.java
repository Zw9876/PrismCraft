package com.mojang.math;

public class Vector3f {
    public float x, y, z;

    public Vector3f() {}
    public Vector3f(float x, float y, float z) {
        this.x = x; this.y = y; this.z = z;
    }

    public static final Vector3f XP = new Vector3f(1, 0, 0);
    public static final Vector3f YP = new Vector3f(0, 1, 0);
    public static final Vector3f ZP = new Vector3f(0, 0, 1);
    public static final Vector3f XN = new Vector3f(-1, 0, 0);
    public static final Vector3f YN = new Vector3f(0, -1, 0);
    public static final Vector3f ZN = new Vector3f(0, 0, -1);
}