import javax.swing.JPanel;
import java.awt.Color;

public class MergerUI {
    private JPanel container;

    public MergerUI() {
        this.container = new JPanel();
        this.container.setBackground(Color.gray);
        this.container.setBounds(0, 0, 200, 200);
    }

    public JPanel getContainer() {
        return container;
    }
}
