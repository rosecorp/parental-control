package parental_control_service.movie_meta_data;


import parental_control_service.movie_meta_data.exeption.TechnicalFailureException;
import parental_control_service.movie_meta_data.exeption.TitleNotFoundException;

public interface MovieService {

    String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException;

}
