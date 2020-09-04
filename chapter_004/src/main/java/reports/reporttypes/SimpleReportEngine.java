package reports.reporttypes;

import reports.Employee;
import reports.Store;

import java.util.function.Predicate;

/**
 * Simple report with all fields from store
 */
public class SimpleReportEngine implements ReportEngine {
    /**
     * Generates employee report
     * @param store store wits data
     * @param filter a search criteria
     * @return report string
     */
    @Override
    public String generate(Store store, Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        for (Employee employee : store.findBy(filter)) {
            text.append(System.lineSeparator())
                    .append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";");
        }
        return text.toString();
    }
}
