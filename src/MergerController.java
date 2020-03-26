import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MergerController {
    private static final String PDF_FILTER_TITLE = "PDF File";
    private static final String PDF_EXTENSION = ".pdf";
    private static final String ERROR_TITLE = "Error";
    private static final String EMPTY_FIELDS_MESSAGE = "1 or more fields are missing";
    private static final String DESTINATION_FILE_NAME_TAKEN_MESSAGE = "Name is taken";

    private MergerView view;
    private DefaultListModel<PDFFile> sources;
    private String destinationFileName;
    private String destinationFolderPath;

    public MergerController(MergerView view, DefaultListModel<PDFFile> sources) {
        this.view = view;
        this.sources = sources;

        this.view.setAddSourceButtonListener(new AddSourceButtonListener());
        this.view.setRemoveSourcesButtonListener(new RemoveSourcesButtonListener());
        this.view.setFileNameTextViewListener(new FileNameTextViewListener());
        this.view.setChooseFolderButtonListener(new ChooseFolderButtonListener());
        this.view.setMergeButtonListener(new MergeButtonListener());
    }

    public class AddSourceButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            final JFileChooser fileChooser = new JFileChooser();
            final FileNameExtensionFilter fileFilter = new FileNameExtensionFilter(PDF_FILTER_TITLE, "pdf");
            fileChooser.setFileFilter(fileFilter);

            int returnVal = fileChooser.showOpenDialog(view.getView());

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                final File pdf = fileChooser.getSelectedFile();
                sources.addElement(new PDFFile(pdf));
            }
        }
    }

    public class RemoveSourcesButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            final int[] selectedSourceIndices = view.getSourcesSelectedIndices();

            for (int i = 0; i < selectedSourceIndices.length; i++) {
                sources.removeElementAt(selectedSourceIndices[i] - i);
            }
        }
    }

    public class FileNameTextViewListener implements DocumentListener {
        public void changedUpdate(DocumentEvent e) {
            update();
        }

        public void removeUpdate(DocumentEvent e) {
            update();
        }

        public void insertUpdate(DocumentEvent e) {
            update();
        }

        private void update() {
            destinationFileName = view.getDestinationFileName();
        }
    }

    public class ChooseFolderButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser folderChooser = new JFileChooser();
            folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            folderChooser.setAcceptAllFileFilterUsed(false);

            int returnVal = folderChooser.showOpenDialog(view.getView());

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                destinationFolderPath = folderChooser.getSelectedFile().getPath();
                view.setFolderNameLabelText(destinationFolderPath);
            }
        }
    }

    public class MergeButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (areFieldsEmpty()) {
                JOptionPane.showMessageDialog(view.getView(),
                    EMPTY_FIELDS_MESSAGE,
                    ERROR_TITLE,
                    JOptionPane.ERROR_MESSAGE);
            } else if (isDestinationFileNameTaken()) {
                JOptionPane.showMessageDialog(view.getView(),
                    DESTINATION_FILE_NAME_TAKEN_MESSAGE,
                    ERROR_TITLE,
                    JOptionPane.ERROR_MESSAGE);
            } else {
                merge();
            }
        }

        private boolean areFieldsEmpty() {
            return (destinationFolderPath == null)
                || (destinationFileName == null)
                || (destinationFileName.equals(""))
                || (sources.size() == 0);
        }

        private boolean isDestinationFileNameTaken() {
            File directory = new File(destinationFolderPath);
            String destinationFileNamePDF = destinationFileName + PDF_EXTENSION;

            for (String fileName : directory.list()) {
                if (destinationFileNamePDF.equals(fileName)) {
                    return true;
                }
            }

            return false;
        }

        private void merge() {
            List<File> sourceList = Arrays.stream(sources.toArray())
                .map(source -> ((PDFFile)source).getFile())
                .collect(Collectors.toList());
            Merger.merge(sourceList, destinationFolderPath +
                "/" + destinationFileName + PDF_EXTENSION);
        }
    }
}
