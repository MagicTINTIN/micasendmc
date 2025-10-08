package fr.magictintin.micasendmc;

import org.slf4j.LoggerFactory;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;

// import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.MessageArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
// import net.minecraft.command.argument.ColorArgumentType;
// import net.minecraft.network.message.MessageType;
// import net.minecraft.text.Text;
// import net.minecraft.util.Formatting;

public final class MSend {

  public static void register(CommandDispatcher<ServerCommandSource> dispatcher,
      CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment _unused) {
    dispatcher.register(CommandManager.literal("msend")
        .then(CommandManager.argument("message", MessageArgumentType.message())
            .executes(context -> MSend.execute(context.getSource().getName(),
                MessageArgumentType.getMessage(context, "message").getString()))));
  }

  private static int execute(String username, String message) {
    MicaSend.LOGGER.warn(username + " sent '" + message + "'");
    Connector.sendMessage(MicaSend.MICASEND_URL, username, message, () -> {
			// ws.sendMessage("new micasend message");
			// fetchMessages();
      MicaSend.ws.sendMessage("new micasend message");
			System.out.println("msg sent");
		});
    return 0;
  }
}
