package com.theohenno.narc_mod.networking;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public interface NetworkMessageEmitter {
    default void sendMessage(NetworkMessage message) {
        if (getWorld().isClient) {
            return;
        }

        getWorld().getEntitiesByClass(
                        Entity.class,
                        getBoundingBox().expand(20),
                        entity -> entity instanceof NetworkMessageReceiver && entity != this)
                .stream().map(NetworkMessageReceiver.class::cast)
                .filter(NetworkMessageReceiver::canReceive)
                .forEach(receiver -> receiver.receiveNetworkMessage(message));
    }

    default void sendMessage(NetworkMessage message , NetworkMessageReceiver receiver) {
        if (getWorld().isClient) {
            return;
        }

        receiver.receiveNetworkMessage(message);
    }

    World getWorld();

    Box getBoundingBox();
}
