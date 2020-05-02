package generic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserStoreTest {
    UserStore store = new UserStore();

    @Before
    public void init() {
        User userFirst = new User("1", "Кирилл");
        User userSecond = new User("2", "Катя");
        User userThird = new User("3", "Иван");
        store.add(userFirst);
        store.add(userSecond);
        store.add(userThird);
    }

    @Test
    public void whenDelete() {
        assertTrue(store.delete("1"));
        assertNull(store.findById("1"));
    }

    @Test
    public void whenReplace() {
        assertTrue(store.replace("2", new User("6", "Пётр")));
        assertNull(store.findById("2"));
        assertEquals(store.findById("6").getName(),"Пётр");
    }

    @Test
    public void findById() {
        assertEquals(store.findById("3").getName(),"Иван");
    }
}