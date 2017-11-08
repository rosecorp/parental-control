package parental_control_service;

import parental_control_service.domain.ParentalControlLevel;
import parental_control_service.domain.ParentalControlLevelRange;

import java.util.Optional;

public class ParentalControlService {

    private ParentalControlRetriever parentalControlRetriever;

    public boolean canWatchMovie(ParentalControlLevel customerPreference, String movieId) {
        Optional<ParentalControlLevel> parentalControlLevel = parentalControlRetriever.retrieveParentalControl(movieId);

        return customerPreference != null && parentalControlLevel.isPresent() ? compareParentalControlLevel(customerPreference, parentalControlLevel.get()) : false;
    }

    //just for simplification of not using DI for testing purposes
    public void setParentalControlRetriever(ParentalControlRetriever parentalControlRetriever) {
        this.parentalControlRetriever = parentalControlRetriever;
    }

    private boolean compareParentalControlLevel(ParentalControlLevel customerPreference, ParentalControlLevel movieParentalControlLevel) {
        ParentalControlLevelRange customerPreferenceLevel = customerPreference.getParentalControlLevel();
        ParentalControlLevelRange parentalControlLevel = movieParentalControlLevel.getParentalControlLevel();

        return customerPreferenceLevel.compareTo(parentalControlLevel) >= 0;
    }

}
