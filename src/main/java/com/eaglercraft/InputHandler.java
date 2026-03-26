package com.eaglercraft;

import com.eaglercraft.js.Browser;
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

    public InputHandler() {
        Browser.getWindow().addEventListener("keydown", this::onKeyDown);
        Browser.getWindow().addEventListener("keyup", this::onKeyUp);
        Browser.getWindow().addEventListener("mousemove", this::onMouseMove);
    }

    private void onKeyDown(KeyboardEvent event) {
        keysDown.add(event.getCode());
        System.out.println("Key down: " + event.getCode());
    }

    private void onKeyUp(KeyboardEvent event) {
        keysDown.remove(event.getCode());
        System.out.println("Key up: " + event.getCode());
    }

    private void onMouseMove(MouseEvent event) {
        mouseX = event.getClientX();
        mouseY = event.getClientY();
        mouseDeltaX = event.getMovementX();
        mouseDeltaY = event.getMovementY();
    }

    public boolean isKeyDown(String code) {
        return keysDown.contains(code);
    }

    public double getMouseX() { return mouseX; }
    public double getMouseY() { return mouseY; }
    public double getMouseDeltaX() { return mouseDeltaX; }
    public double getMouseDeltaY() { return mouseDeltaY; }
}