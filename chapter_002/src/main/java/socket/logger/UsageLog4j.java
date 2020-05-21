package socket.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte bytes = -128;
        short shorts = 512;
        int ints = 1024;
        long longs = 2048L;
        float floats = 100.001F;
        double doubles = 321.123D;
        char chars = '&';
        boolean bool = true;

        LOG.debug("\nbyte : {}\nshort : {}\nint : {}\nlong : {}\nfloat : {}\ndouble : {}\nchar : {}\nboolean : {}",
                bytes, shorts, ints, longs, floats, doubles, chars, bool);
    }
}