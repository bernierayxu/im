package the.flash.Commands;


import lombok.Data;

import java.util.List;

import static the.flash.Commands.Command.GROUP_CREATE_RESPONSE;

@Data
public class GroupCreateResponsePacket extends Packet {
    private List<String> userName;

    private String groupId;

    @Override
    public Byte getCommand() {
        return GROUP_CREATE_RESPONSE;
    }
}
