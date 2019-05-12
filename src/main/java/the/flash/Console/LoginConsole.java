package the.flash.Console;

import the.flash.Commands.LoginRequestPacket;

import io.netty.channel.Channel;
import java.util.Scanner;

public class LoginConsole implements Console {
    @Override
    public void exec(Channel channel, Scanner scanner) {
        System.out.println("请输入账户");

        LoginRequestPacket packet = new LoginRequestPacket();
        packet.setUsername(scanner.nextLine());
        packet.setPassword("You can do it");
        channel.writeAndFlush(packet);
    }
}
