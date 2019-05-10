package the.flash;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import the.flash.Commands.*;


public class LoginServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        Packet packet = PacketCodeC.INSTANCE.decode(buf);
        if(packet instanceof LoginRequestPacket) {
            System.out.println("Authenticating...");
            LoginResponsePacket res = new LoginResponsePacket();
            if(validateLogin((LoginRequestPacket) packet)) {

                res.setCode(200);

            } else {
                res.setCode(400);
                res.setReason("Wrong Password");
            }
            ByteBuf resBuf = PacketCodeC.INSTANCE.encode(ctx.alloc().buffer(), res);
            ctx.channel().writeAndFlush(resBuf);
        } else if(packet instanceof MessageRequestPacket) {
            System.out.println("Receiving Message...");
            MessageResponsePacket res = new MessageResponsePacket();
            res.setMessage("服务端收到信息: " + ((MessageRequestPacket) packet).getMessage());
            ByteBuf resBuf = PacketCodeC.INSTANCE.encode(ctx.alloc().buffer(), res);
            ctx.channel().writeAndFlush(resBuf);
        }
    }
    private boolean validateLogin(LoginRequestPacket packet) {
        return true;
    }
}
