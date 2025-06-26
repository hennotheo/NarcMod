package com.theohenno.narc_mod.networking;

import com.theohenno.narc_mod.entities.goals.SoftwareTask;

public class NetworkTaskMessage extends NetworkMessage {
    public SoftwareTask Task;

    public NetworkTaskMessage(SoftwareTask task, int channelId, NetworkMessageEmitter emitter) {
        super(NetworkMessageType.ORDER,
                channelId,
                emitter,
                task.getClass().getSimpleName(),
                task.toString());
        Task = task;
    }
}
