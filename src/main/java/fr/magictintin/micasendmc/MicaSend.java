package fr.magictintin.micasendmc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;

public class MicaSend implements ModInitializer {
	public static final String MOD_ID = "micasendmc";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	protected static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	protected final static String MICASEND_URL = "https://micasend.magictintin.fr";
	protected static final String WS_URL = "wss://msws.magictintin.fr:8443";

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		// CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess,
		// environment) -> register(dispatcher));
		// ClientCommandRegistrationCallback
		CommandRegistrationCallback.EVENT.register(MSend::register);//(dispatcher,
		// registryAccess) -> {
		// dispatcher.register());
		// ClientCommandManager
		// .literal("msend")
		// .executes());
		// });
		LOGGER.info("Micasend started successfully!");

		String username = MinecraftClient.getInstance().getSession()
				.getUsername();
		Connector.sendMessage(MICASEND_URL, username, "Started minecraft", () -> {
			// ws.sendMessage("new micasend message");
			// fetchMessages();
			System.out.println("Sent information minecraft has been started...");
		});

	}

	@FunctionalInterface
	public static interface VoidCallback {
		public void execute();
	}
}
