package collections.map;

import java.util.Calendar;
import java.util.Objects;

/**
 * 1. Создать модель User [#281950]
 * User data model
 * @author Kirill Asmanov
 * @since 07.05.2020
 */
@SuppressWarnings("CheckStyle")
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
/*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return children == user.children &&
                Objects.equals(name, user.name) &&
                Objects.equals(birthday, user.birthday);
    }
*/
    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
/* @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", children=" + children + '}';
    }*/
}
