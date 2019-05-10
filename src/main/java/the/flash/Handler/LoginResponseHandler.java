package the.flash.Handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.flash.Commands.LoginRequestPacket;
import the.flash.Commands.LoginResponsePacket;
import the.flash.Commands.Packet;

import java.util.UUID;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        if(msg.isSuccessful()) {
            System.out.println("Valid Login");
        } else {
            System.out.println(msg.getReason());
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("LoginResponseHandler");
        LoginRequestPacket packet = new LoginRequestPacket();
        packet.setUserId(UUID.randomUUID().toString());
        packet.setUsername("Ray");
        packet.setPassword("You can do it");
        ctx.channel().writeAndFlush(packet);
    }
}
