import javax.swing.*;
import java.awt.event.*;

public class PasteItem extends JMenuItem {
    Mediator med;
    int x, y;

    public PasteItem(StateManager stateManager) {
        super("Paste");

        addActionListener(new PasteListener());
        this.med = stateManager.canvas.mediator;

        this.x = 0;
        this.y = 0;
    }

    class PasteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (med.buffer != null && !med.buffer.isEmpty()) {
                med.paste(x, y);
            }
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
