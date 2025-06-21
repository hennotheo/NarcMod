package com.theohenno.narcmod.screens;

import com.theohenno.narcmod.entities.DroneEntity;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.ShulkerBoxScreenHandler;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class ModScreens {
    public static final ScreenHandlerType<DroneScreenHandler> DRONE = register("drone", DroneScreenHandler::new);

    public static void initialize() {
        UseEntityCallback.EVENT.register(ModScreens::onUseEntity);
    }

    private static ActionResult onUseEntity(PlayerEntity player, World world, Hand hand, Entity entity, EntityHitResult hit) {
        if (entity instanceof DroneEntity drone) {
            // côté client uniquement : on affiche la GUI
            if (world.isClient()) {
                MinecraftClient.getInstance().setScreen(new DroneScreen(player, drone));
                return ActionResult.SUCCESS;
            }
            // côté serveur : on autorise le client à gérer l'interaction
            return ActionResult.PASS;
        }
        return ActionResult.PASS;
    }

    private static <T extends ScreenHandler> ScreenHandlerType<T> register(String id, ScreenHandlerType.Factory<T> factory) {
        return Registry.register(Registries.SCREEN_HANDLER, Identifier.of("narcmod", id), new ScreenHandlerType<>(factory, FeatureFlags.VANILLA_FEATURES));
    }
}
