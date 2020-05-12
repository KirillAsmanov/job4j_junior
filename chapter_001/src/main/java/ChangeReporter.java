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
        ArrayList<Element> added = new ArrayList<>(changedList);
        ArrayList<Element> deleted = new ArrayList<>(baseList);
        ArrayList<Element> modified = new ArrayList<>();
        for (Element changedEl : changedList) {
            if (!deleted.remove(changedEl)) {
                for (int i = 0; i < deleted.size(); i++) {
                    if (changedEl.id.hashCode() == deleted.get(i).id.hashCode()) {
                        modified.add(deleted.remove(i));
                        break;
                    }
                }
            }
        }
        for (Element baseEl : baseList) {
            if (!added.remove(baseEl)) {
                for (int i = 0; i < added.size(); i++) {
                    if (baseEl.id.hashCode() == added.get(i).id.hashCode()) {
                        added.remove(i);
                        break;
                    }
                }
            }
        }
        return new Report(added, modified, deleted);
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
        prev.add(new Element("1", "B"));
        prev.add(new Element("2", "A"));
        prev.add(new Element("1", "A"));

        ArrayList<Element> current = new ArrayList<>();
        current.add(new Element("3", "A"));
        current.add(new Element("2", "B"));
        current.add(new Element("1", "A"));

        ChangeReporter cr = new ChangeReporter();
        System.out.println("Previous list: " + prev);
        System.out.println("Current list: " + current);
        System.out.println("-----------------------------");
        System.out.println(cr.createReport(prev, current));
    }
}
