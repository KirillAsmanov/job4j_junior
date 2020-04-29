import org.junit.Assert;
import org.junit.Test;

public class TempClassTest {

    @Test
    public void sum() {
        TempClass a = new TempClass();
        Assert.assertEquals(4, a.sum());
    }
}