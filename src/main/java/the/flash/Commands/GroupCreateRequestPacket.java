package the.flash.Commands;


import lombok.Data;


import java.util.List;

import static the.flash.Commands.Command.GROUP_CREATE_REQUEST;

@Data
public class GroupCreateRequestPacket extends Packet {

    private List<String> userList;

    @Override
    public Byte getCommand() {
        return GROUP_CREATE_REQUEST;
    }
}
