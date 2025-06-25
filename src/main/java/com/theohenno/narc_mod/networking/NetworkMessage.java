package com.theohenno.narc_mod.networking;

import com.theohenno.narc_mod.entities.DroneEntity;

public class NetworkMessage {
    public NetworkMessageType Type;
    public int ChannelId;

    public DroneEntity Sender;

    public String Header;
    public String Body;

    public NetworkMessage(NetworkMessageType type, int channelId, DroneEntity sender, String header, String body) {
        this.Type = type;
        this.ChannelId = channelId;
        this.Sender = sender;
        this.Header = header;
        this.Body = body;
    }
}
