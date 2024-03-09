package me.redxax.redxaxeco;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.clip.placeholderapi.PlaceholderAPI;

import java.util.Arrays;

public class RecoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length >= 3) {
                String placeholder = args[0];
                double value = Double.parseDouble(args[1]);
                String cmd = String.join(" ", Arrays.copyOfRange(args, 2, args.length));

                String placeholderValue = PlaceholderAPI.setPlaceholders(player, placeholder);

                if (isNumeric(placeholderValue) && Double.parseDouble(placeholderValue) >= value) {
                    String[] commands = cmd.split("\\|");
                    for (String singleCommand : commands) {
                        if (!singleCommand.startsWith("#") || !singleCommand.endsWith("#")) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), singleCommand.trim().replace("%playername%", player.getName()));
                        }
                    }
                } else {
                    int lastPipeIndex = cmd.lastIndexOf("|");
                    if (lastPipeIndex != -1) {
                        String customMessage = cmd.substring(lastPipeIndex + 1).trim();
                        if (customMessage.startsWith("#") && customMessage.endsWith("#")) {
                            String coloredMessage = ChatColor.translateAlternateColorCodes('&', customMessage.substring(1, customMessage.length() - 1));
                            player.sendMessage(coloredMessage);
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}