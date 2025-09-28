import javax.swing.*;
import java.awt.event.*;

public class CutItem extends JMenuItem {
    Mediator med;

    public CutItem(StateManager stateManager) {
        super("Cut");

        addActionListener(new CutListener());
        this.med = stateManager.canvas.mediator;
    }

    class CutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (med.selectedDrawings != null && !med.selectedDrawings.isEmpty()) {
                med.cut();
            }
        }
    }
}
