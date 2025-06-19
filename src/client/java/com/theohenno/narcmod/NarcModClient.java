package com.theohenno.narcmod;

import com.theohenno.narcmod.entities.client.DroneEntityClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class NarcModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		DroneEntityClient.initialize();
	}
}