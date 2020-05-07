package collections.map;

import java.util.Calendar;

/**
 * 1. Создать модель User [#281950]
 * User data model
 * @author Kirill Asmanov
 * @since 07.05.2020
 */
public class User {
    private final String name;
    private final int children;
    private final Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getBirthday() {
        return birthday;
    }


    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", children=" + children + ", birthday=" + birthday + '}';
    }
}
