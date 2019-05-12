package the.flash.Console;

import io.netty.channel.Channel;
import the.flash.Commands.GroupCreateRequestPacket;

import java.util.Arrays;
import java.util.Scanner;

public class GroupCreateConsole implements Console {
    @Override
    public void exec(Channel channel, Scanner scanner) {
        System.out.println("请输入团成员ID以,分开");
        String line = scanner.nextLine();
        GroupCreateRequestPacket packet = new GroupCreateRequestPacket();
        packet.setUserList( Arrays.asList(line.split(",")) );
        channel.writeAndFlush(packet);
    }
}
