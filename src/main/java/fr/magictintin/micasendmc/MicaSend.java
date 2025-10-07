package fr.magictintin.micasendmc;

import java.util.ArrayList;

// import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
// import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
// import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
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
	private WebsocketClient ws;
	protected static int maxID = 0;

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

		this.ws = new WebsocketClient(WS_URL, () -> fetchMessages());
	}
	
	private synchronized void fetchMessages() {
        ArrayList<Message> list = Connector.fetchMessages(MICASEND_URL);
        for (Message msg : list) {
            if (msg.id() <= maxID)
                continue;
            System.out.println("MESSAGE: [" + msg.sender + "] " + msg.content);
            // System.out.println(" MICASEND (" + i + ") >>> [" + msg.sender + "]: " + msg.content.replace("§", " "));
        }
    }
	@FunctionalInterface
	public static interface VoidCallback {
		public void execute();
	}
}
