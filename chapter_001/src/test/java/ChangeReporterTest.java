import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ChangeReporterTest {
    ChangeReporter cr = new ChangeReporter();
    @Test
    public void whenBaseAndChangedAreEmpty() {
        ArrayList<ChangeReporter.Element> base = new ArrayList<>();
        ArrayList<ChangeReporter.Element> changed = new ArrayList<>();
        ChangeReporter.Report report = cr.createReport(base, changed);
        ChangeReporter.Report expected = new ChangeReporter.Report(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        assertEquals(report, expected);
    }

    @Test
    public void whenBaseHasElAndChangedAreEmpty() {
        ArrayList<ChangeReporter.Element> base = new ArrayList<>();
        base.add(new ChangeReporter.Element("1", "A"));
        ArrayList<ChangeReporter.Element> changed = new ArrayList<>();
        ChangeReporter.Report report = cr.createReport(base, changed);

        ChangeReporter.Report expected = new ChangeReporter.Report(
                new ArrayList<>(),
                new ArrayList<>(),
                List.of(new ChangeReporter.Element("1", "A"))
        );
        assertEquals(report, expected);
    }

    @Test
    public void whenBaseEmptyAndChangedHasEl() {
        ArrayList<ChangeReporter.Element> base = new ArrayList<>();
        ArrayList<ChangeReporter.Element> changed = new ArrayList<>();
        changed.add(new ChangeReporter.Element("1", "A"));
        ChangeReporter.Report report = cr.createReport(base, changed);

        ChangeReporter.Report expected = new ChangeReporter.Report(
                List.of(new ChangeReporter.Element("1", "A")),
                new ArrayList<>(),
                new ArrayList<>()
        );
        assertEquals(report, expected);
    }

    @Test
    public void whenChangedModified() {
        ArrayList<ChangeReporter.Element> base = new ArrayList<>();
        base.add(new ChangeReporter.Element("1", "A"));
        ArrayList<ChangeReporter.Element> changed = new ArrayList<>();
        changed.add(new ChangeReporter.Element("1", "B"));

        ChangeReporter.Report report = cr.createReport(base, changed);
        ChangeReporter.Report expected = new ChangeReporter.Report(
                new ArrayList<>(),
                List.of(new ChangeReporter.Element("1", "A")),
                new ArrayList<>()
        );
        assertEquals(expected, report);
    }

    @Test
    public void whenChangedAddedAndModified() {
        ArrayList<ChangeReporter.Element> base = new ArrayList<>();
        base.add(new ChangeReporter.Element("1", "A"));
        ArrayList<ChangeReporter.Element> changed = new ArrayList<>();
        changed.add(new ChangeReporter.Element("1", "B"));
        changed.add(new ChangeReporter.Element("2", "Added"));

        ChangeReporter.Report report = cr.createReport(base, changed);
        ChangeReporter.Report expected = new ChangeReporter.Report(
                List.of(new ChangeReporter.Element("2", "Added")),
                List.of(new ChangeReporter.Element("1", "A")),
                new ArrayList<>()
        );
        assertEquals(expected, report);
    }

    @Test
    public void whenDeletedAddedAndModified() {
        ArrayList<ChangeReporter.Element> base = new ArrayList<>();
        base.add(new ChangeReporter.Element("1", "A"));
        base.add(new ChangeReporter.Element("3", "Deleted"));
        ArrayList<ChangeReporter.Element> changed = new ArrayList<>();
        changed.add(new ChangeReporter.Element("1", "B"));
        changed.add(new ChangeReporter.Element("2", "Added"));

        ChangeReporter.Report report = cr.createReport(base, changed);
        ChangeReporter.Report expected = new ChangeReporter.Report(
                List.of(new ChangeReporter.Element("2", "Added")),
                List.of(new ChangeReporter.Element("1", "A")),
                List.of(new ChangeReporter.Element("3", "Deleted"))
        );
        assertEquals(expected, report);
    }
}