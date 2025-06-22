package com.theohenno.narc_mod.entities.screen_handler;

import com.theohenno.narc_mod.entities.ModEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class DroneScreenHandler extends ScreenHandler {
    private static final int INVENTORY_SIZE = 27;
    private final Inventory inventory;

    public DroneScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(INVENTORY_SIZE));
    }

    public DroneScreenHandler(int syncId, PlayerInventory playerInventory, Inventory droneInventory) {
        super(ModEntities.DRONE_SCREEN_HANDLER, syncId);
        checkSize(droneInventory, INVENTORY_SIZE);
        this.inventory = droneInventory;
        droneInventory.onOpen(playerInventory.player);

        this.addSlot(new Slot(droneInventory, 0, 152, 8));
//        for (int j = 0; j < 3; ++j) {
//            for (int i = 0; i < 9; ++i) {
//                this.addSlot(new Slot(droneInventory, i + j * 9, 8 + i * 18, 18 + j * 18));
//            }
//        }

        int left = 8;
        int top = 84;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + (i + 1) * 9, left + j * 18, top + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, left + i * 18, top + 58));
        }
    }

    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = this.slots.get(slot);
        if (slot2.hasStack()) {
            ItemStack itemStack2 = slot2.getStack();
            itemStack = itemStack2.copy();
            if (slot < this.inventory.size()) {
                if (!this.insertItem(itemStack2, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(itemStack2, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot2.setStack(ItemStack.EMPTY);
            } else {
                slot2.markDirty();
            }
        }

        return itemStack;
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.inventory.onClose(player);
    }
}
