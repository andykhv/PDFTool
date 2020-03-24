import javax.swing.DefaultListModel;

public class PDFTool {
    public static void main (String[] args) {
        final DefaultListModel<PDFFile> sources = new DefaultListModel<>();
        final MergerView mergerView = new MergerView(sources);
        final MergerController mergerController = new MergerController(mergerView, sources);
        final Window window = new Window(mergerView.getView());

        window.show();
    }
}
