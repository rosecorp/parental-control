package parental_control_service;


import parental_control_service.domain.ParentalControlLevel;
import parental_control_service.movie_meta_data.MovieService;
import parental_control_service.movie_meta_data.exeption.TechnicalFailureException;
import parental_control_service.movie_meta_data.exeption.TitleNotFoundException;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static parental_control_service.domain.ParentalControlLevelRange.NONE;

public class ParentalControlRetriever {
    private MovieService movieService;

    public Optional<ParentalControlLevel> retrieveParentalControl(String movieId) {
        Optional<ParentalControlLevel> parentalControlLevel;

        try {
            //potential blocking thread - just for simplification it's in try/catch block
            String retrievedParentalControlLevel = movieService.getParentalControlLevel(movieId);
            parentalControlLevel = mappedResponse(retrievedParentalControlLevel);
        } catch (TitleNotFoundException | TechnicalFailureException e) {
            parentalControlLevel = empty();
        }

        return parentalControlLevel;
    }

    private Optional<ParentalControlLevel> mappedResponse(String retrievedParentalControlLevel) {
        ParentalControlLevel pcl = new ParentalControlLevel(retrievedParentalControlLevel);

        return pcl.getParentalControlLevel() == NONE ? empty() : of(pcl);
    }

    //just for simplification of not using DI for testing purposes
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }
}
