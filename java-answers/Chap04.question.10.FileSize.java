//Chap04.question.10.FileSize.java

import java.io.File;

public class FileSize {
    private static final String rootFolder = ".";

    public static void printSize(int indents, File folder) {
        assert folder != null;
        for (File f : folder.listFiles()) {
            if (f.isDirectory()) {
                System.out.println(f.getPath());
                printSize(indents + 1, f);
            } else {
                for (int i = 0; i < indents; i++)
                    System.out.print(" ");
                System.out.println(f.getName() + " : " + f.length());
            }
        }
    }

    public static void main(String... args) {
        printSize(0, new File(rootFolder));
    }
}
