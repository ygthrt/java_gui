import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HendButton extends JButton {
    StateManager stateManager;

    public HendButton(StateManager stateManager) {
        super("Hend");

        addActionListener(new HendListener());

        this.stateManager = stateManager;
    }

    class HendListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            stateManager.setState(new HendState(stateManager));
        }
    }

    class HendState implements State {
        StateManager stateManager;
        MyHendecagonal myHendecagonal;
        int x1, y1;

        public HendState(StateManager stateManager) {
            this.stateManager = stateManager;
        }

        public void mouseDown(int x, int y) {
            x1 = x;
            y1 = y;
            myHendecagonal = new MyHendecagonal(x1, y1);
            myHendecagonal.setSize(0, 0);
            if (stateManager.isshadow) {
                myHendecagonal.setshadow(true);
            }
        }

        public void mouseUp(int x, int y) {
        }

        public void mouseDrag(int x, int y) {
            int xpt, ypt;
            if (x1 > x)
                xpt = x1;
            else
                xpt = x;

            if (y1 > y)
                ypt = y1;
            else
                ypt = y;

            if (stateManager.isshadow) {
                myHendecagonal.setshadow(true);
            }
            Color fillcolor = new Color(stateManager.canvas.mediator.CurentColor.getRed(),
                    stateManager.canvas.mediator.CurentColor.getGreen(),
                    stateManager.canvas.mediator.CurentColor.getBlue(), stateManager.canvas.mediator.Curentalpha);
            myHendecagonal.setFillColor(fillcolor);
            myHendecagonal.setLinewidth(stateManager.canvas.mediator.CurentLinewidth);
            myHendecagonal.setLineColor(stateManager.canvas.mediator.CurentLineColor);
            myHendecagonal.setSize(x1 - x, y1 - y);
            myHendecagonal.setLocation(xpt, ypt);
            myHendecagonal.setDashed(stateManager.isDashed);
            stateManager.remove(myHendecagonal);
            stateManager.addDrawing(myHendecagonal);
        }
    }
}
