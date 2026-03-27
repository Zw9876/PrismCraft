package com.eaglercraft.math;

public class Matrix4f {
    public float[] m = new float[16];

    public Matrix4f() {
        identity();
    }

    public Matrix4f identity() {
        for (int i = 0; i < 16; i++) m[i] = 0;
        m[0] = m[5] = m[10] = m[15] = 1.0f;
        return this;
    }

    public static Matrix4f perspective(float fovDegrees, float aspect, float near, float far) {
        Matrix4f result = new Matrix4f();
        float fovRad = (float) Math.toRadians(fovDegrees);
        float f = (float) (1.0 / Math.tan(fovRad / 2.0));
        float rangeInv = 1.0f / (near - far);

        result.m[0]  = f / aspect;
        result.m[5]  = f;
        result.m[10] = (near + far) * rangeInv;
        result.m[11] = -1.0f;
        result.m[14] = near * far * rangeInv * 2.0f;
        result.m[15] = 0.0f;
        return result;
    }

    public static Matrix4f lookAt(Vector3f eye, Vector3f center, Vector3f up) {
        Vector3f f = center.subtract(eye).normalize();
        Vector3f s = f.cross(up).normalize();
        Vector3f u = s.cross(f);

        Matrix4f result = new Matrix4f();
        result.m[0]  =  s.x;
        result.m[4]  =  s.y;
        result.m[8]  =  s.z;
        result.m[1]  =  u.x;
        result.m[5]  =  u.y;
        result.m[9]  =  u.z;
        result.m[2]  = -f.x;
        result.m[6]  = -f.y;
        result.m[10] = -f.z;
        result.m[12] = -s.dot(eye);
        result.m[13] = -u.dot(eye);
        result.m[14] =  f.dot(eye);
        return result;
    }

    public static Matrix4f translation(float x, float y, float z) {
        Matrix4f result = new Matrix4f();
        result.m[12] = x;
        result.m[13] = y;
        result.m[14] = z;
        return result;
    }

    public static Matrix4f rotationY(float angleDegrees) {
        Matrix4f result = new Matrix4f();
        float rad = (float) Math.toRadians(angleDegrees);
        float cos = (float) Math.cos(rad);
        float sin = (float) Math.sin(rad);
        result.m[0]  =  cos;
        result.m[2]  =  sin;
        result.m[8]  = -sin;
        result.m[10] =  cos;
        return result;
    }

    public Matrix4f multiply(Matrix4f other) {
        Matrix4f result = new Matrix4f();
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                float sum = 0;
                for (int k = 0; k < 4; k++) {
                    sum += m[row + k * 4] * other.m[k + col * 4];
                }
                result.m[row + col * 4] = sum;
            }
        }
        return result;
    }

    public float[] toArray() {
        return m;
    }
}