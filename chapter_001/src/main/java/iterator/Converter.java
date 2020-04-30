package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * 5.1.4. Создать convert(Iterator<Iterator>) [#281935]
 * @author Kirill Asmanov
 * @since 30.04.2020
 */
public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> iterators) {
        return new Iterator<Integer>() {
            /**
             * хранит ссылку на обьект текущего внутреннего итератора
             */
            private Iterator<Integer> innerIterator;

            @Override
            public boolean hasNext() {
                while (innerIterator == null || !innerIterator.hasNext()) {
                    if (!iterators.hasNext()) {
                        return false;
                    }
                    innerIterator = iterators.next();
                }
                return true;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return innerIterator.next();
            }
        };
    }
}
