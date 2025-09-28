import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class DeleteItem extends JMenuItem {
    Mediator med;
    Vector<MyDrawing> deleteDrawings;

    public DeleteItem(StateManager stateManager) {
        super("Delete");

        addActionListener(new DeleteListener());
        this.med = stateManager.canvas.mediator;
        deleteDrawings = new Vector<MyDrawing>();
    }

    class DeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (med.selectedDrawings != null && !med.selectedDrawings.isEmpty()) {
                Enumeration<MyDrawing> en1 = med.selectedDrawings.elements();
                while (en1.hasMoreElements()) {
                    MyDrawing drawing = en1.nextElement();
                    deleteDrawings.add(drawing);
                }
                Enumeration<MyDrawing> en2 = deleteDrawings.elements();
                while (en2.hasMoreElements()) {
                    MyDrawing drawing2 = en2.nextElement();
                    drawing2.setSelected(false);
                    med.selectedDrawings.remove(drawing2);
                    med.removeDrawing(drawing2);
                }
            }
        }
    }
}
