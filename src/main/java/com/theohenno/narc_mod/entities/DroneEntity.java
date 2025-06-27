package com.theohenno.narc_mod.entities;

import com.theohenno.narc_mod.NarcMod;
import com.theohenno.narc_mod.entities.goals.SoftwareTask;
import com.theohenno.narc_mod.entities.goals.SoftwareTasksGoal;
import com.theohenno.narc_mod.entities.screen_handler.DroneScreenHandler;
import com.theohenno.narc_mod.items.ModItems;
import com.theohenno.narc_mod.networking.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.InventoryOwner;
import net.minecraft.entity.ai.goal.PrioritizedGoal;
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
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

public class DroneEntity extends PathAwareEntity implements InventoryOwner, NamedScreenHandlerFactory, NetworkMessageEmitter, NetworkMessageReceiver {
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

        if (player.getStackInHand(hand).isOf(ModItems.ADMIN_TEST_ITEM)) {
            return ActionResult.PASS;
        }

        if (!getWorld().isClient) {
            player.openHandledScreen(this);
        }

        return ActionResult.SUCCESS;
    }

    @Override
    protected void initGoals() {
        goalSelector.add(0, new SoftwareTasksGoal(this));
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

        if (getWorld().isClient) {
            return;
        }

        millisecondsSinceLastPing += Objects.requireNonNull(getWorld().getServer()).getTickManager().getMillisPerTick();

        if (millisecondsSinceLastPing >= 15000.0f) {
            millisecondsSinceLastPing = 0.0f;
            ping();
        }
    }

    protected void ping() {
        sendMessage(new NetworkMessage(
                NetworkMessageType.PING,
                0,
                this,
                "Ping",
                "Drone ping at position: " + getPos())
        );
    }

    @Override
    public void receiveNetworkMessage(NetworkMessage message) {
        if (!(getWorld() instanceof ServerWorld)) {
            return;
        }

        if (message.Type == NetworkMessageType.PING) {
            NarcMod.LOGGER.info("Drone received ping from: {}", message.Emitter);
            return;
        }

        if (message.Type == NetworkMessageType.ORDER) {
            if (message instanceof NetworkTaskMessage taskMessage) {
                addTask(taskMessage.Task);
                return;
            }

            NarcMod.LOGGER.error("Received ORDER message but it's not a NetworkTaskMessage: {}", message);
        }
    }

    @Override
    public boolean canReceive() {
        return true;
    }

    private void addTask(SoftwareTask task) {
        Optional<PrioritizedGoal> goalHandler = goalSelector.getGoals().stream()
                .filter(goal -> goal.getGoal() instanceof SoftwareTasksGoal)
                .findAny();

        if (goalHandler.isEmpty())
            return;

        if (!(goalHandler.get().getGoal() instanceof SoftwareTasksGoal softwareTasksGoal))
            return;

        task.Entity = this;
        softwareTasksGoal.addTask(task);
    }
}