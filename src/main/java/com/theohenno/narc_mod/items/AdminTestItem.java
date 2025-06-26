package com.theohenno.narc_mod.items;

import com.theohenno.narc_mod.entities.goals.MoveToTask;
import com.theohenno.narc_mod.networking.*;
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
                    new NetworkTaskMessage(
                            new MoveToTask(context.getHitPos(), 0.5),
                            0,
                            this),
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
