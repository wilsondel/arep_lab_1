import edu.eci.arsw.service.HttpConnection;
import org.junit.Test;

import javax.print.attribute.standard.PresentationDirection;

import static org.junit.Assert.*;

public class HttpConnectionTest {

    @Test
    public void givenAAPIWhenRequestThenReturnJson() throws Exception {
        String response = HttpConnection.getAPIInfo("t=tenet");
        String expected = "{\"Title\":\"Tenet\",\"Year\":\"2020\",\"Rated\":\"PG-13\",\"Released\":\"03 Sep 2020\",\"Runtime\":\"150 min\",\"Genre\":\"Action, Sci-Fi, Thriller\",\"Director\":\"Christopher Nolan\",\"Writer\":\"Christopher Nolan\",\"Actors\":\"John David Washington, Robert Pattinson, Elizabeth Debicki\",\"Plot\":\"Armed with only one word, Tenet, and fighting for the survival of the entire world, a Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.\",\"Language\":\"English, Russian, Ukrainian, Estonian, Norwegian, Hindi\",\"Country\":\"United States, United Kingdom\",\"Awards\":\"Won 1 Oscar. 50 wins & 137 nominations total\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BMzU3YWYwNTQtZTdiMC00NjY5LTlmMTMtZDFlYTEyODBjMTk5XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"7.3/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"69%\"},{\"Source\":\"Metacritic\",\"Value\":\"69/100\"}],\"Metascore\":\"69\",\"imdbRating\":\"7.3\",\"imdbVotes\":\"513,686\",\"imdbID\":\"tt6723592\",\"Type\":\"movie\",\"DVD\":\"15 Dec 2020\",\"BoxOffice\":\"$58,504,105\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}";
        assertEquals(expected, response);
    }

}
