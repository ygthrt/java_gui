import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;

public class SelectButton extends JButton {
    StateManager stateManager;

    public SelectButton(StateManager stateManager) {
        super("Select");

        addActionListener(new SelectListener());

        this.stateManager = stateManager;
    }

    class SelectListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            stateManager.setState(new SelectState(stateManager));
        }
    }

    class SelectState implements State {
        StateManager stateManager;
        int x1, y1, x2, y2;
        MyRectangle myRectangle = null;
        Rectangle rect;

        public SelectState(StateManager stateManager) {
            this.stateManager = stateManager;
        }

        public void mouseDown(int x, int y) {
            x1 = x;
            y1 = y;
            x2 = x;
            y2 = y;
            stateManager.canvas.mediator.setSelected(x, y);
            if (stateManager.canvas.mediator.selectedDrawings != null
                    && stateManager.canvas.mediator.selectedDrawings.isEmpty()) {
                myRectangle = new MyRectangle(x2, y2);
                myRectangle.setSize(0, 0);
                myRectangle.setDashed(true);
                myRectangle.setLineColor(Color.BLACK);
                myRectangle.setFillColor(null);
                myRectangle.setLinewidth(1);
            }

        }

        public void mouseUp(int x, int y) {
            if (myRectangle != null) {
                myRectangle.setSelected(false);
                stateManager.canvas.mediator.selectedDrawings.remove(myRectangle);
                stateManager.canvas.mediator.removeDrawing(myRectangle);
                myRectangle = null;
            }
        }

        public void mouseDrag(int x, int y) {
            if (stateManager.canvas.mediator.selectedDrawings != null
                    && !stateManager.canvas.mediator.selectedDrawings.isEmpty() && myRectangle == null) {
                stateManager.canvas.mediator.move(x - x1, y - y1);
                x1 = x;
                y1 = y;
                stateManager.canvas.mediator.repaint();
            } else if (myRectangle != null) {
                myRectangle.setSize(x2 - x, y2 - y);
                myRectangle.setLocation(x, y);
                myRectangle.setSelected(false);
                stateManager.canvas.mediator.selectedDrawings.clear();
                stateManager.remove(myRectangle);
                stateManager.addDrawing(myRectangle);
                int startX = Math.min(x, x2);
                int startY = Math.min(y, y2);
                int endX = Math.max(x, x2);
                int endY = Math.max(y, y2);
                Enumeration<MyDrawing> e = stateManager.canvas.mediator.drawingsElements();
                while (e.hasMoreElements()) {
                    MyDrawing d = e.nextElement();
                    d.setSelected(false);
                    for (int a = startX; a < endX; a++) {
                        for (int b = startY; b < endY; b++) {
                            if (d.contains(a, b)) {
                                if (!stateManager.canvas.mediator.selectedDrawings.contains(d)) {
                                    stateManager.canvas.mediator.selectedDrawings.add(d);
                                    d.setSelected(true);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
