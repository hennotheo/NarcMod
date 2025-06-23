package com.theohenno.narc_mod.blocks.entities;

import com.theohenno.narc_mod.NarcMod;
import com.theohenno.narc_mod.blocks.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<ComputerBlockEntity> COMPUTER_BLOCK_ENTITY =
            register("counter", ComputerBlockEntity::new, ModBlocks.COMPUTER);

    public static void initialize() {
        // This method is called to ensure the block entities are registered
        // You can add any additional initialization logic here if needed
    }

    private static <T extends BlockEntity> BlockEntityType<T> register(String name, FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory, Block... blocks) {
        Identifier id = Identifier.of(NarcMod.MOD_ID, name);
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }

    private ModBlockEntities() {
        // Private constructor to prevent instantiation
    }
}
