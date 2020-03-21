import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import net.miginfocom.swing.MigLayout;

public class MergerUI {
    private static final String SOURCES_TITLE = "PDFs to merge:";
    private static final String SOURCES_ADD_TITLE = "Add";
    private static final String SOURCES_REMOVE_TITLE = "Remove";
    private static final String ARROW_LABEL = "---------------------->";
    private static final int SOURCES_LIST_WIDTH = 200;
    private static final int SOURCES_LIST_HEIGHT = 250;

    private JPanel ui;

    public MergerUI() {
        final JPanel ui = new JPanel(new MigLayout());

        ui.add(this.buildSourcesUI());
        ui.add(new JLabel(ARROW_LABEL));

        this.ui = ui;
    }

    public JPanel getUI() {
        return this.ui;
    }

    private JPanel buildSourcesUI() {
        final JPanel sourcesUI = new JPanel(new MigLayout());
        final JPanel sourcesButtonContainer = new JPanel(new MigLayout());
        final JLabel sourcesLabel = new JLabel(SOURCES_TITLE);
        final JList<String> sourcesList = new JList<>();
        final JScrollPane sourcesListScrollPane = new JScrollPane(sourcesList);
        final JButton addSourceButton = new JButton(SOURCES_ADD_TITLE);
        final JButton removeSourceButton = new JButton(SOURCES_REMOVE_TITLE);

        sourcesListScrollPane.setPreferredSize(new Dimension(SOURCES_LIST_WIDTH, SOURCES_LIST_HEIGHT));


        sourcesButtonContainer.add(addSourceButton);
        sourcesButtonContainer.add(removeSourceButton);
        sourcesUI.add(sourcesLabel, "wrap"); //wrap denotes end of row
        sourcesUI.add(sourcesListScrollPane, "wrap");
        sourcesUI.add(sourcesButtonContainer);

        return sourcesUI;
    }
}
