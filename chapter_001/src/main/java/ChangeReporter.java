import java.util.*;

/**
 * 2. Статистика по коллекции. [#281921]
 * @author Kirill Asmanov
 * @since 12.05.2020
 */
public class ChangeReporter {

    /**
     * Creates the report of collection modification
     * @param baseList - non-modified list
     * @param changedList - modified list
     * @return Report with info about list modifications
     */
    public Report createReport(List<Element> baseList, List<Element> changedList) {
        Report rsl = new Report();
        if (baseList.hashCode() == changedList.hashCode()) { // проверяем списки на равенство
            return rsl;
        }
        HashMap<String, Element> baseMap = new HashMap<>();
        for (Element e : baseList) {
            baseMap.put(e.id, e);
        }

        for (Element e : changedList) {
            Element foundElement = baseMap.get(e.id);
            if (foundElement != null) {
                if (foundElement.name.equals(e.name)) {
                    baseMap.remove(e.id);
                } else {
                    rsl.modified.add(baseMap.remove(e.id));
                }
            } else {
                rsl.added.add(e);
            }
        }
        rsl.deleted = new ArrayList<>(baseMap.values());
        return rsl;
    }


    /* ------- inner classes ---------- */

    /**
     * Model of elements in lists
     */
    static class Element {
        String id;
        String name;

        public Element(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "{ id='" + id + '\'' + ", name='" + name + '\'' + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Element element = (Element) o;
            return Objects.equals(id, element.id) && Objects.equals(name, element.name);

        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    /**
     * Report model
     */
    static class Report {
        List<Element> added;
        List<Element> modified;
        List<Element> deleted;

        public Report() {
            this.added = new ArrayList<>();
            this.modified = new ArrayList<>();
            this.deleted = new ArrayList<>();
        }

        public Report(List<Element> added, List<Element> modified, List<Element> deleted) {
            this.added = added;
            this.modified = modified;
            this.deleted = deleted;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Report report = (Report) o;
            return Objects.equals(added, report.added) && Objects.equals(modified, report.modified) && Objects.equals(deleted, report.deleted);
        }

        @Override
        public int hashCode() {
            return Objects.hash(added, modified, deleted);
        }

        @Override
        public String toString() {
            return "Report:" + System.lineSeparator()
                    + "Added = " + added.size() + ": " + added + System.lineSeparator()
                    + "Modified = " + modified.size() + ": " + modified + System.lineSeparator()
                    + "Deleted = " + deleted.size() + ": " + deleted;
        }
    }


    // Для демонстрации работы в консоли
    public static void main(String[] args) {
        ArrayList<Element> prev = new ArrayList<>();
        prev.add(new Element("1", "A"));
        prev.add(new Element("3", "A"));
        prev.add(new Element("2", "A"));

        ArrayList<Element> current = new ArrayList<>();
        current.add(new Element("4", "A"));
        current.add(new Element("3", "A"));
        current.add(new Element("1", "B"));

        ChangeReporter cr = new ChangeReporter();
        System.out.println("Previous list: " + prev);
        System.out.println("Current list: " + current);
        System.out.println("-----------------------------");
        System.out.println(cr.createReport(prev, current));
    }
}
