package org.discbot.commands;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.discbot.Commands;
import org.discbot.musicplayer.GuildMusicManager;
import org.discbot.musicplayer.PlayerManager;

import java.util.List;

public class Nowplaying implements Commands {
    @Override
    public String getName() {
        return "nowplayin";
    }

    @Override
    public String getDescription() {
        return "details of the song";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        net.dv8tion.jda.api.entities.Member member = event.getMember();
        GuildVoiceState memberVoiceState = member.getVoiceState();

        if(!memberVoiceState.inAudioChannel()){
            event.reply("Get in a voice channel");
            return;
        }

        Member self = event.getGuild().getSelfMember();
        GuildVoiceState selfVoiceState = self.getVoiceState();

        if(!selfVoiceState.inAudioChannel()){
            event.reply("I am not in an audio channel").queue();
            return;
        }

        if(selfVoiceState.getChannel()!= memberVoiceState.getChannel()){
            event.reply("You are not in the same channel as me").queue();
            return;
        }

        GuildMusicManager guildMusicManager = PlayerManager.get().getGuildMusicManager(event.getGuild());
        if(guildMusicManager.getTrackScheduler().getPlayer().getPlayingTrack()==null){
            event.reply("I am not playing anything").queue();
            return;
        }
        AudioTrackInfo info = guildMusicManager.getTrackScheduler().getPlayer().getPlayingTrack().getInfo();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Currently Playing");
        embedBuilder.setDescription("**Name:** '"+info.title+ "'");
        embedBuilder.appendDescription("\n**Author:** `" + info.author + "`");
        embedBuilder.appendDescription("\n**URL:** `" + info.uri + "`");
        event.replyEmbeds(embedBuilder.build()).queue();
    }
}
