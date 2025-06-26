package com.theohenno.narc_mod.networking;

import com.theohenno.narc_mod.entities.goals.SoftwareTask;

public class NetworkMessage {
    public NetworkMessageType Type;
    public int ChannelId;

    public NetworkMessageEmitter Emitter;

    public String Header;
    public String Body;

    public NetworkMessage(NetworkMessageType type, int channelId, NetworkMessageEmitter emitter, String header, String body) {
        this.Type = type;
        this.ChannelId = channelId;
        this.Emitter = emitter;
        this.Header = header;
        this.Body = body;
    }
}
