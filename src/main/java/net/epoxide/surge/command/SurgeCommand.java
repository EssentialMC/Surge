package net.epoxide.surge.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public interface SurgeCommand {
    
    /**
     * Gets the name of the surge sub command being represented.
     * 
     * @return The name of the surge sub command.
     */
    String getSubName ();
    
    /**
     * Gets a usage string for the surge sub command.
     * 
     * @return The usage string for the surge sub command.
     */
    String getUsage ();
    
    /**
     * Handles execution of the surge sub command.
     * 
     * @param server Instance of the server. Null when the player is on client.
     * @param sender The player sending the command.
     * @param args The arguments for the command.
     */
    void execute (MinecraftServer server, ICommandSender sender, String[] args);
}
