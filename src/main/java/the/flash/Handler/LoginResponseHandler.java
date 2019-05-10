package the.flash.Handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.flash.Commands.LoginResponsePacket;
import the.flash.Commands.Packet;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        if(msg.isSuccessful()) {
            System.out.println("Valid Login");
        } else {
            System.out.println(msg.getReason());
        }
    }
}
