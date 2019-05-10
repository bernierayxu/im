package the.flash.Commands;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import sun.rmi.runtime.Log;
import the.flash.Serializer.JsonSerializer;
import the.flash.Serializer.Serializer;
import the.flash.Serializer.SerializerAlgorithm;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import static the.flash.Commands.Command.LOGIN_REQUEST;
import static the.flash.Serializer.SerializerAlgorithm.*;

public class PacketCodeC {
    private static int MAGIC_NUMBER = 0x12345678;
    private final Map<Byte, Class <? extends Packet>> packetMap;
    private final Map<Byte, Serializer> serializerMap;
    private PacketCodeC() {
        packetMap = new HashMap<>();
        packetMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        serializerMap = new HashMap<>();
        serializerMap.put(JSON, new JsonSerializer());
    }
    public Packet decode(ByteBuf buffer) {
        buffer.skipBytes(4);
        buffer.skipBytes(1);
        byte serializerAlgorithm = buffer.readByte();
        byte command = buffer.readByte();
        int length = buffer.readInt();
        byte[] bytes = new byte[length];
        buffer.readBytes(bytes);
        Serializer serializer = serializerMap.get(serializerAlgorithm);
        Class<? extends Packet> commandClass = packetMap.get(command);
        if(serializer == null || commandClass == null) {
            System.out.println("Null");
            return null;
        }
        Packet packet = serializer.unserialize(commandClass, bytes);
        return packet;
    }
    public ByteBuf encode(ByteBuf buf, Packet packet) {
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        buf.writeInt(MAGIC_NUMBER);
        buf.writeByte(packet.getVersion());
        buf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        buf.writeByte(packet.getCommand());
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
        return buf;
    }

}
