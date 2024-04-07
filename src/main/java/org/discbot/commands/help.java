package org.discbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.discbot.Commands;

import java.util.List;

public class help implements Commands {

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "shows commands";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Bots's Commands");
        embedBuilder.setDescription("**/play**"+" - plays music\n");
        embedBuilder.appendDescription("**/nowplayin**"+" - shows the current track\n");
        embedBuilder.appendDescription("**/queue**"+" - shows the queue of songs added\n");
        embedBuilder.appendDescription("**/skip**"+" - skips the current song\n");
        embedBuilder.appendDescription("**/stop**"+" - stops the player\n");
        event.replyEmbeds(embedBuilder.build()).queue();
    }
}
