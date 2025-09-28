import javax.swing.*;
import java.awt.event.*;

public class PasteButton extends JButton {
    Mediator med;
    int shift = 100;
    int x = 0;
    int y = shift;

    public PasteButton(StateManager stateManager) {
        super("Paste");

        addActionListener(new PasteListener());
        this.med = stateManager.canvas.mediator;
    }

    class PasteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            x += shift;
            y += shift;
            if (x > shift * 7) {
                x = 0;
                y += shift;
            }
            if (y > shift * 5) {
                x += shift;
                y = shift;
            }
            if (med.selectedDrawings != null && !med.selectedDrawings.isEmpty()) {
                med.paste(x, y);
            }
        }
    }
}
