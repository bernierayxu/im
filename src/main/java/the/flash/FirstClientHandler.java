package the.flash;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("准备发送数据");
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "Hello 你好".getBytes(Charset.forName("UTF-8"));
        buffer.writeBytes(bytes);
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buffer = (ByteBuf) msg;
        System.out.println("收到服务端发回的数据"+ ((ByteBuf) msg).toString(Charset.forName("UTF-8")));
    }
}
