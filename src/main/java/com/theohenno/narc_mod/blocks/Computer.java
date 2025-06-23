package com.theohenno.narc_mod.blocks;

import com.mojang.serialization.MapCodec;
import com.theohenno.narc_mod.NarcMod;
import com.theohenno.narc_mod.blocks.entities.ComputerBlockEntity;
import com.theohenno.narc_mod.entities.DroneEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class Computer extends BlockWithEntity {
    public Computer(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return createCodec(Computer::new);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ComputerBlockEntity(pos, state);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        ActionResult result = super.onUse(state, world, pos, player, hit);

        if (result.isAccepted()) {
            return result;
        }

        if (!(world.getBlockEntity(pos) instanceof ComputerBlockEntity computerBlockEntity)) {
            return result;
        }

        NarcMod.LOGGER.info("------------------------");
        world.getEntitiesByClass(DroneEntity.class, Box.from(Vec3d.of(pos)).expand(20), entity -> true)
                .forEach(entity -> {
                    if (entity instanceof DroneEntity computer) {
                        NarcMod.LOGGER.info("Drone found: {}", computer.getUuid());
                    }
                });

        return ActionResult.SUCCESS;
    }
}
