import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Use case:
 * 
 * 1. It should be able to list the cities where affiliate cinemas are located.
 * (CityFinder) 2. Customers should be able to search movies by their title,
 * language, genre, release date, and city name. (MovieService) 3. Once the
 * customer selects a movie, the service should display the cinemas running that
 * movie and its available shows. (CinemaService) 4. The customer should be able
 * to select a show at a particular cinema and book their
 * tickets.(MovieShowService, BookingService)
 * 
 * 
 * 
 * 
 * FLOW:
 * 
 * 1. User calls cityfinder service to get all cities with atleast one cinema
 * (CityFinder.getCityWithCinema) 2. User selects a city and fetches all the
 * cinema for the city (CinemaService.getAllCinemas) 3. User selects a cinema
 * and get all movies (MovieService.getAllMovieShowForCinema) 4. User selects a
 * movie show and get all available seats
 * (MovieShowService.getAudiInfoForMovieShow) 5. User selects seats and attempt
 * the booking (BookingService.bookMovieShow) 6. User invokes the payment
 * service
 */

/**
 * Returns all the cities where we are operational
 * 
 * @return
 */
class CityFinder {
    public List<City> getAllCities() {
        return null;

    }

    public List<City> getCityForTheatre() {
        return null;
    }
}

class City {
    int id;
    String name;
}

class CityService {
    public List<Theatre> getAllTheatres(City city) {
        return null;
    }
}

class Theatre {
    int id;
    String name;
    String address;
    List<Audi> audis;

}

class Audi {
    int id;
    String name;
    String location;
    List<Seat> seats;
}

class Seat {
    int id;
    String type; // recliner, general, etc
    boolean isAvailable;
}

class TheatreService {
    public List<Theatre> getAllTheatres() {
        return null;
    }

    public List<Theatre> getTheatresForMovie(Movie movie) {
        return null;
    }

    public List<Theatre> getTheatreForCity(City city) {
        return null;
    }
}

class Movie {
    int id;
    String name;
    String language;
    LocalDate releaseDate;
}

class MovieSerice {
    public List<MovieShow> getAllMovieShows() {
        return null;
    }

    public List<MovieShow> getAllMovieShowsForTheatre(Theatre theatre) {
        return null;
    }

    public List<MovieShow> getAllMovieShowsForMovie(Movie movie) {
        return null;
    }
}

class MovieShow {
    int id;
    int movieId;
    int theatreId;
    int audiId;
    LocalDateTime showTime;
    Duration duration;
}

class MovieShowService {
    public Audi getAudiForMovieShow(MovieShow movieShow) {
        return null;
    }
}

class BookingService {
    public Ticket bookMovieShow(MovieShow movieShow, List<Seat> seats) {
        return null;
    }

    public Ticket markBookingSuccessful(Ticket ticket) {
        return ticket;
    }
}

class BookingPaymentService {
    public boolean pay(Ticket ticket) {
        return true;
    }
}

class Ticket {
    int id;
    Double price;
    LocalDateTime showTime;
    Status status;
    Audi audi;
    Movie movie;
    List<Seat> seats;
    Customer customer;
}

class Customer {
    int id;
    String name;
}

enum Status {
    IN_PROGRESS, BOOKED, CANCELLED, EXPIRED
}

public class BMS {
    public static void main(String[] args) {

    }
}