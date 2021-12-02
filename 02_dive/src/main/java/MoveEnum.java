import java.util.Locale;

public enum MoveEnum {
    FORWARD, DOWN, UP;

    public static MoveEnum toEnum(String value) {
        return MoveEnum.valueOf(value.toUpperCase(Locale.GERMAN));
    }
}
