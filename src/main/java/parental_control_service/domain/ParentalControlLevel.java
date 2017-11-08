package parental_control_service.domain;

import static parental_control_service.domain.ParentalControlLevelRange.getParentalControlLevelRange;

public class ParentalControlLevel {

    private ParentalControlLevelRange parentalControlLevelRange;

    public ParentalControlLevel(String level) {
        this.parentalControlLevelRange = getParentalControlLevelRange(level);
    }

    public ParentalControlLevelRange getParentalControlLevel() {
        return this.parentalControlLevelRange;
    }

}
