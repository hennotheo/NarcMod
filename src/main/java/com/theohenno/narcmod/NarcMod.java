package com.theohenno.narcmod;

import net.fabricmc.api.ModInitializer;

import net.minecraft.block.Block;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NarcMod implements ModInitializer {
    public static final String MOD_ID = "narcmod";

    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        com.theohenno.narcmod.items.ModItems.initialize();
        com.theohenno.narcmod.blocks.ModBlocks.initialize();
        com.theohenno.narcmod.entities.ModEntities.initialize();

        LOGGER.info("Hello Fabric world!");
    }
}