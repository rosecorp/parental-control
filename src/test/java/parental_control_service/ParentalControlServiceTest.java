package parental_control_service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import parental_control_service.domain.ParentalControlLevel;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ParentalControlServiceTest {

    @Mock
    private ParentalControlRetriever parentalControlRetriever;

    @InjectMocks
    private ParentalControlService testObj = new ParentalControlService();

    @Test
    public void canWatchMovie() {
        when(parentalControlRetriever.retrieveParentalControl("1")).thenReturn(of(new ParentalControlLevel("PG")));

        boolean result = testObj.canWatchMovie(new ParentalControlLevel("12"), "1");

        Assert.assertTrue(result);
    }

    @Test
    public void wontWatchMovieIfEmptyContentIsReturned() {
        when(parentalControlRetriever.retrieveParentalControl("1")).thenReturn(empty());

        boolean result = testObj.canWatchMovie(new ParentalControlLevel("12"), "1");

        Assert.assertFalse(result);
    }

    @Test
    public void wontWatchMovieForNulls() {
        when(parentalControlRetriever.retrieveParentalControl(null)).thenReturn(empty());

        boolean result = testObj.canWatchMovie(null, null);

        Assert.assertFalse(result);
    }

    @Test
    public void wontWatchMovieForNotSpecifiedCustomerPreference() {
        when(parentalControlRetriever.retrieveParentalControl("1")).thenReturn(of(new ParentalControlLevel("PG")));

        boolean result = testObj.canWatchMovie(null, "1");

        Assert.assertFalse(result);
    }

    @Test
    public void wontWatchMovieForNotMappedParentalControl() {
        when(parentalControlRetriever.retrieveParentalControl("1")).thenReturn(of(new ParentalControlLevel("PG")));

        boolean result = testObj.canWatchMovie(new ParentalControlLevel("some_new_parental_control_not_mapped_yet"), "1");

        Assert.assertFalse(result);
    }

}