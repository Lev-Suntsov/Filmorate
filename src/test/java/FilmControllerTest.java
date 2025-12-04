import model.Film;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FilmControllerTest {
    FilmController controller;
    Film film;
    @BeforeEach
    public void setController(){
        controller = new FilmController();
        film = new Film();
    }

    @Test
    public void addFilmTest(){
       RuntimeException exception = assertThrows(RuntimeException.class, () -> controller.addFilm(film));
       assertEquals(exception.getMessage(), "Укажите имя фильма");

       film.setName("Форсаж");
       exception = assertThrows(RuntimeException.class, () -> controller.addFilm(film));
       assertEquals(exception.getMessage(), "Укажите описание фильма");

       film.setDescription("Фильм форсаж");
       exception = assertThrows(RuntimeException.class, () -> controller.addFilm(film));
       assertEquals(exception.getMessage(), "Укажите дату релиза");

       film.setReleaseDate(LocalDate.now());
       exception = assertThrows(RuntimeException.class, () -> controller.addFilm(film));
       assertEquals(exception.getMessage(), "Укажите продолжительность фильма");

       film.setDuration(Duration.ofHours(3));

       film.setId(controller.addFilm(film).getId());
       assertEquals(true, controller.getFilms());
    }

}
