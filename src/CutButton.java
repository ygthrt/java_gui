import javax.swing.*;
import java.awt.event.*;

public class CutButton extends JButton {
    Mediator med;

    public CutButton(StateManager stateManager) {
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
