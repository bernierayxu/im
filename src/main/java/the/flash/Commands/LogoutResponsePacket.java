package the.flash.Commands;

import static the.flash.Commands.Command.LOGOUT_RESPONSE;

public class LogoutResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return LOGOUT_RESPONSE;
    }
}
