package parental_control_service;

import org.junit.Assert;
import org.junit.Test;
import parental_control_service.domain.ParentalControlLevel;
import parental_control_service.movie_meta_data.MovieService;

// for simplicity of integration test - I am not testing null scenarios. It is handle in lower layers.
public class ParentalControlIntegrationTest {

    private ParentalControlRetriever parentalControlRetriever;
    private MovieService movieService;

    private ParentalControlService testObj = new ParentalControlService();

    @Test
    public void canWatchMovieIfCustomerParentalPreferenceEqualToMovieParentalControlLevel() {
        whenRetrievedMovieServiceIdIs("U");

        ParentalControlLevel customerPreference = new ParentalControlLevel("U");
        boolean result = testObj.canWatchMovie(customerPreference, "1");

        Assert.assertTrue(result);
    }

    @Test
    public void canWatchMovieIfCustomerParentalPreferenceGreaterThenMovieParentalControlLevel() {
        whenRetrievedMovieServiceIdIs("U");

        ParentalControlLevel customerPreference = new ParentalControlLevel("18");
        boolean result = testObj.canWatchMovie(customerPreference, "1");

        Assert.assertTrue(result);
    }

    @Test
    public void canNotWatchMovieIfCustomerParentalPreferenceIsLessThenMovieParentalControlLevel() {
        whenRetrievedMovieServiceIdIs("18");

        ParentalControlLevel customerPreference = new ParentalControlLevel("U");
        boolean result = testObj.canWatchMovie(customerPreference, "1");

        Assert.assertFalse(result);
    }

    private void whenRetrievedMovieServiceIdIs(String id) {
        movieService = movieId -> id;
        parentalControlRetriever = new ParentalControlRetriever();
        parentalControlRetriever.setMovieService(movieService);
        testObj.setParentalControlRetriever(parentalControlRetriever);
    }

}
