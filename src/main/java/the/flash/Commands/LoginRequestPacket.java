package the.flash.Commands;

import lombok.Data;

import static the.flash.Commands.Command.LOGIN_REQUEST;

@Data
public class LoginRequestPacket extends Packet {

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
