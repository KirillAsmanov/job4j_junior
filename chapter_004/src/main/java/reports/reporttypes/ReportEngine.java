package reports.reporttypes;

import reports.Employee;
import reports.Store;

import java.util.function.Predicate;

public interface ReportEngine {
    /**
     * Generates employee report
     * @param store store wits data
     * @param filter a search criteria
     * @return report string
     */
    String generate(Store store, Predicate<Employee> filter);
}
