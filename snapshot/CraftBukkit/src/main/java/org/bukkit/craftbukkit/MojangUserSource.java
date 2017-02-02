package org.bukkit.craftbukkit;

import java.util.UUID;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.inject.Inject;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import org.bukkit.Server;
import tc.oc.minecraft.api.user.OfflinePlayer;
import tc.oc.minecraft.api.user.UserSource;

public class MojangUserSource implements UserSource {

    private final Server server;
    private final MinecraftSessionService sessionService;

    @Inject MojangUserSource(Server server, MinecraftSessionService sessionService) {
        this.server = server;
        this.sessionService = sessionService;
    }

    @Override
    public OfflinePlayer findUser(UUID id, Predicate<OfflinePlayer> filter, Supplier<OfflinePlayer> next) {
        return new CraftOfflinePlayer((CraftServer) server, sessionService.fillProfileProperties(new GameProfile(id, null), true));
    }
}
