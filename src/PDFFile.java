import java.io.File;

/* wrapper class for java's File class
 * return a File's name instead of path for toString()
 */
public class PDFFile {
    private File file;

    public PDFFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return this.file;
    }

    @Override
    public String toString() {
        return this.file.getName();
    }
}
