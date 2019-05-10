package the.flash;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import the.flash.Commands.LoginRequestPacket;
import the.flash.Commands.PacketCodeC;

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
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("Received message " + ((ByteBuf) msg).toString(Charset.forName("UTF-8")));
    }
}
