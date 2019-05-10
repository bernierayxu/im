package the.flash.Handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import the.flash.Commands.PacketCodeC;

public class Spliter extends LengthFieldBasedFrameDecoder {
    private static int lengthFieldOffset = 7;
    private static int lengthFieldLength = 4;
    public Spliter() {
        super(Integer.MAX_VALUE, lengthFieldOffset, lengthFieldLength);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if(in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER) {
            ctx.channel().close();
            return null;
        }
        return super.decode(ctx, in);
    }
}
