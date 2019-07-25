package ru.job4j.servlets.http.cinema;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ValidationService implements HallValidate{
    private final static ValidationService VALIDATE_SERVICE = new ValidationService();
private final Hall cinemaStore = CinemaStore.getInstance();

    private ValidationService() {
    }
        public static ValidationService getInstance() {
        return VALIDATE_SERVICE;
    }

    public boolean addPlace(Account account, Place place) {
        return cinemaStore.addPlace(account, place);
    }
}
