package the.flash.Console;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import the.flash.Commands.MessageRequestPacket;
import the.flash.Commands.PacketCodeC;

import java.util.Scanner;

public class MessageConsole implements Console {
    @Override
    public void exec(Channel channel, Scanner scanner) {
        System.out.println("发送消息到服务端 ID + 信息");

        MessageRequestPacket packet = new MessageRequestPacket();
        packet.setToUserId(scanner.nextLine());
        packet.setMessage(scanner.nextLine());

        ByteBuf buf = PacketCodeC.INSTANCE.encode(channel.alloc().ioBuffer(), packet);
        channel.writeAndFlush(buf);
    }
}
