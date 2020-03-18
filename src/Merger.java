import org.apache.pdfbox.multipdf.PDFMergerUtility;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

/* wraps Apache's PDFMergerUtility */
public class Merger {
    public static void merge(List<String> pdfFullPaths, String destination) {
        PDFMergerUtility merger = new PDFMergerUtility();
        merger.setDestinationFileName(destination);
        addSources(merger, pdfFullPaths);
        merge(merger);
    }

    private static void addSources(PDFMergerUtility merger, List<String> sources) {
        try {
            for (String fullPathToPdf : sources) {
                merger.addSource(fullPathToPdf);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void merge(PDFMergerUtility merger) {
        try {
            merger.mergeDocuments(null); //null allows merger to use unrestricted main memory
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
