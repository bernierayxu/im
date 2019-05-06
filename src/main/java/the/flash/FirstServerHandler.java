package the.flash;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class FirstServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buffer = (ByteBuf) msg;
        System.out.println("收到数据" + buffer.toString(Charset.forName("UTF-8")));
        ctx.channel().writeAndFlush(getByteBuf(ctx));
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "服务器收到你的数据了".getBytes(Charset.forName("UTF-8"));
        buffer.writeBytes(bytes);
        return buffer;
    }
}
