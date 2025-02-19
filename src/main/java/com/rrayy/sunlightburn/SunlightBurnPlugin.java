package com.rrayy.sunlightburn;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SunlightBurnPlugin extends JavaPlugin{

    @Override
    public void onEnable() {
        // Schedule a repeating task to check if players are in sunlight
        new SunlightBurnTask().runTaskTimer(this, 0L, 20L); // Runs every second (20 ticks)
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private class SunlightBurnTask extends BukkitRunnable {
        @Override
        public void run() {
            for (World world : Bukkit.getWorlds()) {
                if (world.getEnvironment() == World.Environment.NORMAL) { // Only affect the Overworld
                    for (Player player : world.getPlayers()) {
                        if (player.getWorld().getTime() < 12300 || player.getWorld().getTime() > 23850) { // Daytime check
                            if (player.getLocation().getBlock().getLightFromSky() == 15) { // Direct sunlight check
                                player.setFireTicks(20); // Set player on fire for 1 second
                                player.damage(6.0); // 추가 데미지 부여
                            }
                        }
                    }
                }
            }
        }
    }
}
