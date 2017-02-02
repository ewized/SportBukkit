package org.bukkit.craftbukkit;

import java.util.UUID;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.inject.Inject;

import com.mojang.authlib.GameProfile;
import tc.oc.minecraft.api.user.OfflinePlayer;
import tc.oc.minecraft.api.user.UserSource;

public class EmptyUserSource implements UserSource {

    private final CraftServer server;

    @Inject EmptyUserSource(CraftServer server) {
        this.server = server;
    }

    @Override
    public OfflinePlayer findUser(UUID id, Predicate<OfflinePlayer> filter, Supplier<OfflinePlayer> next) {
        return new CraftOfflinePlayer(server, new GameProfile(id, null));
    }
}
