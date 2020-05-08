package collections.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class MapExample {
    public static void main(String[] args) {
        Map<User, String> userMap = new HashMap();
        User first = new User("Кирилл", 1, new GregorianCalendar(1997, Calendar.SEPTEMBER, 5));
        User second = new User("Кирилл", 1, new GregorianCalendar(1997, Calendar.SEPTEMBER, 5));
        User third = second;
        userMap.put(first, "value1");
        userMap.put(second, "value2");
        userMap.put(third, "REPLACEMENT");
        System.out.println(userMap);
        // Оба объекта User являются уникальными потому, что они, будучи ссылочными типами, хранят ссылки на
        // разные ячейки в памяти.
        // Карта воспринимает их как два разных объекта, потому что по умолчанию метод equals в Object сравнивает
        // адреса ссылок, которые не равны у этих объектов, и hashcode будет отличаться.
        // Для того, чтобы карта могла опознать объекты как равные, нужно либо присвоить объекту такую же ссылку (third),
        // либо переопределить у объекта методы equals() И hashcode().
    }
}
