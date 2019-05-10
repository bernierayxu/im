package the.flash.Handler;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import the.flash.Commands.LoginRequestPacket;
import the.flash.Commands.LoginResponsePacket;
import the.flash.Commands.Packet;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        System.out.println("LoginRequestHandler");
        ctx.channel().writeAndFlush(login(msg));
    }

    private Packet login(LoginRequestPacket packet) {
        LoginResponsePacket res = new LoginResponsePacket();
        if(validateLogin((packet))) {
            res.setCode(200);
        } else {
            res.setCode(400);
            res.setReason("Invalid Credential");
        }
        return res;
    }

    private boolean validateLogin(LoginRequestPacket packet) {
        return false;
    }
}
