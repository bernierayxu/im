package the.flash.Handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import sun.rmi.runtime.Log;
import the.flash.Commands.GroupCreateRequestPacket;
import the.flash.Commands.GroupCreateResponsePacket;
import the.flash.LoginUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GroupCreateRequestHandler extends SimpleChannelInboundHandler<GroupCreateRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupCreateRequestPacket msg) throws Exception {
        List<String> userList = msg.getUserList();
        Iterator<String> i = userList.iterator();
        ChannelGroup group = new DefaultChannelGroup(ctx.executor());
        List<String> userName = new ArrayList<>();
        while(i.hasNext()) {
            String userId = i.next();
            Channel channel = LoginUtil.getChannel(userId);
            if(channel != null) {
                group.add(channel);
                userName.add(LoginUtil.getSession(channel).getUserName());
            }
        }
        String groupId = LoginUtil.createGroup(group);
        //send response back to creator
        GroupCreateResponsePacket res = new GroupCreateResponsePacket();
        res.setUserName(userName);
        res.setGroupId(groupId);
        group.writeAndFlush(res);
    }
}
