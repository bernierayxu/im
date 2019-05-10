package the.flash;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import the.flash.Commands.LoginRequestPacket;
import the.flash.Commands.Packet;
import the.flash.Commands.PacketCodeC;

import java.nio.charset.Charset;

public class LoginServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        Packet packet = new PacketCodeC().decode(buf);
        if(packet instanceof LoginRequestPacket) {
            System.out.println("Authenticating...");
            if(validateLogin((LoginRequestPacket) packet)) {
                ByteBuf res = ctx.alloc().buffer();
                res.writeBytes("Successful Login".getBytes(Charset.forName("UTF-8")));
                ctx.channel().writeAndFlush(res);
            }
        }
    }
    private boolean validateLogin(LoginRequestPacket packet) {
        return true;
    }
}
