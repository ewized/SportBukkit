package org.bukkit.craftbukkit;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import net.minecraft.server.MinecraftServer;
import org.bukkit.Server;
import org.bukkit.ServerModule;
import tc.oc.minecraft.api.user.UserSourceBinder;

public class CraftServerModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ServerModule());

        final UserSourceBinder sources = new UserSourceBinder(binder());
        sources.addBinding().to(MojangUserSource.class);
        sources.addBinding().to(EmptyUserSource.class);
    }

    @Provides
    CraftServer craftServer(Server server) {
        return (CraftServer) server;
    }

    @Provides
    MinecraftServer minecraftServer(CraftServer craftServer) {
        return craftServer.getServer();
    }

    @Provides
    MinecraftSessionService minecraftSessionService(MinecraftServer minecraftServer) {
        return minecraftServer.az();
    }
}
