import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OvalButton extends JButton {
    StateManager stateManager;

    public OvalButton(StateManager stateManager) {
        super("Oval");

        addActionListener(new OvalListener());

        this.stateManager = stateManager;
    }

    class OvalListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            stateManager.setState(new OvalState(stateManager));
        }
    }

    class OvalState implements State {
        StateManager stateManager;
        MyOval myOval;
        int x1, y1;

        public OvalState(StateManager stateManager) {
            this.stateManager = stateManager;
        }

        public void mouseDown(int x, int y) {
            x1 = x;
            y1 = y;
            myOval = new MyOval(x1, y1);
            myOval.setSize(0, 0);
            if (stateManager.isshadow) {
                myOval.setshadow(true);
            }
        }

        public void mouseUp(int x, int y) {
        }

        public void mouseDrag(int x, int y) {
            if (stateManager.isshadow) {
                myOval.setshadow(true);
            }
            Color fillcolor = new Color(stateManager.canvas.mediator.CurentColor.getRed(),
                    stateManager.canvas.mediator.CurentColor.getGreen(),
                    stateManager.canvas.mediator.CurentColor.getBlue(), stateManager.canvas.mediator.Curentalpha);
            myOval.setFillColor(fillcolor);
            myOval.setLinewidth(stateManager.canvas.mediator.CurentLinewidth);
            myOval.setLineColor(stateManager.canvas.mediator.CurentLineColor);
            myOval.setSize(x1 - x, y1 - y);
            myOval.setLocation(x, y);
            myOval.setDashed(stateManager.isDashed);
            stateManager.remove(myOval);
            stateManager.addDrawing(myOval);
        }
    }
}
