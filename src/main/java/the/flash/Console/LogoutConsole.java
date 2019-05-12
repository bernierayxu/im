package the.flash.Console;

import io.netty.channel.Channel;
import the.flash.Commands.LogoutRequestPacket;

import java.util.Scanner;

public class LogoutConsole implements Console {
    @Override
    public void exec(Channel channel, Scanner scanner) {
        channel.writeAndFlush(new LogoutRequestPacket());
    }
}
