package com.theohenno.narc_mod.entities;

import com.theohenno.narc_mod.NarcMod;
import com.theohenno.narc_mod.entities.goals.MoveToPointGoal;
import com.theohenno.narc_mod.entities.screen_handler.DroneScreenHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.InventoryOwner;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class DroneEntity extends PathAwareEntity implements InventoryOwner, NamedScreenHandlerFactory {
    public static final String ID = "drone";

    private float millisecondsSinceLastPing = 0.0f;
    private final SimpleInventory inventory = new SimpleInventory(27);

    public DroneEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);

        Vec3d target = new Vec3d(0, 0, 0);
        getNavigation().startMovingTo(target.x, target.y, target.z, speed);
    }

    public static DefaultAttributeContainer.Builder createDroneAttributes() {
        return AnimalEntity.createAnimalAttributes()
                .add(EntityAttributes.MAX_HEALTH, 20.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.25);
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        ActionResult result = super.interactMob(player, hand);
        if (result.isAccepted()) {
            return result;
        }

        if (!getWorld().isClient) {
            player.openHandledScreen(this);
        }

        return ActionResult.SUCCESS;
    }

    @Override
    protected void initGoals() {
        goalSelector.add(0, new MoveToPointGoal(this, new Vec3d(0, 0, 0), 1.0));
    }

    @Override
    public SimpleInventory getInventory() {
        return inventory;
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);

        writeInventory(nbt, getRegistryManager());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);

        readInventory(nbt, getRegistryManager());
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new DroneScreenHandler(syncId, playerInventory, inventory);
    }

    @Override
    public void tick() {
        super.tick();

        if(getWorld().isClient) {
            return;
        }

        millisecondsSinceLastPing += Objects.requireNonNull(getWorld().getServer()).getTickManager().getMillisPerTick();

        if (millisecondsSinceLastPing >= 2000.0f) {
            millisecondsSinceLastPing = 0.0f;
            ping();
        }
    }

    protected void sendMessage() {
        if (getWorld().isClient) {
            return;
        }

        getWorld()
                .getEntitiesByClass(DroneEntity.class, getBoundingBox().expand(20), entity -> entity != this)
                .forEach(entity -> {
                    if (entity instanceof DroneEntity drone) {
                        drone.receiveMessage("Ping: " + getUuid());
                    }
                });
    }

    protected void receiveMessage(String message) {
        NarcMod.LOGGER.info("Drone receive message: {}", message);
    }

    protected void ping() {
        NarcMod.LOGGER.info("Drone ping at position: {}", getPos());
    }
}