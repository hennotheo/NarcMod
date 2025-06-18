package com.theohenno.narcmod.entities;

import com.theohenno.narcmod.NarcMod;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities
{
    public static final EntityType<MissileEntity> MISSILE = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of("narcmod", "missile"),
            EntityType.Builder.create(MissileEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.75f, 0.75f)
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(NarcMod.MOD_ID, "missile")))
    );

    public static void initialize()
    {
        FabricDefaultAttributeRegistry.register(MISSILE, MissileEntity.createMobAttributes());
    }
}
