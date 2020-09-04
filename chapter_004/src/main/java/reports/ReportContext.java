package reports;

import reports.reporttypes.ReportEngine;

import java.util.function.Predicate;

/**
 * Context managing report types
 */
public class ReportContext {
    ReportEngine reportEngine;
    Store employeeStore;

    public ReportContext(ReportEngine reportEngine, Store employeeStore) {
        this.reportEngine = reportEngine;
        this.employeeStore = employeeStore;
    }

    public String createReport(Predicate<Employee> filter) {
        return reportEngine.generate(employeeStore, filter);
    }
}
