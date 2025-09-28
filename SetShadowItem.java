import javax.swing.*;
import java.awt.event.*;

public class SetShadowItem extends JMenuItem {
    Mediator med;

    public SetShadowItem(StateManager stateManager) {
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
