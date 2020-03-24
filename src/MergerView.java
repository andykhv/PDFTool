import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentListener;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;

public class MergerView {
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

    private JPanel view;
    private JList<PDFFile> sourcesJList;
    private JButton addSourceButton;
    private JButton removeSourcesButton;
    private JButton chooseFolderButton;
    private JTextField fileNameTextView;
    private JLabel folderNameLabel;

    public MergerView(ListModel<PDFFile> sources) {
        final JPanel view = new JPanel(new MigLayout());

        view.add(this.buildSourcesView(sources));
        view.add(new JLabel(ARROW_LABEL));
        view.add(this.buildDestinationView());

        this.view = view;
    }

    public JPanel getView() {
        return this.view;
    }

    public int[] getSourcesSelectedIndices() {
        return this.sourcesJList.getSelectedIndices();
    }

    public String getDestinationFileName() {
        return this.fileNameTextView.getText();
    }

    public void setAddSourceButtonListener(ActionListener listener) {
        this.addSourceButton.addActionListener(listener);
    }

    public void setRemoveSourcesButtonListener(ActionListener listener) {
        this.removeSourcesButton.addActionListener(listener);
    }

    public void setFileNameTextViewListener(DocumentListener listener) {
        this.fileNameTextView.getDocument().addDocumentListener(listener);
    }

    public void setChooseFolderButtonListener(ActionListener listener) {
        this.chooseFolderButton.addActionListener(listener);
    }

    public void setFolderNameLabelText(String text) {
        this.folderNameLabel.setText(text);
    }

    private JPanel buildSourcesView(ListModel<PDFFile> sources) {
        final JPanel sourcesView = new JPanel(new MigLayout());
        final JLabel sourcesLabel = new JLabel(SOURCES_TITLE);
        final JScrollPane sourcesListScrollPane = buildSourcesListScrollPane(sources);
        final JPanel sourcesButtonContainer = buildSourcesButtonContainer();

        sourcesView.add(sourcesLabel, "wrap"); //wrap denotes end of row
        sourcesView.add(sourcesListScrollPane, "wrap");
        sourcesView.add(sourcesButtonContainer);

        return sourcesView;
    }

    private JScrollPane buildSourcesListScrollPane(ListModel<PDFFile> sources) {
        final JList<PDFFile> sourcesList = new JList<>(sources);
        final JScrollPane sourcesListScrollPane = new JScrollPane(sourcesList);
        sourcesListScrollPane.setPreferredSize(new Dimension(SOURCES_LIST_WIDTH, SOURCES_LIST_HEIGHT));

        this.sourcesJList = sourcesList;

        return sourcesListScrollPane;
    }

    private JPanel buildSourcesButtonContainer() {
        final JPanel sourcesButtonContainer = new JPanel(new MigLayout());
        final JButton addSourceButton = new JButton(SOURCES_ADD_TITLE);
        final JButton removeSourcesButton = new JButton(SOURCES_REMOVE_TITLE);

        this.addSourceButton = addSourceButton;
        this.removeSourcesButton = removeSourcesButton;

        sourcesButtonContainer.add(addSourceButton);
        sourcesButtonContainer.add(removeSourcesButton);

        return sourcesButtonContainer;
    }

    private JPanel buildDestinationView() {
        final JPanel destinationView = new JPanel();
        final JPanel fileNameContainer = buildFileNameContainer();
        final JPanel folderContainer = buildFolderContainer();
        final JLabel destinationLabel = new JLabel(DESTINATION_TITLE);
        final JButton chooseFolderButton = new JButton(DESTINATION_CHOOSE_FOLDER_TITLE);
        final JButton saveButton = new JButton(SAVE_TITLE);

        this.chooseFolderButton =  chooseFolderButton;

        destinationView.setLayout(new BoxLayout(destinationView, BoxLayout.Y_AXIS));
        destinationView.add(destinationLabel);
        destinationView.add(fileNameContainer);
        destinationView.add(folderContainer);
        destinationView.add(chooseFolderButton);
        destinationView.add(saveButton);

        return destinationView;
    }

    private JPanel buildFileNameContainer() {
        final JPanel fileNameContainer = new JPanel(new MigLayout());
        final JLabel fileNameLabel = new JLabel(FILE_NAME_TITLE);
        final JTextField fileNameInput = new JTextField();

        this.fileNameTextView = fileNameInput;

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

        this.folderNameLabel = folderName;

        folderContainer.add(folderTitle);
        folderContainer.add(folderName);
        folderContainer.setBorder(new LineBorder(Color.black));

        return folderContainer;
    }
}
