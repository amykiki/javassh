package doc.enums;

/**
 * Created by Amysue on 2016/5/5.
 */
public enum Role {
    ADMIN(0), NORMAL(1);
    private int code;

    public int getCode() {
        return code;
    }
    Role(int code) {
        this.code = code;
    }
}
