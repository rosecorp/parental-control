package parental_control_service.domain;

public enum ParentalControlLevelRange {

    NONE(0), U(1), PG(2), _12(3), _18(4);

    private int level;

    ParentalControlLevelRange(int level) {
        this.level = level;
    }

    public static ParentalControlLevelRange getParentalControlLevelRange(String level) {
        if (level == null) {
            return NONE;
        }

        switch (level) {
            case "U":
                return U;
            case "PG":
                return PG;
            case "12":
                return _12;
            case "18":
                return _18;
            default:
                return NONE;
        }
    }
}
