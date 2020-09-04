package reports.reporttypes;

import reports.Employee;
import reports.Store;

import java.util.function.Predicate;

/**
 * Employee report for counter department with changes in salary field
 */
public class CounterDepReportEngine implements ReportEngine {
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

        // "Отдел бухгалтерии попросил изменить вид зарплаты." - не ясно что имелось ввиду в данном случае.
        // добавил значок доллара после суммы.

        for (Employee employee : store.findBy(filter)) {
            text.append(System.lineSeparator())
                    .append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append("$").append(";");
        }
        return text.toString();
    }
}
