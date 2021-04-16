package io.neutrino.economy;

import io.neutrino.Neutrino;
import io.neutrino.economy.commands.BalanceCommand;
import io.neutrino.economy.commands.CurrencyCommand;
import io.neutrino.economy.models.Currency;
import io.neutrino.economy.models.Transaction;
import io.neutrino.economy.models.Wallet;
import org.bukkit.plugin.java.JavaPlugin;

public class Economy extends JavaPlugin {

    private static Economy instance;

    @Override
    public void onEnable() {
        setInstance(this);
        registerCommands();
        setupDatabase();
        Neutrino.getInstance().logSuccess("Economy plugin enabled");
    }

    @Override
    public void onDisable() {
        Neutrino.getInstance().logSuccess("Economy plugin disabled");
        setInstance(null);
    }

    public static Economy getInstance() {
        return instance;
    }

    public static void setInstance(Economy instance) {
        Economy.instance = instance;
    }

    public Neutrino getNeutrino() {
        return Neutrino.getInstance();
    }

    private void setupDatabase() {
        getNeutrino().log("Setting up database for Economy addon...");
        getNeutrino().getDatabase().registerModel(new Currency());
        getNeutrino().getDatabase().registerModel(new Wallet());
        getNeutrino().getDatabase().registerModel(new Transaction());
    }

    private void registerCommands() {
        getCommand("balance").setExecutor(new BalanceCommand());
        getCommand("currency").setExecutor(new CurrencyCommand());
    }
}
