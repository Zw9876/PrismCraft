package com.eaglercraft;

import com.eaglercraft.math.Matrix4f;
import com.eaglercraft.math.Vector3f;

public class Camera {
    private Vector3f position;
    private float yaw = -90.0f;
    private float pitch = 0.0f;
    private float fov = 70.0f;

    public Camera(float x, float y, float z) {
        this.position = new Vector3f(x, y, z);
    }

    public void moveForward(float speed) {
        Vector3f front = getFront();
        position = position.add(front.scale(speed));
    }

    public void moveBackward(float speed) {
        Vector3f front = getFront();
        position = position.subtract(front.scale(speed));
    }

    public void moveLeft(float speed) {
        Vector3f front = getFront();
        Vector3f right = front.cross(new Vector3f(0, 1, 0)).normalize();
        position = position.subtract(right.scale(speed));
    }

    public void moveRight(float speed) {
        Vector3f front = getFront();
        Vector3f right = front.cross(new Vector3f(0, 1, 0)).normalize();
        position = position.add(right.scale(speed));
    }

    public void rotate(float deltYaw, float deltPitch) {
        yaw += deltYaw;
        pitch += deltPitch;
        if (pitch > 89.0f) pitch = 89.0f;
        if (pitch < -89.0f) pitch = -89.0f;
    }

    public Vector3f getFront() {
        float yawRad = (float) Math.toRadians(yaw);
        float pitchRad = (float) Math.toRadians(pitch);
        return new Vector3f(
                (float) (Math.cos(yawRad) * Math.cos(pitchRad)),
                (float) Math.sin(pitchRad),
                (float) (Math.sin(yawRad) * Math.cos(pitchRad))
        ).normalize();
    }

    public Matrix4f getViewMatrix() {
        Vector3f front = getFront();
        return Matrix4f.lookAt(position, position.add(front), new Vector3f(0, 1, 0));
    }

    public Matrix4f getProjectionMatrix(float aspectRatio) {
        return Matrix4f.perspective(fov, aspectRatio, 0.1f, 1000.0f);
    }

    public Vector3f getPosition() { return position; }
    public float getYaw() { return yaw; }
    public float getPitch() { return pitch; }
}