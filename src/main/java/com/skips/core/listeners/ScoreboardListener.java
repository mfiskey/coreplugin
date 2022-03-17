package com.skips.core.listeners;

import com.skips.core.main.Main;
import com.skips.core.procedures.KillStreakProcedure;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.UUID;

public class ScoreboardListener implements Listener {

    private final HashMap<UUID, Double> kdrMap = new HashMap<>();
    private final HashMap<UUID, Integer> killsMap = new HashMap<>();
    private final HashMap<UUID, Integer> deathsMap = new HashMap<>();
    public static final HashMap<UUID, Integer> killStreakMap = new HashMap<>();

    private final DecimalFormat df = new DecimalFormat("0.0");

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPlayedBefore()) {
            this.killsMap.put(player.getUniqueId(), (Integer) Main.playerStatsData.getConfig("playerStats.yml").get(player.getName() + ".kills"));
            this.deathsMap.put(player.getUniqueId(), (Integer) Main.playerStatsData.getConfig("playerStats.yml").get(player.getName() + ".deaths"));
            this.kdrMap.put(player.getUniqueId(), (Double) Main.playerStatsData.getConfig("playerStats.yml").get(player.getName() + ".kdr"));

        }
        killStreakMap.put(player.getUniqueId(), 0);

        this.killsMap.putIfAbsent(player.getUniqueId(), 0);
        this.deathsMap.putIfAbsent(player.getUniqueId(), 0);
        this.kdrMap.putIfAbsent(player.getUniqueId(), 0.0);

        // sidebar scoreboard setup (for players who have and have not played before).
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective sidebar = scoreboard.registerNewObjective("sidebar", "dummy");
        sidebar.setDisplaySlot(DisplaySlot.SIDEBAR);
        sidebar.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "KIT PVP STATS");


        // killStreakScore setup
        Team killStreakScore = scoreboard.registerNewTeam("killStreakScore");
        killStreakScore.addEntry(ChatColor.GREEN.toString());
        killStreakScore.setPrefix(ChatColor.WHITE + "Kill Streak: ");
        killStreakScore.setSuffix(ChatColor.WHITE.toString() + killStreakMap.get(player.getUniqueId()));
        sidebar.getScore(ChatColor.GREEN.toString()).setScore(6);

        // space
        Score space2 = sidebar.getScore("    ");
        space2.setScore(5);

        // killScore set up
        Team killsScore = scoreboard.registerNewTeam("killsScore");
        killsScore.addEntry(ChatColor.AQUA.toString());
        killsScore.setPrefix(ChatColor.WHITE + "Kills: ");
        killsScore.setSuffix(ChatColor.WHITE.toString() + this.killsMap.get(player.getUniqueId()));
        sidebar.getScore(ChatColor.AQUA.toString()).setScore(4);



        // deathsScore set up
        Team deathsScore = scoreboard.registerNewTeam("deathsScore");
        deathsScore.addEntry(ChatColor.BLACK.toString());
        deathsScore.setPrefix(ChatColor.WHITE + "Deaths: ");
        deathsScore.setSuffix(ChatColor.WHITE.toString() + this.deathsMap.get(player.getUniqueId()));
        sidebar.getScore(ChatColor.BLACK.toString()).setScore(3);

        // space
        Score space = sidebar.getScore(" ");
        space.setScore(2);

        // kdrScore setup
        Team kdrScore = scoreboard.registerNewTeam("kdrScore");
        kdrScore.addEntry(ChatColor.YELLOW.toString());
        kdrScore.setPrefix(ChatColor.WHITE + "KDR: ");
        kdrScore.setSuffix(ChatColor.WHITE.toString() + this.kdrMap.get(player.getUniqueId()));
        sidebar.getScore(ChatColor.YELLOW.toString()).setScore(1);

        Main.playerStatsData.getConfig("playerStats.yml").set(player.getName(), null);
        Main.playerStatsData.getConfig("playerStats.yml").set(player.getName() + ".UUID", player.getUniqueId().toString());
        Main.playerStatsData.getConfig("playerStats.yml").set(player.getName() + ".killstreak", killStreakMap.get(player.getUniqueId()));
        Main.playerStatsData.getConfig("playerStats.yml").set(player.getName() + ".kills", this.killsMap.get(player.getUniqueId()));
        Main.playerStatsData.getConfig("playerStats.yml").set(player.getName() + ".deaths", this.deathsMap.get(player.getUniqueId()));
        Main.playerStatsData.getConfig("playerStats.yml").set(player.getName() + ".kdr", this.kdrMap.get(player.getUniqueId()));
        Main.playerStatsData.saveConfig("playerStats.yml");

        player.setScoreboard(scoreboard);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.getEntity();
        Player player = event.getEntity();


        this.killsMap.put(player.getUniqueId(), (Integer) Main.playerStatsData.getConfig("playerStats.yml").get(player.getName() + ".kills"));
        this.deathsMap.put(player.getUniqueId(), (Integer) Main.playerStatsData.getConfig("playerStats.yml").get(player.getName() + ".deaths"));
        killStreakMap.put(player.getUniqueId(), (Integer) Main.playerStatsData.getConfig("playerStats.yml").get(player.getName() + ".killstreak"));
        this.kdrMap.put(player.getUniqueId(), (Double) Main.playerStatsData.getConfig("playerStats.yml").get(player.getName() + ".kdr"));

        if (player.getKiller() != null) {
            this.killsMap.put(player.getKiller().getUniqueId(), (Integer) Main.playerStatsData.getConfig("playerStats.yml").get(player.getKiller().getName() + ".kills"));
            killStreakMap.put(player.getKiller().getUniqueId(), (Integer) Main.playerStatsData.getConfig("playerStats.yml").get(player.getKiller().getName() + ".killstreak"));
            this.deathsMap.put(player.getKiller().getUniqueId(), (Integer) Main.playerStatsData.getConfig("playerStats.yml").get(player.getKiller().getName() + ".deaths"));
            this.kdrMap.put(player.getKiller().getUniqueId(), (Double) Main.playerStatsData.getConfig("playerStats.yml").get(player.getKiller().getName() + ".kdr"));

            }



        // deathsScore updater
        this.deathsMap.put(player.getUniqueId(), this.deathsMap.get(player.getUniqueId()) + 1);
        Main.playerStatsData.getConfig("playerStats.yml").set(player.getName() + ".deaths", this.deathsMap.get(player.getUniqueId()));
        player.getScoreboard().getTeam("deathsScore").setSuffix(ChatColor.WHITE.toString() +
                Main.playerStatsData.getConfig("playerStats.yml").get(player.getName() + ".deaths"));

        // killsScore updater
        if (player.getKiller() != null) {
            this.killsMap.put(player.getKiller().getUniqueId(), this.killsMap.get(player.getKiller().getUniqueId()) + 1);
            Main.playerStatsData.getConfig("playerStats.yml").set(player.getKiller().getName() + ".kills", this.killsMap.get(player.getKiller().getUniqueId()));
            player.getKiller().getScoreboard().getTeam("killsScore").setSuffix(ChatColor.WHITE.toString() +
                    Main.playerStatsData.getConfig("playerStats.yml").get(player.getKiller().getName() + ".kills"));

        }

        //kdrScore updater
        // death
        if (this.deathsMap.get(player.getUniqueId()) > 0) {
            int kills = this.killsMap.get(player.getUniqueId());
            int deaths = this.deathsMap.get(player.getUniqueId());
            double kdr = ((double) kills) / deaths;
            this.kdrMap.put(player.getUniqueId(), kdr);
            Main.playerStatsData.getConfig("playerStats.yml").set(player.getName() + ".kdr", Double.parseDouble(this.df.format(this.kdrMap.get(player.getUniqueId()))));
        }
        else {
            this.kdrMap.put(player.getUniqueId(), 0.0);
            Main.playerStatsData.getConfig("playerStats.yml").set(player.getName() + ".kdr", Double.parseDouble(this.df.format(this.kdrMap.get(player.getUniqueId()))));
        }
        player.getScoreboard().getTeam("kdrScore").setSuffix(ChatColor.WHITE.toString() +
                Main.playerStatsData.getConfig("playerStats.yml").get(player.getName() + ".kdr"));

        // kill
        if (player.getKiller() != null) {
            if (this.deathsMap.get(player.getKiller().getUniqueId()) > 0) {
                int kills = this.killsMap.get(player.getKiller().getUniqueId());
                int deaths = this.deathsMap.get(player.getKiller().getUniqueId());
                double kdr = ((double)kills) / deaths;
                this.kdrMap.put(player.getKiller().getUniqueId(), kdr);
                Main.playerStatsData.getConfig("playerStats.yml").set(player.getKiller().getName() + ".kdr",
                        Double.parseDouble(this.df.format(this.kdrMap.get(player.getKiller().getUniqueId()))));
            }
            else {
                this.kdrMap.put(player.getKiller().getUniqueId(), 0.0);
                Main.playerStatsData.getConfig("playerStats.yml").set(player.getKiller().getName() + ".kdr", 0.0);
            }
            player.getKiller().getScoreboard().getTeam("kdrScore").setSuffix(ChatColor.WHITE.toString() +
                    Main.playerStatsData.getConfig("playerStats.yml").get(player.getKiller().getName() + ".kdr"));

        }

        // killStreak updater (killer)
        if (player.getKiller() != null) {
            killStreakMap.put(player.getKiller().getUniqueId(), killStreakMap.get(player.getKiller().getUniqueId()) + 1);

            Main.playerStatsData.getConfig("playerStats.yml").set(player.getKiller().getName() + ".killstreak",
                    killStreakMap.get(player.getKiller().getUniqueId()));

            player.getKiller().getScoreboard().getTeam("killStreakScore").setSuffix(ChatColor.WHITE.toString() +
                    Main.playerStatsData.getConfig("playerStats.yml").get(player.getKiller().getName() + ".killstreak"));
        }

        // (killed)
        killStreakMap.put(player.getUniqueId(), 0);

        Main.playerStatsData.getConfig("playerStats.yml").set(player.getName() + ".killstreak", 0);

        player.getScoreboard().getTeam("killStreakScore").setSuffix(ChatColor.WHITE.toString() +
                Main.playerStatsData.getConfig("playerStats.yml").get(player.getName() + ".killstreak"));

        // save data.
        Main.playerStatsData.saveConfig("playerStats.yml");

        // kill streak announcements.
        KillStreakProcedure.killStreakAnnouncer(player);
    }

}
