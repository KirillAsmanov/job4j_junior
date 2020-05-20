package inputoutput;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AnalyzeTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void diapasonFrom500CenterTo300Last() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            StringBuilder write = new StringBuilder();
            write.append("200 10:00:00")
                    .append(System.lineSeparator())
                    .append("500 10:30:00")
                    .append(System.lineSeparator())
                    .append("300 11:00:00");
            out.println(write);
        }
        Analyze analyze = new Analyze();
        analyze.createUnavailableLog(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:30:00;11:00:00"));
    }

    @Test
    public void diapasonFrom500FirstTo400Last() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            StringBuilder write = new StringBuilder();
            write.append("500 10:00:00")
                    .append(System.lineSeparator())
                    .append("400 10:30:00")
                    .append(System.lineSeparator())
                    .append("400 11:00:00");
            out.println(write);
        }
        Analyze analyze = new Analyze();
        analyze.createUnavailableLog(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:00:00;--:--:--"));
    }

    @Test
    public void diapasonFrom400FirstAnd500Last() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            StringBuilder write = new StringBuilder();
            write.append("400 10:00:00")
                    .append(System.lineSeparator())
                    .append("200 10:30:00")
                    .append(System.lineSeparator())
                    .append("500 11:00:00");
            out.println(write);
        }
        Analyze analyze = new Analyze();
        analyze.createUnavailableLog(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(l -> rsl.append(l).append(System.lineSeparator()));
        }
        assertThat(rsl.toString(), is("10:00:00;10:30:00" + System.lineSeparator()
                + "11:00:00;--:--:--" + System.lineSeparator()));
    }

    @Test
    public void diapasonFrom400SecondAnd500Third() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            StringBuilder write = new StringBuilder();
            write.append("300 10:00:00")
                    .append(System.lineSeparator())
                    .append("400 10:30:00")
                    .append(System.lineSeparator())
                    .append("500 11:00:00")
                    .append(System.lineSeparator())
                    .append("200 11:30:00");
            out.println(write);
        }
        Analyze analyze = new Analyze();
        analyze.createUnavailableLog(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(l -> rsl.append(l).append(System.lineSeparator()));
        }
        assertThat(rsl.toString(), is("10:30:00;11:30:00" + System.lineSeparator()));
    }
}