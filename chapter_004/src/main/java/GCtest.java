import java.util.Scanner;

import static com.carrotsearch.sizeof.RamUsageEstimator.sizeOf;

public class GCtest {
    static class User {
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("Я удалился!!!");
        }

        public User() {
            System.out.println("Я родился ");
        }

    }

    static class User2 {
        long money;

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("Я удалился");
        }

        public User2() {
            System.out.println("Я родился c полями" + System.lineSeparator());
        }
    }

    public static void main(String[] args) {
        // Для выяснения размера объектов:
        /* System.out.println("ДО СОЗДАНИЯ ОБЪЕКТОВ" + System.lineSeparator());
        info();
        System.out.println("СОЗДАЕМ 2 ОБЪЕКТА" + System.lineSeparator());
        User user = new User();
        User2 user2 = new User2();
        System.out.println("Без полей: " + sizeOf(user));
        System.out.println("С полем long: " + sizeOf(user2));
        info(); */

        // Для активации GC:
        Scanner sc = new Scanner(System.in);
        System.out.println("type run");
        String cmd = sc.nextLine();
        if (cmd.equals("run")) {
            for (int i = 0; i <= 15000; i++) {
                new User();
                System.out.print(i + " ");
            }
        }
        String sd = sc.nextLine();
    }
 
    private static void info() {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Used memory " + (runtime.totalMemory() - runtime.freeMemory()));
        System.out.println("Free memory " + runtime.freeMemory());
        System.out.println("Total memory " + runtime.totalMemory());
        System.out.println("Max memory " + runtime.maxMemory());
        System.out.print(System.lineSeparator());
    }
}
