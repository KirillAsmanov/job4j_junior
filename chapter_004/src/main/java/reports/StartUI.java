package reports;

import reports.reporttypes.HtmlReportEngine;
import reports.reporttypes.ReportEngine;

import java.util.Calendar;

public class StartUI {
    public static void main(String[] args) {
        MemStore store = new MemStore();
        store.add(new Employee("Ivan", Calendar.getInstance(), Calendar.getInstance(), 100));
        store.add(new Employee("Nikolay", Calendar.getInstance(), Calendar.getInstance(), 400));
        store.add(new Employee("Kirill", Calendar.getInstance(), Calendar.getInstance(), 350));
        ReportEngine reportEngine = new HtmlReportEngine();
        ReportContext reportContext = new ReportContext(reportEngine, store);
        System.out.println(reportContext.createReport(em -> true));
    }
}
