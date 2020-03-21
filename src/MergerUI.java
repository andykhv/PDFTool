import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;
import java.awt.Dimension;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;

public class MergerUI {
    private static final String SOURCES_TITLE = "PDFs to merge:";
    private static final String SOURCES_ADD_TITLE = "Add";
    private static final String SOURCES_REMOVE_TITLE = "Remove";
    private static final String ARROW_LABEL = "---------------------->";
    private static final String DESTINATION_TITLE = "Destination:";
    private static final String FILE_NAME_TITLE = "File name:";
    private static final String FOLDER_TITLE = "Folder:";
    private static final String DESTINATION_CHOOSE_FOLDER_TITLE = "Choose Folder";
    private static final String SAVE_TITLE = "Save";
    private static final int SOURCES_LIST_WIDTH = 200;
    private static final int SOURCES_LIST_HEIGHT = 250;
    private static final int FILE_NAME_INPUT_WIDTH = 100;
    private static final int FILE_NAME_INPUT_HEIGHT = 20;

    private JPanel ui;

    public MergerUI() {
        final JPanel ui = new JPanel(new MigLayout());

        ui.add(this.buildSourcesUI());
        ui.add(new JLabel(ARROW_LABEL));
        ui.add(this.buildDestinationUI());

        this.ui = ui;
    }

    public JPanel getUI() {
        return this.ui;
    }

    private JPanel buildSourcesUI() {
        final JPanel sourcesUI = new JPanel(new MigLayout());
        final JLabel sourcesLabel = new JLabel(SOURCES_TITLE);
        final JScrollPane sourcesListScrollPane = buildSourcesListScrollPane();
        final JPanel sourcesButtonContainer = buildSourcesButtonContainer();

        sourcesUI.add(sourcesLabel, "wrap"); //wrap denotes end of row
        sourcesUI.add(sourcesListScrollPane, "wrap");
        sourcesUI.add(sourcesButtonContainer);

        return sourcesUI;
    }

    private JScrollPane buildSourcesListScrollPane() {
        final JList<String> sourcesList = new JList<>();
        final JScrollPane sourcesListScrollPane = new JScrollPane(sourcesList);
        sourcesListScrollPane.setPreferredSize(new Dimension(SOURCES_LIST_WIDTH, SOURCES_LIST_HEIGHT));

        return sourcesListScrollPane;
    }

    private JPanel buildSourcesButtonContainer() {
        final JPanel sourcesButtonContainer = new JPanel(new MigLayout());
        final JButton addSourceButton = new JButton(SOURCES_ADD_TITLE);
        final JButton removeSourceButton = new JButton(SOURCES_REMOVE_TITLE);

        sourcesButtonContainer.add(addSourceButton);
        sourcesButtonContainer.add(removeSourceButton);

        return sourcesButtonContainer;
    }

    private JPanel buildDestinationUI() {
        final JPanel destinationUI = new JPanel();
        final JPanel fileNameContainer = buildFileNameContainer();
        final JPanel folderContainer = buildFolderContainer();
        final JLabel destinationLabel = new JLabel(DESTINATION_TITLE);
        final JButton chooseFolderButton = new JButton(DESTINATION_CHOOSE_FOLDER_TITLE);
        final JButton saveButton = new JButton(SAVE_TITLE);

        destinationUI.setLayout(new BoxLayout(destinationUI, BoxLayout.Y_AXIS));
        destinationUI.add(destinationLabel);
        destinationUI.add(fileNameContainer);
        destinationUI.add(folderContainer);
        destinationUI.add(chooseFolderButton);
        destinationUI.add(saveButton);

        return destinationUI;
    }

    private JPanel buildFileNameContainer() {
        final JPanel fileNameContainer = new JPanel(new MigLayout());
        final JLabel fileNameLabel = new JLabel(FILE_NAME_TITLE);
        final JTextField fileNameInput = new JTextField();

        fileNameInput.setPreferredSize(new Dimension(FILE_NAME_INPUT_WIDTH, FILE_NAME_INPUT_HEIGHT));

        fileNameContainer.add(fileNameLabel);
        fileNameContainer.add(fileNameInput);
        fileNameContainer.setBorder(new LineBorder(Color.black));

        return fileNameContainer;
    }

    private JPanel buildFolderContainer() {
        final JPanel folderContainer = new JPanel(new MigLayout());
        final JLabel folderTitle = new JLabel(FOLDER_TITLE);
        final JLabel folderName = new JLabel();

        folderContainer.add(folderTitle);
        folderContainer.add(folderName);
        folderContainer.setBorder(new LineBorder(Color.black));

        return folderContainer;
    }
}
