package inputoutput;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ConfigTest {
    @Test
    public void whenGetCommentString() {
        Config config = new Config("./data/app.properties");
        config.load();
        assertNull(config.value("## HELLO"));
    }

    @Test
    public void whenGetName() {
        Config config = new Config("./data/app.properties");
        config.load();
        assertThat(
                config.value("name"),
                is("Kirill Asmanov")
        );
    }

    @Test
    public void whenGetPosition() {
        Config config = new Config("./data/app.properties");
        config.load();
        assertThat(
                config.value("position"),
                is("junior")
        );
    }

    @Test
    public void whenGetPassword() {
        Config config = new Config("./data/app.properties");
        config.load();
        assertThat(
                config.value("password"),
                is("qwerty123")
        );
    }
}