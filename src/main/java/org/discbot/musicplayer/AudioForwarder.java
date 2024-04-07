package org.discbot.musicplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.MutableAudioFrame;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.entities.Guild;
import org.jetbrains.annotations.Nullable;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class AudioForwarder implements AudioSendHandler {

    private final AudioPlayer audioPlayer;
    private final ByteBuffer buffer;
    private final Guild guild;
    private MutableAudioFrame frame;
    private int time;

    public AudioForwarder(AudioPlayer audioPlayer, Guild guild) {
        this.audioPlayer = audioPlayer;
        this.buffer = ByteBuffer.allocate(1024);
        this.frame = new MutableAudioFrame();
        this.frame.setBuffer(buffer);
        this.guild = guild;
    }

    @Override
    public boolean canProvide() {

        boolean canProvide = audioPlayer.provide(frame);
        if(!canProvide) {
            time += 20;
            if(time >= 300000) {
                time = 0;
                guild.getAudioManager().closeAudioConnection();
            }
        } else {
            time = 0;
        }
        return canProvide;
    }

    @Nullable
    @Override
    public ByteBuffer provide20MsAudio() {
        final Buffer tmp = ((Buffer) this.buffer).flip();
        return (ByteBuffer) tmp;
    }

    @Override
    public boolean isOpus() {
        return true;
    }

}