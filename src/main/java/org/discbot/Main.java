package org.discbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.exceptions.ContextException;
import org.discbot.commands.*;

import javax.security.auth.login.LoginException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws LoginException{
          JDA jda = JDABuilder.createDefault("UR TOKEN").setActivity(Activity.listening("/help")).build();
          //jda.addEventListener(new Listener());
          ManageCommands manager = new ManageCommands();
          manager.addcommands(new play());
          manager.addcommands(new skip());
          manager.addcommands(new stop());
          manager.addcommands(new Nowplaying());
          manager.addcommands(new Queue());
          manager.addcommands(new help());
          jda.addEventListener(manager);
    }
}