package template;

import template.exceptions.MoreKeysThenNeedException;
import template.exceptions.NotEnoughKeyException;

import java.util.Map;
/**
 * 3. Шаблонизатор. [#281993]
 * @since 01.09.2020
 * @author Kirill Asmanov
 */
public interface Generator {
    String produce(String template, Map<String, String> args) throws NotEnoughKeyException, MoreKeysThenNeedException;
}