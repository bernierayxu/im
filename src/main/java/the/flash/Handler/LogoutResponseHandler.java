package the.flash.Handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.flash.Commands.LogoutResponsePacket;
import the.flash.LoginUtil;

import java.util.Date;

public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket msg) throws Exception {
        System.out.println(new Date() + "  " +LoginUtil.getSession(ctx.channel()).getUserName() + " has logged out!");
        LoginUtil.unbindSession(ctx.channel());
    }
}
