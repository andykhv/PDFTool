public class PDFTool {
    public static void main (String[] args) {
        final MergerUI mergerUI = new MergerUI();
        final Window window = new Window(mergerUI.getUI());
        window.show();
    }
}
