package the.flash;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import the.flash.Commands.Attributes;

public class LoginUtil {
    public static void markLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Object> login = channel.attr(Attributes.LOGIN);
        return login.get() != null;
    }

}
