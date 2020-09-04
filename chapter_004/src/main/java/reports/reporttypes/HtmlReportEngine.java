package reports.reporttypes;

import reports.Employee;
import reports.Store;

import java.util.function.Predicate;

/**
 * Employee report for IT department in HTML
 */
public class HtmlReportEngine implements ReportEngine {
    /**
     * Generates employee report
     * @param store store wits data
     * @param filter a search criteria
     * @return report string
     */
    @Override
    public String generate(Store store, Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();

        text.append("<html>")
                .append("<head><meta charset=\"utf-8\"><title>Сотрудники</title></head>")
                .append("<body>")
                .append("<table border=\"1\">")
                .append("<caption>Сотрудники</caption>")
                .append("<tr>")
                .append("<th>Name</th>")
                .append("<th>Hired</th>")
                .append("<th>Fired</th>")
                .append("<th>Salary</th>")
                .append("</tr>");
        for (Employee employee : store.findBy(filter)) {
            text.append("<tr>")
                    .append("<td>")
                    .append(employee.getName())
                    .append("</td>")
                    .append("<td>")
                    .append(employee.getHired().getTime())
                    .append("</td>")
                    .append("<td>")
                    .append(employee.getFired().getTime())
                    .append("</td>")
                    .append("<td>")
                    .append(employee.getSalary())
                    .append("</td>")
                    .append("</tr>");
        }
        text.append("</table>")
                .append("</body>")
                .append("</html>");


        return text.toString();
    }
}
