package net.minecraft.server.packs.resources;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;
import net.minecraft.resources.ResourceLocation;

public interface ResourceManager {
    Set<String> getNamespaces();
    Resource getResource(ResourceLocation location) throws IOException;
    boolean hasResource(ResourceLocation location);
    List<Resource> getResources(ResourceLocation location) throws IOException;
    Collection<ResourceLocation> listResources(String path, Predicate<String> filter);
    Stream<Object> listPacks();
}