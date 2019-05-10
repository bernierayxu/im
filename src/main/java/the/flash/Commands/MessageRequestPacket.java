package the.flash.Commands;

import lombok.Data;

import static the.flash.Commands.Command.MESSAGE_REQUEST;

@Data
public class MessageRequestPacket extends Packet{
    private String message;
    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
