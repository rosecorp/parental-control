package parental_control_service.domain;

import org.junit.Assert;
import org.junit.Test;
import parental_control_service.domain.ParentalControlLevelRange;

public class ParentalControlLevelRangeTest {

    @Test
    public void testParentalControlLevelOptionsMapping() {
        ParentalControlLevelRange result = ParentalControlLevelRange.getParentalControlLevelRange("U");

        Assert.assertEquals(ParentalControlLevelRange.U, result);
    }

    @Test
    public void testParentalControlLevelOptionsMappingIfNotFound() {
        ParentalControlLevelRange result = ParentalControlLevelRange.getParentalControlLevelRange("UUUU");

        Assert.assertEquals(ParentalControlLevelRange.NONE, result);
    }

    @Test
    public void testParentalControlLevelOptionsMappingForNull() {
        ParentalControlLevelRange result = ParentalControlLevelRange.getParentalControlLevelRange(null);

        Assert.assertEquals(ParentalControlLevelRange.NONE, result);
    }

}