package io.neutrino.economy;

import io.neutrino.Neutrino;
import io.neutrino.model.NeutrinoProfile;
import models.Currency;
import models.Transaction;
import models.Wallet;
import org.bukkit.plugin.java.JavaPlugin;

public class Economy extends JavaPlugin {

    private static Economy instance;

    @Override
    public void onEnable() {
        setInstance(this);
        setupDatabase();
        Neutrino.getInstance().logSuccess("Economy plugin enabled");
        getNeutrino().log(getNeutrino().getDatabase().isConnected() + "");
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

    private Neutrino getNeutrino() {
        return Neutrino.getInstance();
    }

    private void setupDatabase() {
        getNeutrino().log("Setting up database for Economy addon...");
        getNeutrino().getDatabase().registerModel(new Currency());
        getNeutrino().getDatabase().registerModel(new Wallet());
        getNeutrino().getDatabase().registerModel(new Transaction());
    }
}
