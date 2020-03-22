public class PDFTool {
    public static void main (String[] args) {
        final MergerView mergerView = new MergerView();
        final Window window = new Window(mergerView.getView());
        window.show();
    }
}
