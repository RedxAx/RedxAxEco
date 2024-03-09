package me.redxax.redxaxeco;

import org.bukkit.plugin.java.JavaPlugin;

public class RedxAxEco extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("reco").setExecutor(new RecoCommand());
    }
}