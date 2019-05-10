package the.flash.Commands;

import lombok.Data;

import static the.flash.Commands.Command.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket extends Packet {
    private int code;
    private String reason;
    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
    public boolean isSuccessful() {
        return code == 200 ? true: false;
    }
}
