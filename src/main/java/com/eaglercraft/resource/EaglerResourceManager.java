package com.eaglercraft.resource;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class EaglerResourceManager implements ResourceManager {

    private static EaglerResourceManager instance;
    private final Map<String, byte[]> resources = new HashMap<>();

    public static EaglerResourceManager getInstance() {
        if (instance == null) instance = new EaglerResourceManager();
        return instance;
    }

    public void putResource(String path, byte[] data) {
        resources.put(path, data);
    }

    public boolean hasRawResource(String path) {
        return resources.containsKey(path);
    }

    public byte[] getRawResource(String path) {
        return resources.get(path);
    }

    private String toPath(ResourceLocation location) {
        return location.getNamespace() + "/" + location.getPath();
    }

    @Override
    public Set<String> getNamespaces() {
        Set<String> namespaces = new HashSet<>();
        namespaces.add("minecraft");
        return namespaces;
    }

    @Override
    public Resource getResource(ResourceLocation location) throws IOException {
        String path = toPath(location);
        byte[] data = resources.get(path);
        if (data == null) {
            throw new FileNotFoundException("Resource not found: " + path);
        }
        return new EaglerResource(location, data);
    }

    @Override
    public boolean hasResource(ResourceLocation location) {
        return resources.containsKey(toPath(location));
    }

    @Override
    public List<Resource> getResources(ResourceLocation location) throws IOException {
        List<Resource> list = new ArrayList<>();
        if (hasResource(location)) {
            list.add(getResource(location));
        }
        return list;
    }

    @Override
    public Collection<ResourceLocation> listResources(String path, Predicate<String> filter) {
        List<ResourceLocation> result = new ArrayList<>();
        for (String key : resources.keySet()) {
            if (key.contains(path)) {
                String[] parts = key.split("/", 2);
                if (parts.length == 2 && filter.test(parts[1])) {
                    result.add(new ResourceLocation(parts[0], parts[1]));
                }
            }
        }
        return result;
    }

    @Override
    public Stream<Object> listPacks() {
        return Stream.empty();
    }

    // Inner class for resource entries
    public static class EaglerResource implements Resource {
        private final ResourceLocation location;
        private final byte[] data;

        public EaglerResource(ResourceLocation location, byte[] data) {
            this.location = location;
            this.data = data;
        }

        @Override
        public ResourceLocation getLocation() { return location; }

        @Override
        public InputStream getInputStream() {
            return new ByteArrayInputStream(data);
        }

        @Override
        public <T> T getMetadata(Object serializer) { return null; }

        @Override
        public String getSourceName() { return "eaglercraft-epk"; }

        @Override
        public void close() throws IOException {}
    }
}