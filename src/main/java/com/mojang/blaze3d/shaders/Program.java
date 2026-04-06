package com.mojang.blaze3d.shaders;

import java.util.HashMap;
import com.mojang.blaze3d.platform.GlStateManager;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Program {
    private final Program.Type type;
    private final String name;
    private final int id;
    private int references;

    private Program(Program.Type type, int id, String name) {
        this.type = type;
        this.id = id;
        this.name = name;
    }

    public void attachToEffect(Effect effect) {
        this.references++;
        GlStateManager.glAttachShader(effect.getId(), this.id);
    }

    public void close() {
        this.references--;
        if (this.references <= 0) {
            GlStateManager.glDeleteShader(this.id);
            this.type.getPrograms().remove(this.name);
        }
    }

    public String getName() { return this.name; }

    public static Program compileShader(Program.Type type, String name, InputStream stream, String sourceName) throws IOException {
        String source = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        int shaderId = GlStateManager.glCreateShader(type.getGlType());
        GlStateManager.glShaderSource(shaderId, source);
        GlStateManager.glCompileShader(shaderId);
        if (GlStateManager.glGetShaderi(shaderId, 35713) == 0) {
            String log = GlStateManager.glGetShaderInfoLog(shaderId, 32768);
            throw new IOException("Couldn't compile " + type.getName() + " program (" + sourceName + ", " + name + ") : " + log);
        }
        Program program = new Program(type, shaderId, name);
        type.getPrograms().put(name, program);
        return program;
    }

    public static enum Type {
        VERTEX("vertex", ".vsh", 35633),
        FRAGMENT("fragment", ".fsh", 35632);

        private final String name;
        private final String extension;
        private final int glType;
        private final Map<String, Program> programs = new HashMap<>();

        private Type(String name, String extension, int glType) {
            this.name = name;
            this.extension = extension;
            this.glType = glType;
        }

        public String getName() { return this.name; }
        public String getExtension() { return this.extension; }
        public int getGlType() { return this.glType; }
        public Map<String, Program> getPrograms() { return this.programs; }
    }
}