package inputoutput.archive;

import inputoutput.search.SimpleFileVisitor;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static java.nio.file.FileVisitResult.CONTINUE;
/**
 * 5.1. Архивировать проект. [#281980]
 * @since 19.05.2020
 * @author Kirill Asmanov
 */
public class Zip {
    /**
     * Packs the files in list to archive
     * @param sources - list of paths
     * @param target - name of archive
     */
    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            for (Path path : sources) {
                Path pathInZip = target.relativize(path);
                zip.putNextEntry(new ZipEntry(pathInZip.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds to list paths of all files in directory
     * @param root path to directory
     * @param ext excluded extension
     * @return list of paths
     * @throws IOException
     */
    public static List<Path> search(Path root, String ext) throws IOException {
        if (!Files.exists(root)) {
            throw new IllegalArgumentException(String.format("Cannot find %s", root.toString()));
        }
        if (!Files.isDirectory(root)) {
            throw new IllegalArgumentException(String.format("Not directory %s", root.toString()));
        }
        List<Path> foundFiles = new ArrayList<>();
        SimpleFileVisitor fileVisitor = new SimpleFileVisitor() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (!file.getFileName().toString().endsWith(ext)) {
                    foundFiles.add(file);
                }
                return CONTINUE;
            }
        };
        Files.walkFileTree(root, fileVisitor);
        return foundFiles;
    }

    public static void main(String[] args) throws IOException {
        ArgZip argZip = new ArgZip(args);
        new Zip().packFiles(
                search(Paths.get(argZip.directory()), argZip.exclude()),
                Paths.get(argZip.output())
        );
    }
}