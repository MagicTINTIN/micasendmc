package fr.magictintin.micasendmc;

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
  public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess,
      CommandManager.RegistrationEnvironment _unused) {
    dispatcher.register(CommandManager.literal("msend")
        .requires(source -> source.hasPermissionLevel(2)) // Must be a game master to use the command. Command will not
                                                          // show up in tab completion or execute to non operators or
                                                          // any operator that is permission level 1.
        // .then(CommandManager.argument("color", ColorArgumentType.color())
        .then(CommandManager.argument("message", MessageArgumentType.message()) // MessageArgumentType
            .executes(ctx -> send2MS(ctx.getSource(), MessageArgumentType.getMessage(ctx, "msg").getString()))));
  }

  public static int send2MS(ServerCommandSource source, String message) {

    System.out.println(source.getName() + " sent '" + message + "'");
    // final Text text = Text.literal(message).formatted(formatting);

    // source.getServer().getPlayerManager().broadcastChatMessage(text,
    // MessageType.CHAT, source.getPlayer().getUuid());
    return Command.SINGLE_SUCCESS; // Success
  }
}