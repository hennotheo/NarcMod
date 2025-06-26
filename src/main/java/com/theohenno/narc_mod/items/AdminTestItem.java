package com.theohenno.narc_mod.items;

import com.theohenno.narc_mod.NarcMod;
import com.theohenno.narc_mod.entities.DroneEntity;
import com.theohenno.narc_mod.networking.NetworkMessage;
import com.theohenno.narc_mod.networking.NetworkMessageEmitter;
import com.theohenno.narc_mod.networking.NetworkMessageReceiver;
import com.theohenno.narc_mod.networking.NetworkMessageType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class AdminTestItem extends Item implements NetworkMessageEmitter {
    private World currentWorld;
    private NetworkMessageReceiver networkMessageReceiver;

    public AdminTestItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (networkMessageReceiver != null) {
            currentWorld = context.getWorld();
            sendMessage(
                    new NetworkMessage(
                            NetworkMessageType.ORDER,
                            0,
                            this,
                            "GOTO " + context.getHitPos().toString(),
                            "BODY"),
                    networkMessageReceiver);
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (entity instanceof NetworkMessageReceiver target) {
            currentWorld = user.getWorld();
            sendMessage(
                    new NetworkMessage(
                            NetworkMessageType.PING,
                            0,
                            this,
                            "AdminTestItem",
                            "Bind to admin Item of {0}" + user.getGameProfile().getName()),
                    target);
            networkMessageReceiver = target;
            return ActionResult.SUCCESS;
        }

        return super.useOnEntity(stack, user, entity, hand);
    }

    @Override
    public World getWorld() {
        return currentWorld;
    }

    @Override
    public Box getBoundingBox() {
        return null;
    }
}
