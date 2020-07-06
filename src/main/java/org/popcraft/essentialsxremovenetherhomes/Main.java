package org.popcraft.essentialsxremovenetherhomes;

import com.earth2me.essentials.IEssentials;
import com.earth2me.essentials.User;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        IEssentials essentials = (IEssentials) this.getServer().getPluginManager().getPlugin("Essentials");
        if (essentials == null) {
            return;
        }
        for (OfflinePlayer player : this.getServer().getOfflinePlayers()) {
            User user = essentials.getUser(player.getUniqueId());
            Location logout = user.getLogoutLocation(), last = user.getLastLocation();
            Location spawn = this.getServer().getWorlds().get(0).getSpawnLocation();
            if (logout.getWorld() != null && World.Environment.NETHER.equals(logout.getWorld().getEnvironment())) {
                user.setLogoutLocation(spawn);
            }
            if (last.getWorld() != null && World.Environment.NETHER.equals(last.getWorld().getEnvironment())) {
                user.setLastLocation(spawn);
            }
            for (String homeName : user.getHomes()) {
                try {
                    Location home = user.getHome(homeName);
                    if (home.getWorld() != null && World.Environment.NETHER.equals(home.getWorld().getEnvironment())) {
                        user.delHome(homeName);
                    }
                } catch (Exception ignored) {
                }
            }
        }
    }
}
