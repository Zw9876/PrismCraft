package com.mojang.blaze3d.shaders;

import com.mojang.blaze3d.platform.GlStateManager;
import java.io.IOException;

public class ProgramManager {

    public static void glUseProgram(int program) {
        GlStateManager.glUseProgram(program);
    }

    public static void releaseProgram(Effect effect) {
        effect.getFragmentProgram().close();
        effect.getVertexProgram().close();
        GlStateManager.glDeleteProgram(effect.getId());
    }

    public static int createProgram() throws IOException {
        int id = GlStateManager.glCreateProgram();
        if (id <= 0) {
            throw new IOException("Could not create shader program (returned program ID " + id + ")");
        }
        return id;
    }

    public static void linkProgram(Effect effect) throws IOException {
        effect.getFragmentProgram().attachToEffect(effect);
        effect.getVertexProgram().attachToEffect(effect);
        GlStateManager.glLinkProgram(effect.getId());
        int status = GlStateManager.glGetProgrami(effect.getId(), 35714);
        if (status == 0) {
            System.err.println("Error linking program: " + GlStateManager.glGetProgramInfoLog(effect.getId(), 32768));
        }
    }
}