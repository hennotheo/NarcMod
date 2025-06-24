package com.theohenno.narc_mod.networking;

public interface NetworkMessageReceiver {
    void receiveNetworkMessage(NetworkMessage message);

    boolean canReceive();
}
