import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.*;

public class RoadItem extends JMenuItem {
    Mediator med;

    public RoadItem(StateManager stateManager) {
        super("Road");
        med = stateManager.canvas.mediator;
        addActionListener(new RoadActionListener());
    }

    class RoadActionListener implements ActionListener {
        @SuppressWarnings("unchecked")
        public void actionPerformed(ActionEvent e) {
            try {
                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(null);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    FileInputStream fin = new FileInputStream(file);

                    ObjectInputStream in = new ObjectInputStream(fin);
                    med.drawings = (Vector<MyDrawing>) in.readObject();
                    med.repaint();
                    fin.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}