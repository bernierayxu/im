package the.flash.Console;

import io.netty.channel.Channel;
import java.util.Scanner;

public interface Console {
    public void exec(Channel channel, Scanner scanner);
}
