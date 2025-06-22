package com.theohenno.narcmod.screens;

import com.theohenno.narcmod.NarcMod;
import com.theohenno.narcmod.entities.DroneEntity;
import com.theohenno.narcmod.entities.DroneScreenHandler;
import com.theohenno.narcmod.entities.ModEntities;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class ModScreens {
    public static void initialize() {
//        UseEntityCallback.EVENT.register(ModScreens::onUseEntity);
        HandledScreens.register(ModEntities.DRONE_SCREEN_HANDLER, DroneScreen::new);
    }

    private static ActionResult onUseEntity(PlayerEntity player, World world, Hand hand, Entity entity, EntityHitResult hit) {
        if (entity instanceof DroneEntity drone) {
            // côté client uniquement : on affiche la GUI
            if (!world.isClient()) {
//                DroneScreenHandler screenHandler = new DroneScreenHandler(player.playerScreenHandler.syncId, player.getInventory(), drone.getInventory());
//                MinecraftClient.getInstance().setScreen(new DroneScreen(screenHandler, player.getInventory(), Text.literal("Drone Inventory")));

                NamedScreenHandlerFactory screenHandlerFactory = new NamedScreenHandlerFactory() {
                    @Override
                    public Text getDisplayName() {
                        return Text.literal("Narc Bot Controller");
                    }

                    @Override
                    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
                        // 2. C'EST ICI ! Minecraft génère un nouveau syncId et te le donne.
                        //    Tu le passes simplement à ton constructeur.
                        return new DroneScreenHandler(syncId, playerInventory);
                    }
                };

                // 3. Ouvre l'écran. Le serveur va envoyer un paquet au client avec le nouveau syncId.
                player.openHandledScreen(screenHandlerFactory);

                return ActionResult.SUCCESS;
            }
            // côté serveur : on autorise le client à gérer l'interaction
            return ActionResult.PASS;
        }
        return ActionResult.PASS;
    }
}
