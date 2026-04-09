package net.minecraft.resources;

import net.minecraft.ResourceLocationException;

public class ResourceLocation implements Comparable<ResourceLocation> {
    protected final String namespace;
    protected final String path;

    public ResourceLocation(String namespace, String path) {
        this.namespace = namespace.isEmpty() ? "minecraft" : namespace;
        this.path = path;
    }

    public ResourceLocation(String location) {
        String[] parts = decompose(location, ':');
        this.namespace = parts[0].isEmpty() ? "minecraft" : parts[0];
        this.path = parts[1];
    }

    protected static String[] decompose(String location, char separator) {
        String[] parts = new String[]{"minecraft", location};
        int idx = location.indexOf(separator);
        if (idx >= 0) {
            parts[1] = location.substring(idx + 1);
            if (idx >= 1) {
                parts[0] = location.substring(0, idx);
            }
        }
        return parts;
    }

    public String getNamespace() { return namespace; }
    public String getPath() { return path; }

    public static ResourceLocation tryParse(String location) {
        try {
            return new ResourceLocation(location);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() { return namespace + ":" + path; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ResourceLocation)) return false;
        ResourceLocation other = (ResourceLocation) obj;
        return namespace.equals(other.namespace) && path.equals(other.path);
    }

    @Override
    public int hashCode() { return 31 * namespace.hashCode() + path.hashCode(); }

    @Override
    public int compareTo(ResourceLocation other) {
        int c = path.compareTo(other.path);
        return c != 0 ? c : namespace.compareTo(other.namespace);
    }
}