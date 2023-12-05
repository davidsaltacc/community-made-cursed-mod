package net.justacoder.communitymade;

import net.fabricmc.api.ModInitializer;

public abstract class FabricInitializer implements ModInitializer {

	@Override
	public void onInitialize() {
		Main.initialize();
	}
}