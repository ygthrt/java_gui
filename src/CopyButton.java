import javax.swing.*;
import java.awt.event.*;

public class CopyButton extends JButton {
    Mediator med;

    public CopyButton(StateManager stateManager) {
        super("Copy");

        addActionListener(new CopyListener());
        this.med = stateManager.canvas.mediator;
    }

    class CopyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (med.selectedDrawings != null && !med.selectedDrawings.isEmpty()) {
                med.copy();
            }
        }
    }

}
