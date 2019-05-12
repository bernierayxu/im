package the.flash.Commands;

import lombok.Data;

import static the.flash.Commands.Command.MESSAGE_RESPONSE;

@Data
public class MessageResponsePacket extends Packet{
    private String message;
    private String fromUserId;
    private String fromUserName;
    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}