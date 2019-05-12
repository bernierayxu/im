package the.flash.Console;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class ConsoleManager implements Console{
    private  Map<String, Console> map;
    public ConsoleManager() {
        map = new HashMap<>();
        map.put("sendMessages", new MessageConsole());
        map.put("createGroup", new GroupCreateConsole());
        map.put("logout", new LogoutConsole());
    }

    @Override
    public void exec(Channel channel, Scanner scanner) {
        System.out.println("请输入命令");
        Console command = map.get(scanner.nextLine());
        if(command != null) {
            command.exec(channel, scanner);
        } else {
            System.err.println("该命令不存在");
        }
    }
}
