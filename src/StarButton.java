import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StarButton extends JButton {
    StateManager stateManager;

    public StarButton(StateManager stateManager) {
        super("Star");

        addActionListener(new StarListener());

        this.stateManager = stateManager;
    }

    class StarListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            stateManager.setState(new StarState(stateManager));
        }
    }

    class StarState implements State {
        StateManager stateManager;
        MyStar myStar;
        int x1, y1;

        public StarState(StateManager stateManager) {
            this.stateManager = stateManager;
        }

        public void mouseDown(int x, int y) {
            x1 = x;
            y1 = y;
            myStar = new MyStar(x1, y1);
            myStar.setSize(0, 0);
            if (stateManager.isshadow) {
                myStar.setshadow(true);
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
                myStar.setshadow(true);
            }
            Color fillcolor = new Color(stateManager.canvas.mediator.CurentColor.getRed(),
                    stateManager.canvas.mediator.CurentColor.getGreen(),
                    stateManager.canvas.mediator.CurentColor.getBlue(), stateManager.canvas.mediator.Curentalpha);
            myStar.setFillColor(fillcolor);
            myStar.setLinewidth(stateManager.canvas.mediator.CurentLinewidth);
            myStar.setLineColor(stateManager.canvas.mediator.CurentLineColor);
            myStar.setSize(x1 - x, y1 - y);
            myStar.setLocation(xpt, ypt);
            myStar.setDashed(stateManager.isDashed);
            stateManager.remove(myStar);
            stateManager.addDrawing(myStar);
        }
    }
}
