package org.discbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Listener extends ListenerAdapter {
    /* @Override
    public void onReady(ReadyEvent event) {
        JDA jda = event.getJDA();
        for(Guild guild:jda.getGuilds()){
            for(TextChannel channel: jda.getTextChannels()){
                channel.sendMessage("Hello bois").queue();
            }
        }
    }*/

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        JDA jda = event.getJDA();
        if(event.getAuthor().isBot()){
            return;
        }
        MessageChannel channel = event.getChannel();
        channel.sendMessage("hello").queue();
    }
}
