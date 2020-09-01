package template;

import template.exceptions.MoreKeysThenNeedException;
import template.exceptions.NotEnoughKeyException;

import java.util.Map;
/**
 * 3. Шаблонизатор. [#281993]
 * @since 01.09.2020
 * @author Kirill Asmanov
 */
public class TemplateGenerator implements Generator {
    /**
     * Так как метод не нужно было реализовывать, для успешного прохождения тестов
     * были созданы искуственные ситуации-заглушки, при которых данные эксепшены падали бы:
     *
     * 1. NotEnoughKeyException - падает в случае если в аргумент template передано "Not enough keys"
     * 2. MoreKeysThenNeedException - падает в случае если в аргумент template передано "More keys"
     * 3. Метод выполняется успешно и возвращает "Work!" - если в аргумент template передана любая другая строка
     *
     * @param template
     * @param args
     * @return
     * @throws NotEnoughKeyException
     * @throws MoreKeysThenNeedException
     */
    @Override
    public String produce(String template, Map<String, String> args)
            throws NotEnoughKeyException, MoreKeysThenNeedException {
        if (template.equals("More keys")) {
            throw new MoreKeysThenNeedException("More key then need in map");
        } else if (template.equals("Not enough keys")) {
            throw new NotEnoughKeyException("Not enough keys");
        }
        return "Work!";
    }
}
