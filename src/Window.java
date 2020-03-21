import javax.swing.JFrame;
import javax.swing.JComponent;

public class Window {
    private static final String TITLE = "PDFTool";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 400;
    private JFrame window;

    public Window(JComponent pdfToolContainer) {
        this.window = new JFrame(TITLE);
        this.window.setSize(WIDTH, HEIGHT);
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
