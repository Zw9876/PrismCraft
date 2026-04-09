package com.eaglercraft;

import com.eaglercraft.js.ImageLoader;
import com.eaglercraft.js.WebGL2RenderingContext;
import org.teavm.jso.JSObject;

public class Texture {
    private final WebGL2RenderingContext gl;
    private JSObject textureId;
    private boolean loaded = false;

    public Texture(WebGL2RenderingContext gl) {
        this.gl = gl;
    }

    public void load(String src) {
        textureId = gl.createTexture();
        gl.bindTexture(gl.getTexture2D(), textureId);
        ImageLoader.load(src, image -> {
            gl.bindTexture(gl.getTexture2D(), textureId);
            gl.texImage2D(gl.getTexture2D(), 0, gl.getRGBA(), gl.getRGBA(), gl.getUnsignedByte(), image);
            gl.texParameteri(gl.getTexture2D(), gl.getTextureMinFilter(), gl.getNearest());
            gl.texParameteri(gl.getTexture2D(), gl.getTextureMagFilter(), gl.getNearest());
            gl.generateMipmap(gl.getTexture2D());
            loaded = true;
            System.out.println("Texture loaded: " + src);
        });
    }

    public void loadFromBytes(byte[] data, String debugName) {
        textureId = gl.createTexture();
        gl.bindTexture(gl.getTexture2D(), textureId);
        com.eaglercraft.bridge.GLBridge.loadImageFromBytes(data, image -> {
            gl.bindTexture(gl.getTexture2D(), textureId);
            gl.texImage2D(gl.getTexture2D(), 0, gl.getRGBA(), gl.getRGBA(), gl.getUnsignedByte(), image);
            gl.texParameteri(gl.getTexture2D(), gl.getTextureMinFilter(), gl.getNearest());
            gl.texParameteri(gl.getTexture2D(), gl.getTextureMagFilter(), gl.getNearest());
            gl.generateMipmap(gl.getTexture2D());
            loaded = true;
            System.out.println("Texture loaded from EPK: " + debugName);
        });
    }

    public void bind(int unit) {
        gl.activeTexture(gl.getTexture0() + unit);
        gl.bindTexture(gl.getTexture2D(), textureId);
    }

    public boolean isLoaded() {
        return loaded;
    }
}