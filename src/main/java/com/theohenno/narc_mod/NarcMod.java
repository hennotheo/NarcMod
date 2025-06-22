package com.theohenno.narc_mod;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NarcMod implements ModInitializer {
    public static final String MOD_ID = "narc_mod";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        com.theohenno.narc_mod.items.ModItems.initialize();
        com.theohenno.narc_mod.blocks.ModBlocks.initialize();
        com.theohenno.narc_mod.entities.ModEntities.initialize();

        LOGGER.info("Narc Mod initialized successfully!");
    }
}