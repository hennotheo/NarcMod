package com.theohenno.narc_mod.screens;

import com.theohenno.narc_mod.entities.ModEntities;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class ModScreens {
    public static void initialize() {
        HandledScreens.register(ModEntities.DRONE_SCREEN_HANDLER, DroneScreen::new);
    }

    private ModScreens() {
    }
}
