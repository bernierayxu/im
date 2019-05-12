package the.flash.Handler;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import sun.rmi.runtime.Log;
import the.flash.Commands.LoginRequestPacket;
import the.flash.Commands.LoginResponsePacket;
import the.flash.Commands.Packet;
import the.flash.LoginUtil;
import the.flash.Session;

import java.util.Date;
import java.util.UUID;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        LoginUtil.unbindSession(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
//        System.out.println("LoginRequestHandler");
        ctx.channel().writeAndFlush(login(msg, ctx));
    }

    private Packet login(LoginRequestPacket packet, ChannelHandlerContext ctx) {
        LoginResponsePacket res = new LoginResponsePacket();
        if(validateLogin((packet))) {
            String userId = UUID.randomUUID().toString();
            res.setCode(200);
            res.setUserId(userId);
            res.setUserName(packet.getUsername());
            LoginUtil.bindSession(new Session(userId, packet.getUsername()), ctx.channel());
            System.out.println(new Date() + " " + res.getUserName() +  " has Login");
        } else {
            res.setCode(400);
            res.setReason("Invalid Credential");
        }
        return res;
    }

    private boolean validateLogin(LoginRequestPacket packet) {
        return true;
    }
}
