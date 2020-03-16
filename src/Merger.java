import org.apache.pdfbox.multipdf.PDFMergerUtility;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Merger {
    public static void main(String[] args) {
        File pdf1 = new File("absolute path to pdf");
        File pdf2 = new File("absolute path to second pdf");

        PDFMergerUtility merger = new PDFMergerUtility();
        merger.setDestinationFileName("absolute path to destination");
          
        /* add pdf sources to merger, then merge
         * catch necessary exceptions
         */
        try {
            merger.addSource(pdf1);
            merger.addSource(pdf2);
            merger.mergeDocuments(null); //null allows merger to use unrestricted main memory
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
