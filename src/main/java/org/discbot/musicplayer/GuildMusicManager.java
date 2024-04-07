package org.discbot.musicplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import net.dv8tion.jda.api.entities.Guild;

public class GuildMusicManager {

    public final AudioPlayer audioPlayer;
    public final TrackScheduler scheduler;
    private final AudioForwarder sendHandler;

    public GuildMusicManager(AudioPlayerManager manager, Guild guild){
        this.audioPlayer = manager.createPlayer();
        this.scheduler = new TrackScheduler(this.audioPlayer);
        this.audioPlayer.addListener(this.scheduler);
        this.sendHandler = new AudioForwarder(this.audioPlayer, guild);
    }

    public AudioForwarder getSendHandler(){

        return sendHandler;
    }

    public TrackScheduler getTrackScheduler(){
        return scheduler;
    }

}