package fr.magictintin.micasendmc;

import net.fabricmc.api.ModInitializer;

import javax.swing.text.html.parser.Entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MicaSend implements ModInitializer {
	public static final String MOD_ID = "micasendmc";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private final static String MICASEND_URL = "https://micasend.magictintin.fr";
    private static final String WS_URL = "wss://msws.magictintin.fr:8443";
    

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Micasend started successfully!");
		Connector.sendMessage(MICASEND_URL, "Someone", "Started minecraft", () -> {
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