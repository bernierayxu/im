package the.flash.Handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.flash.Commands.LoginRequestPacket;
import the.flash.Commands.LoginResponsePacket;
import the.flash.Commands.Packet;
import the.flash.LoginUtil;
import the.flash.Session;

import java.util.UUID;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        if(msg.isSuccessful()) {
            System.out.println("Valid Login");

            LoginUtil.bindSession(new Session(msg.getUserId(), msg.getUserName()), ctx.channel());
        } else {
            System.out.println(msg.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("客户端连接被关闭!");
    }
}
