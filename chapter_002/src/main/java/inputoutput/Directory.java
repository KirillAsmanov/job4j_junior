package inputoutput;

import java.io.File;

public class Directory {
    /**
     * Displays the name and size of ONLY the files in the directory and all subfolders
     * @param file - path to directory
     */
    public void getAllFiles(File file) {
        for (File subfile : file.listFiles()) {
            if (subfile.isDirectory()) {
                getAllFiles(subfile);
            } else if (subfile.isFile()) {
                System.out.println("File name: " + subfile.getName() + " - size: " + subfile.length() + " bytes");
            }
        }
    }

    public static void main(String[] args) {
        File file = new File("c:\\projects\\job4j_junior");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        Directory dir = new Directory();
        dir.getAllFiles(file);

    }
}
