import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SaveItem extends JMenuItem {
    Mediator med;

    public SaveItem(StateManager stateManager) {
        super("Save");
        med = stateManager.canvas.mediator;
        addActionListener(new SaveActionListener());
    }

    class SaveActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showSaveDialog(null);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    FileOutputStream fout = new FileOutputStream(file);
                    ObjectOutputStream out = new ObjectOutputStream(fout);
                    out.writeObject(med.drawings);
                    out.flush();
                    fout.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
