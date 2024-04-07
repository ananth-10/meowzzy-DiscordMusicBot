package org.discbot.commands;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Widget;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.managers.AudioManager;
import org.discbot.Commands;
import org.discbot.musicplayer.GuildMusicManager;
import org.discbot.musicplayer.PlayerManager;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class play implements Commands {

    public String getName() {
        return "play";
    }

    public String getDescription() {
        return "Will play a song";
    }

    public List<OptionData> getOptions(){
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.STRING,"name","Name of the song to play"));
        return options;
    }

    public void execute(SlashCommandInteractionEvent event){
        String link = event.getOption("name").getAsString();
        Member member = event.getMember();
        GuildVoiceState memberVoiceState = member.getVoiceState();

        if (!memberVoiceState.inAudioChannel()) {
            event.reply("You need to be in a voice channel").queue();
            return;
        }

        VoiceChannel voiceChannel = (VoiceChannel) memberVoiceState.getChannel();
        AudioManager audioManager = event.getGuild().getAudioManager();

        if (!audioManager.isConnected()) {
            audioManager.openAudioConnection(voiceChannel);
        }

        try {
            new URI(link);
        } catch (URISyntaxException e) {
            link = "ytsearch:" + link;
        }
        PlayerManager playerManager = PlayerManager.get();
        GuildMusicManager guildMusicManager = playerManager.getGuildMusicManager(event.getGuild());

        if (guildMusicManager.getTrackScheduler().isPlaying()) {
            playerManager.play(event.getGuild(), link);
            event.reply("Added to queue").queue();
        } else {
            PlayerManager.get().play(event.getGuild(), link);
            event.reply("Playing").queue();
        }

    }
}
