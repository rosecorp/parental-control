package parental_control_service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import parental_control_service.domain.ParentalControlLevel;
import parental_control_service.domain.ParentalControlLevelRange;
import parental_control_service.movie_meta_data.MovieService;
import parental_control_service.movie_meta_data.exeption.TechnicalFailureException;
import parental_control_service.movie_meta_data.exeption.TitleNotFoundException;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ParentalControlRetrieverTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private ParentalControlRetriever testObj = new ParentalControlRetriever();

    @Test
    public void retrieveParentalControlForExistingTitle() throws Exception {
        when(movieService.getParentalControlLevel("1")).thenReturn("PG");

        Optional<ParentalControlLevel> result = testObj.retrieveParentalControl("1");

        Assert.assertEquals(ParentalControlLevelRange.PG, result.get().getParentalControlLevel());
    }

    @Test
    public void retrieveParentalControlIfTitleDoesNotExist() throws Exception {
        when(movieService.getParentalControlLevel("1")).thenThrow(new TitleNotFoundException());

        Optional<ParentalControlLevel> result = testObj.retrieveParentalControl("1");

        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void retrieveParentalControlIfSystemErrorOccurs() throws Exception {
        when(movieService.getParentalControlLevel("1")).thenThrow(new TechnicalFailureException());

        Optional<ParentalControlLevel> result = testObj.retrieveParentalControl("1");

        Assert.assertFalse(result.isPresent());
    }

}