package com.eaglercraft;

import com.eaglercraft.js.Browser;
import com.eaglercraft.js.HTMLCanvas;
import com.eaglercraft.js.WebGL2RenderingContext;
import com.eaglercraft.resource.ResourceManager;
import org.teavm.jso.JSObject;

public class Main {
    public static void main(String[] args) {
        System.out.println("Eaglercraft 1.16 - initializing...");

        HTMLCanvas canvas = Browser.getDocument().getElementById("gameCanvas");
        if (canvas == null) {
            System.out.println("ERROR: Could not find canvas element!");
            return;
        }

        System.out.println("Canvas found: " + canvas.getWidth() + "x" + canvas.getHeight());

        JSObject contextObj = canvas.getContext("webgl2");
        if (contextObj == null) {
            System.out.println("ERROR: Could not get WebGL2 context!");
            return;
        }

        WebGL2RenderingContext gl = (WebGL2RenderingContext) contextObj;
        InputHandler input = new InputHandler(canvas);
        GameLoop gameLoop = new GameLoop(canvas, gl, input);

        ResourceManager.loadEPK("assets.epk", () -> {
            System.out.println("Assets loaded, starting game...");
            gameLoop.start();
        });
    }
}