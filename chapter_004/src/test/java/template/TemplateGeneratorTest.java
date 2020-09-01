package template;

import org.junit.Test;
import template.exceptions.MoreKeysThenNeedException;
import template.exceptions.NotEnoughKeyException;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TemplateGeneratorTest {

    @Test
    public void produce() throws NotEnoughKeyException, MoreKeysThenNeedException {
        Generator testGen = new TemplateGenerator();
        HashMap<String, String> args = new HashMap<>();
        String template = "I am a ${name}, Who are ${subject}?";
        args.put("name", "Kirill");
        args.put("subject", "you");
        String rsl = testGen.produce(template, args);
        assertThat(rsl, is("Work!"));
    }

    @Test
    public void whenMoreKeysThenNeeded() {
        Generator testGen = new TemplateGenerator();
        HashMap<String, String> args = new HashMap<>();
        String template = "I am a ${name}, Who are ${subject}? / More keys";
        args.put("name", "Kirill");
        args.put("subject", "you");
        args.put("age", "22");
        try {
            testGen.produce(template, args);
            fail("MoreKeysThenNeedException expected");
        } catch (Exception ex) {
            assertThat(ex.getMessage(), containsString("More key then need in map"));
        }
    }

    @Test
    public void whenNotEnoughKeysInMap() {
        Generator testGen = new TemplateGenerator();
        HashMap<String, String> args = new HashMap<>();
        String template = "I am a ${name}, Who are ${subject}? / Not enough keys";
        args.put("name", "Kirill");
        try {
            testGen.produce(template, args);
            fail("NotEnoughKeysException expected");
        } catch (Exception ex) {
            assertThat(ex.getMessage(), containsString("Not enough keys"));
        }
    }
}