package the.flash.Handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import sun.rmi.runtime.Log;
import the.flash.Commands.MessageRequestPacket;
import the.flash.Commands.MessageResponsePacket;
import the.flash.LoginUtil;

import java.util.Date;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        System.out.println(new Date() + " 服务器收到信息: " + msg.getMessage());
        MessageResponsePacket packet = new MessageResponsePacket();
        packet.setMessage("服务端回复" + msg.getMessage());
        //send messages to other people
        Channel toChannel = LoginUtil.getChannel(msg.getToUserId());
        if(toChannel != null) {
            MessageResponsePacket res = new MessageResponsePacket();
            res.setMessage(msg.getMessage());
            res.setFromUserId(LoginUtil.getSession(ctx.channel()).getUserId());
            res.setFromUserName(LoginUtil.getSession(ctx.channel()).getUserName());
            if(LoginUtil.hasLogin(toChannel)) {
                toChannel.writeAndFlush(res);
            } else {
                System.err.println(res.getFromUserName() + "不在线");
            }

        } else {
            System.err.println(new Date() + " " + msg.getToUserId() + "不存在");
        }
//        ctx.channel().writeAndFlush(packet);
    }
}
