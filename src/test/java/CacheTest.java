import edu.eci.arsw.repository.Cache;
import org.junit.Test;
import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void givenAvalueWhenSaveInCacheThenReturnIt() throws Exception {
        Cache myCache = new Cache();
        myCache.saveQuery("test","value test");
        assertEquals("value test", myCache.getQuery("test"));
    }

    @Test
    public void givenAvalueWhenSaveInCacheThenValidateIfHasQuery() throws Exception {
        Cache myCache = new Cache();
        myCache.saveQuery("test","value test");
        assertEquals(true, myCache.hasQuery("test"));
    }


}
