package the.flash.Handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.flash.Commands.MessageRequestPacket;
import the.flash.Commands.MessageResponsePacket;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        System.out.println("Received Message: " + msg.getMessage());
        MessageResponsePacket packet = new MessageResponsePacket();
        packet.setMessage("服务端回复" + msg.getMessage());
        ctx.channel().writeAndFlush(packet);
    }
}
