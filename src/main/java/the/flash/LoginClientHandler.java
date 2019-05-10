package the.flash;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import the.flash.Commands.*;

import java.nio.charset.Charset;
import java.util.UUID;

public class LoginClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LoginRequestPacket packet = new LoginRequestPacket();
        packet.setVersion((byte)1);
        packet.setUserId(UUID.randomUUID().toString());
        packet.setUsername("Ray");
        packet.setPassword("You can do it!");
        ByteBuf buffer = ctx.alloc().buffer();
        PacketCodeC.INSTANCE.encode(buffer, packet);
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println(((ByteBuf) msg).toString(Charset.forName("UTF-8")));
        Packet packet = PacketCodeC.INSTANCE.decode((ByteBuf) msg);
        if(packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginPacket = (LoginResponsePacket) packet;

            if(loginPacket.isSuccessful()) {
                LoginUtil.markLogin(ctx.channel());
                System.out.println(loginPacket.getCode());
            } else {

                System.out.println(loginPacket.getCode() + " " + loginPacket.getReason());
            }

        } else if(packet instanceof MessageResponsePacket) {
            MessageResponsePacket messagePacket = (MessageResponsePacket) packet;
            System.out.println(messagePacket.getMessage());
        }

    }
}
