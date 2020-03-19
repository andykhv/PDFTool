import javax.swing.JFrame;
import javax.swing.JComponent;

public class Window {
    private JFrame window;
    private int dimension = 400;
    private String windowTitle = "PDFTool";

    public Window(JComponent pdfToolContainer) {
        this.window = new JFrame(windowTitle);
        this.window.setSize(dimension, dimension);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.add(pdfToolContainer);
    }

    public void show() {
        this.window.setVisible(true);
    }

    public void close() {
        this.window.setVisible(false);
    }
}
