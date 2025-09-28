import javax.swing.*;
import java.awt.event.*;

public class SetShadowButton extends JButton {
    Mediator med;

    public SetShadowButton(StateManager stateManager) {
        super("Setshadow");

        addActionListener(new SetShadowListener());
        this.med = stateManager.canvas.mediator;
    }

    class SetShadowListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (med.selectedDrawings != null && !med.selectedDrawings.isEmpty()) {
                med.setshadow(!med.selectedDrawings.get(0).getshadow());
            }
        }
    }
}
