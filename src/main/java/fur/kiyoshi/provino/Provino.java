package fur.kiyoshi.provino;

import fur.kiyoshi.provino.commands.SpawnCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Provino extends JavaPlugin {


    private static Provino instance;
    public static Provino getInstance() {
        return instance;
    }


    private void initialize() {
        saveDefaultConfig();
        getConfig().options().parseComments(true);
        getConfig().options().copyDefaults(true);
        instance = this;
    }

    @SuppressWarnings("DataFlowIssue")
    private void commands() {
        getCommand("spawn").setExecutor(new SpawnCommand());
    }

    @Override
    public void onEnable() {
        initialize();
        commands();
    }
}
