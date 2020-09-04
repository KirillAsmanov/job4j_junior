package reports.reporttypes;

import reports.Employee;
import reports.Store;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Employee report for HR department without fired and hired fields and sorts by salary decrease
 */
public class HRReportEngine implements ReportEngine {
    /**
     * Generates employee report
     * @param store store wits data
     * @param filter a search criteria
     * @return report string
     */
    @Override
    public String generate(Store store, Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;");
        List<Employee> employees = store.findBy(filter);
        employees.sort(new SalaryComparatorReverse());
        for (Employee employee : employees) {
            text.append(System.lineSeparator())
                    .append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";");
        }
        return text.toString();
    }

    /**
     * Salary decrease comparator
     */
    class SalaryComparatorReverse implements Comparator<Employee> {

        public int compare(Employee e1, Employee e2) {
            if (e1.getSalary() == e2.getSalary()) {
                return 0;
            }
            if (e1.getSalary() < e2.getSalary()) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
