package examtask;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchFiles extends SimpleFileVisitor {
    private List<Path> foundFiles;
    Predicate<Path> predicate;

    public SearchFiles(Predicate<Path> predicate) {
        this.foundFiles = new ArrayList<>();
        this.predicate = predicate;
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (predicate.test(file)) {
            foundFiles.add(file);
        }
        return CONTINUE;
    }
}
