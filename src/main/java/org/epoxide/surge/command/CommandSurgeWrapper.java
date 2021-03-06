package org.epoxide.surge.command;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import org.epoxide.surge.libs.TextUtils.ChatFormat;

import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class CommandSurgeWrapper extends CommandBase {

    /**
     * Map of sub commands that are available through the command wrapper.
     */
    private static Map<String, SurgeCommand> subCommands = new HashMap<>();

    /**
     * Adds a sub command to the surge command wrapper.
     *
     * @param command The surge sub command to register.
     */
    public static void addCommand (SurgeCommand command) {

        subCommands.put(command.getSubName(), command);
    }

    @Override
    public String getName () {

        return "surge";
    }

    @Override
    public String getUsage (ICommandSender sender) {

        return "command.surge.usage";
    }

    @Override
    public int getRequiredPermissionLevel () {

        return 0;
    }

    @Override
    public void execute (MinecraftServer server, ICommandSender sender, String[] args) {

        if (args.length > 0 && subCommands.containsKey(args[0]))
            subCommands.get(args[0]).execute(sender, Arrays.copyOfRange(args, 1, args.length));

        else
            sender.sendMessage(new TextComponentString(this.getSubCommandDescriptions()));
    }

    /**
     * Creates a string containing the description of all registered sub commands for surge.
     *
     * @return A string that contains descriptions for all sub commands for surge.
     */
    private String getSubCommandDescriptions () {

        final StringBuilder builder = new StringBuilder(I18n.format("command.surge.usage"));
        subCommands.values().forEach(command -> builder.append("\n" + ChatFormat.GREEN + "/surge " + command.getUsage() + ChatFormat.RESET + " - " + command.getDescription()));
        return builder.toString();
    }

    @Override
    public List<String> getTabCompletions (MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos) {

        if (args.length == 1) {
            final Set<String> set = subCommands.keySet();
            return getListOfStringsMatchingLastWord(args, set.toArray(new String[set.size()]));
        }
        else
            return Collections.emptyList();
    }
}
