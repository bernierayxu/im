package the.flash;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import the.flash.Commands.Attributes;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class LoginUtil {

    private static final ConcurrentHashMap<String, Channel> map = new ConcurrentHashMap<String, Channel>();

    private static final ConcurrentHashMap<String, ChannelGroup> group = new ConcurrentHashMap<String, ChannelGroup>();

    public static boolean hasLogin(Channel channel) {
        Attribute<Object> login = channel.attr(Attributes.LOGIN);
        return login.get() != null;
    }

    public static void bindSession(Session session, Channel channel) {
        map.put(session.getUserId(), channel);
        channel.attr(Attributes.LOGIN).set(session);
        System.out.println("Session Bound: " + session.getUserId() + channel);
    }
    public static void unbindSession(Channel channel) {
        map.remove(getSession(channel).getUserId());
        channel.attr(Attributes.LOGIN).set(null);
    }

    public static Session getSession(Channel channel) {
        return (Session) channel.attr(Attributes.LOGIN).get();
    }

    public static Channel getChannel(String userId) {
        return map.get(userId);
    }

    public static ChannelGroup getGroup(String groupId) { return group.get(groupId); }

    public static String createGroup(ChannelGroup channelGroup) {
        String id = UUID.randomUUID().toString();
        group.put(id, channelGroup);
        return id;
    }

}
