package io.neutrino.economy.commands;

import io.neutrino.Neutrino;
import io.neutrino.economy.Economy;
import io.neutrino.economy.models.Wallet;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BalanceCommand implements CommandExecutor {

    private static final Neutrino neutrino = Economy.getInstance().getNeutrino();

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if(args.length > 1) {
            cs.sendMessage(neutrino.getPrefix() + "§cToo many arguments. Correct usage: /balance [player_name]");
            return false;
        }

        String playerName = "";
        if(args.length == 1) {
            playerName = playerName.replace("[", "").replace("]", "").trim();
        } else {
            if (cs instanceof Player) {
                playerName = cs.getName();
            } else {
                cs.sendMessage(neutrino.getPrefix() + "§cUnknown target player. If you are executing this command from console, please specify a player name.");
                return false;
            }
        }

        List<Wallet> wallets;
        Player target = Bukkit.getPlayer(playerName);
        if(target == null) {
            OfflinePlayer offPlayer = Bukkit.getOfflinePlayer(playerName);
            wallets = Wallet.getPlayerWallets(offPlayer.getUniqueId());
        } else {
            wallets = Wallet.getPlayerWallets(target);
        }

        if(wallets.size() == 0) {
            cs.sendMessage(neutrino.getPrefix() + "§aThis player does not have any active wallets.");
            return true;
        } else {
            if(wallets.size() > 1) {
                if(args.length != 0) {
                    cs.sendMessage(neutrino.getPrefix() + "§aThis player has §7" + wallets.size() + " §aactive wallets.");
                } else {
                    cs.sendMessage(neutrino.getPrefix() + "§aYou have §71ž §a active wallets.");
                }
            } else {
                if(args.length != 0) {
                    cs.sendMessage(neutrino.getPrefix() + "§aThis player has §7" + wallets.size() + " §aactive wallet.");
                } else {
                    cs.sendMessage(neutrino.getPrefix() + "§aYou have §71 §a active wallets.");
                }
            }
        }

        return true;
    }
}
