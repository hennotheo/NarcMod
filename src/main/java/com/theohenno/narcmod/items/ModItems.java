package com.theohenno.narcmod.items;

import com.theohenno.narcmod.NarcMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems
{
    public static final RegistryKey<ItemGroup> NARC_MOD_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(NarcMod.MOD_ID, "item_group"));
    public static final ItemGroup NARC_MOD_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.SUSPICIOUS_SUBSTANCE))
            .displayName(Text.translatable("itemGroup.narcmod"))
            .build();

    public static final Item SUSPICIOUS_SUBSTANCE = register("suspicious_substance", Item::new, new Item.Settings());

    public static void initialize()
    {
        Registry.register(Registries.ITEM_GROUP, NARC_MOD_ITEM_GROUP_KEY, NARC_MOD_ITEM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(NARC_MOD_ITEM_GROUP_KEY).register((itemGroup) -> {
            itemGroup.add(ModItems.SUSPICIOUS_SUBSTANCE);
        });
    }

    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings)
    {
        // Create the item key.
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NarcMod.MOD_ID, name));

        // Create the item instance.
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        // Register the item.
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

}