package com.eaglercraft;

import com.eaglercraft.js.Browser;
import com.eaglercraft.js.HTMLCanvas;
import com.eaglercraft.js.KeyboardEvent;
import com.eaglercraft.js.MouseEvent;

import java.util.HashSet;
import java.util.Set;

public class InputHandler {
    private final Set<String> keysDown = new HashSet<>();
    private double mouseX = 0;
    private double mouseY = 0;
    private double mouseDeltaX = 0;
    private double mouseDeltaY = 0;
    private boolean pointerLocked = false;
    private final HTMLCanvas canvas;

    public InputHandler(HTMLCanvas canvas) {
        this.canvas = canvas;
        Browser.getWindow().addEventListener("keydown", this::onKeyDown);
        Browser.getWindow().addEventListener("keyup", this::onKeyUp);
        Browser.getWindow().addEventListener("mousemove", this::onMouseMove);
        Browser.addDocumentEventListener("pointerlockchange", this::onPointerLockChange);
        canvas.addEventListener("click", this::onCanvasClick);
    }

    private void onKeyDown(KeyboardEvent event) {
        keysDown.add(event.getCode());
    }

    private void onKeyUp(KeyboardEvent event) {
        keysDown.remove(event.getCode());
    }

    private void onMouseMove(MouseEvent event) {
        mouseX = event.getClientX();
        mouseY = event.getClientY();
        if (pointerLocked) {
            mouseDeltaX = event.getMovementX();
            mouseDeltaY = event.getMovementY();
        } else {
            mouseDeltaX = 0;
            mouseDeltaY = 0;
        }
    }

    private void onPointerLockChange() {
        pointerLocked = Browser.isPointerLocked();
        System.out.println("Pointer lock: " + pointerLocked);
    }

    private void onCanvasClick() {
        if (!pointerLocked) {
            canvas.requestPointerLock();
        }
    }

    public void consumeMouseDelta() {
        mouseDeltaX = 0;
        mouseDeltaY = 0;
    }

    public boolean isKeyDown(String code) { return keysDown.contains(code); }
    public double getMouseX() { return mouseX; }
    public double getMouseY() { return mouseY; }
    public double getMouseDeltaX() { return mouseDeltaX; }
    public double getMouseDeltaY() { return mouseDeltaY; }
    public boolean isPointerLocked() { return pointerLocked; }
}