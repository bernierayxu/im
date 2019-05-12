package the.flash.Handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.flash.Commands.GroupCreateResponsePacket;

public class GroupCreateResponseHandler extends SimpleChannelInboundHandler<GroupCreateResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupCreateResponsePacket msg) throws Exception {
        System.out.println("Group " + msg.getGroupId() + " Created " );
        System.out.println("Members are: " + msg.getUserName());
    }
}
