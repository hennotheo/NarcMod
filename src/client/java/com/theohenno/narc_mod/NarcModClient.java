package com.theohenno.narc_mod;

import com.theohenno.narc_mod.entities.DroneEntityClient;
import com.theohenno.narc_mod.screens.ModScreens;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class NarcModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModScreens.initialize();
		DroneEntityClient.initialize();
	}
}