package reports.reporttypes;

import org.junit.Test;
import reports.Employee;
import reports.MemStore;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class HtmlReportEngineTest {
    @Test
    public void generate() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportEngine engine = new HtmlReportEngine();
        StringBuilder expect = new StringBuilder()
                .append("<html>")
                .append("<head><meta charset=\"utf-8\"><title>Сотрудники</title></head>")
                .append("<body>")
                .append("<table border=\"1\">")
                .append("<caption>Сотрудники</caption>")
                .append("<tr>")
                .append("<th>Name</th>")
                .append("<th>Hired</th>")
                .append("<th>Fired</th>")
                .append("<th>Salary</th>")
                .append("</tr>")
                .append("<tr>")
                .append("<td>")
                .append(worker.getName())
                .append("</td>")
                .append("<td>")
                .append(worker.getHired().getTime())
                .append("</td>")
                .append("<td>")
                .append(worker.getFired().getTime())
                .append("</td>")
                .append("<td>")
                .append(worker.getSalary())
                .append("</td>")
                .append("</tr>")
                .append("</table>")
                .append("</body>")
                .append("</html>");
        assertThat(engine.generate(store, em -> true), is(expect.toString()));
    }
}