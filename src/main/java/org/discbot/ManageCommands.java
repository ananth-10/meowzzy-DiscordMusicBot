package org.discbot;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;

public class ManageCommands extends ListenerAdapter {
          private ArrayList<Commands> commands= new ArrayList<>();

    @Override
    public void onReady(ReadyEvent event) {
        for(Guild guild:event.getJDA().getGuilds()){
            for(Commands command:commands){
                if(command.getOptions()==null){
                    guild.upsertCommand(command.getName(),command.getDescription()).queue();
                }
                else{
                    guild.upsertCommand(command.getName(),command.getDescription()).addOptions(command.getOptions()).queue();
                }
            }
        }
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        for(Commands command:commands){
            if(command.getName().equals(event.getName())){
                command.execute(event);
                return;
            }
        }
    }

    public void addcommands(Commands command){
        commands.add(command);
    }
}
