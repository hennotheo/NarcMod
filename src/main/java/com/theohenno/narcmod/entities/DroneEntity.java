package com.theohenno.narcmod.entities;

import com.theohenno.narcmod.NarcMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.InventoryOwner;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.VillagerData;
import net.minecraft.world.World;

public class DroneEntity extends PathAwareEntity implements InventoryOwner {
    private final SimpleInventory inventory = new SimpleInventory(8);

    public DroneEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);

        Vec3d target = new Vec3d(0, 0, 0);
        getNavigation().startMovingTo(target.x, target.y, target.z, speed);
    }

    public static DefaultAttributeContainer.Builder createDroneAttributes() {
        return AnimalEntity.createAnimalAttributes()
                .add(EntityAttributes.MAX_HEALTH, 10.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.25);
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        ActionResult result = super.interactMob(player, hand);
        if (result.isAccepted()) {
            return result;
        }

        if (!getWorld().isClient) {
            player.sendMessage(Text.literal("🚀 Clic droit capturé dans AIBotEntity !"), true);
        }

        return ActionResult.SUCCESS;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new MoveToPointGoal(this, new Vec3d(0, 0, 0), 1.0));
//        this.goalSelector.add(1, new FlyGoal(this, 2));
//        this.goalSelector.add(2, new LookAroundGoal(this));
    }

    @Override
    public SimpleInventory getInventory() {
        return this.inventory;
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
//        if (!this.getWorld().isClient) {
//
//            TradeOfferList tradeOfferList = this.getOffers();
//            if (!tradeOfferList.isEmpty()) {
//                nbt.put("Offers", TradeOfferList.CODEC, this.getRegistryManager().getOps(NbtOps.INSTANCE), tradeOfferList);
//            }
//        }

        this.writeInventory(nbt, this.getRegistryManager());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
//        this.offers = (TradeOfferList)nbt.get("Offers", TradeOfferList.CODEC, this.getRegistryManager().getOps(NbtOps.INSTANCE)).orElse((Object)null);
        this.readInventory(nbt, this.getRegistryManager());
    }
}