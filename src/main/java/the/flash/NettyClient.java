package the.flash;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import the.flash.Commands.LoginRequestPacket;
import the.flash.Commands.MessageRequestPacket;
import the.flash.Commands.PacketCodeC;
import the.flash.Console.ConsoleManager;
import the.flash.Console.LoginConsole;
import the.flash.Handler.*;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class NettyClient {
    public static int MAX_RETRY = 3;
    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
//                socketChannel.pipeline().addLast(new LoginClientHandler());
                socketChannel.pipeline().addLast(new Spliter())
                        .addLast(new PacketDecoder())
                        .addLast(new LoginResponseHandler())
                        .addLast(new LogoutResponseHandler())
                        .addLast(new MessageResponseHandler())
                        .addLast(new GroupCreateResponseHandler())
                        .addLast(new PacketEncoder());
            }
        });
        connect(bootstrap, "localhost", 8000, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        if(retry == 0) {
            System.out.println("Failed");
            return ;
        }



        bootstrap.connect(host, port).addListener(future -> {
            if(future.isSuccess()) {
                System.out.println("Connected to " + host + ":" + port);
                Channel channel = ((ChannelFuture)future).channel();
                startConsoleThread(channel);
            } else {
                int order = MAX_RETRY - retry + 1;
                System.out.println(new Date() + " 第" + order + "次连接");
                long delay = 1 << order;
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
                        .SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        Scanner scanner = new Scanner(System.in);
        new Thread(()->{
            while(!Thread.interrupted()) {
                if(LoginUtil.hasLogin(channel)) {
                    new ConsoleManager().exec(channel, scanner);
                } else {
                    new LoginConsole().exec(channel, scanner);
                    waitForResponse();
                }

            }


        }).start();
    }

    private static void waitForResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
