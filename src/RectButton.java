import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RectButton extends JButton {
    StateManager stateManager;

    public RectButton(StateManager stateManager) {
        super("Rectangle");
        addActionListener(new RectListener());

        this.stateManager = stateManager;
        stateManager.setState(new RectState(stateManager));
    }

    class RectListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            stateManager.setState(new RectState(stateManager));
        }
    }

    class RectState implements State {
        StateManager stateManager;
        MyRectangle myRectangle;
        int x1, y1;

        public RectState(StateManager stateManager) {
            this.stateManager = stateManager;
        }

        public void mouseDown(int x, int y) {
            x1 = x;
            y1 = y;
            myRectangle = new MyRectangle(x1, y1);
            myRectangle.setSize(0, 0);
            if (stateManager.isshadow) {
                myRectangle.setshadow(true);
            }
        }

        public void mouseUp(int x, int y) {
        }

        public void mouseDrag(int x, int y) {
            if (stateManager.isshadow) {
                myRectangle.setshadow(true);
            }
            Color fillcolor = new Color(stateManager.canvas.mediator.CurentColor.getRed(),
                    stateManager.canvas.mediator.CurentColor.getGreen(),
                    stateManager.canvas.mediator.CurentColor.getBlue(), stateManager.canvas.mediator.Curentalpha);
            myRectangle.setFillColor(fillcolor);
            myRectangle.setLinewidth(stateManager.canvas.mediator.CurentLinewidth);
            myRectangle.setLineColor(stateManager.canvas.mediator.CurentLineColor);
            myRectangle.setSize(x1 - x, y1 - y);
            myRectangle.setLocation(x, y);
            myRectangle.setDashed(stateManager.isDashed);
            stateManager.remove(myRectangle);
            stateManager.addDrawing(myRectangle);
        }
    }
}
