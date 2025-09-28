import java.awt.Color;
import java.util.Enumeration;
import java.util.Vector;

public class Mediator {
    Vector<MyDrawing> drawings;
    MyCanvas canvas;
    Vector<MyDrawing> selectedDrawings;
    Vector<MyDrawing> buffer;
    Color CurentColor = Color.white;
    Color CurentLineColor = Color.BLACK;
    int CurentLinewidth = 1;
    int Curentalpha = 255;

    public Mediator(MyCanvas canvas) {
        this.canvas = canvas;
        drawings = new Vector<MyDrawing>();
        selectedDrawings = new Vector<MyDrawing>();
        buffer = new Vector<MyDrawing>();
    }

    public Enumeration<MyDrawing> drawingsElements() {
        return drawings.elements();
    }

    public void addDrawing(MyDrawing d) {
        drawings.add(d);
        setselectedDrawing(d);
        repaint();
    }

    public void removeDrawing(MyDrawing d) {
        drawings.remove(d);
        repaint();
    }

    public Vector<MyDrawing> getSelectedDrawings() {
        return selectedDrawings;
    }

    public void setselectedDrawing(MyDrawing d) {
        if (selectedDrawings != null && !selectedDrawings.isEmpty()) {
            Enumeration<MyDrawing> e = selectedDrawings.elements();
            while (e.hasMoreElements()) {
                MyDrawing drawing = e.nextElement();
                drawing.setSelected(false);
            }
            selectedDrawings.clear();
        }
        if (d != null) {
            if (selectedDrawings.contains(d)) {
                selectedDrawings.remove(d);
            }
            d.setSelected(true);
            selectedDrawings.add(d);
        }

    }

    public void move(int dx, int dy) {
        if (selectedDrawings != null && !selectedDrawings.isEmpty()) {
            Enumeration<MyDrawing> e = selectedDrawings.elements();
            while (e.hasMoreElements()) {
                MyDrawing drawing = e.nextElement();
                drawing.move(dx, dy);
            }
        }

    }

    public void repaint() {
        canvas.repaint();
    }

    public void setSelected(int x, int y) {
        Enumeration<MyDrawing> e = drawingsElements();
        MyDrawing frontmostSelectedDrawing = null;
        int flag = selectedDrawings.size();
        while (e.hasMoreElements()) {
            MyDrawing d = e.nextElement();
            if (selectedDrawings.size() < 2) {
                if (d.contains(x, y)) {
                    if (frontmostSelectedDrawing != null) {
                        frontmostSelectedDrawing.setSelected(false);
                    }
                    d.setSelected(true);
                    frontmostSelectedDrawing = d;
                } else {
                    d.setSelected(false);
                }
                if (frontmostSelectedDrawing != null) {
                    setselectedDrawing(frontmostSelectedDrawing);
                } else {
                    setselectedDrawing(null);
                }
            } else {
                if (d.contains(x, y)) {
                    if (frontmostSelectedDrawing != null && !selectedDrawings.contains(frontmostSelectedDrawing)) {
                        frontmostSelectedDrawing.setSelected(false);
                    }
                    d.setSelected(true);
                    frontmostSelectedDrawing = d;
                } else if (selectedDrawings.contains(d)) {
                    flag--;
                }
                if (frontmostSelectedDrawing != null && !selectedDrawings.contains(frontmostSelectedDrawing)) {
                    setselectedDrawing(frontmostSelectedDrawing);
                } else if (flag == 0) {
                    setselectedDrawing(null);
                }
            }
        }
        repaint();
    }

    public void setColor(Color color) {
        Color fillcolor = new Color(color.getRed(), color.getGreen(), color.getBlue(), Curentalpha);
        if (selectedDrawings != null && !selectedDrawings.isEmpty()) {
            Enumeration<MyDrawing> e = selectedDrawings.elements();
            while (e.hasMoreElements()) {
                MyDrawing drawing = e.nextElement();
                drawing.setFillColor(fillcolor);
            }
        }
        repaint();
        CurentColor = fillcolor;
    }

    public void setLineColor(Color color) {
        if (selectedDrawings != null && !selectedDrawings.isEmpty()) {
            Enumeration<MyDrawing> e = selectedDrawings.elements();
            while (e.hasMoreElements()) {
                MyDrawing drawing = e.nextElement();
                drawing.setLineColor(color);
            }
        }
        repaint();
        CurentLineColor = color;
    }

    public void setLinewidth(int width) {
        if (selectedDrawings != null && !selectedDrawings.isEmpty()) {
            Enumeration<MyDrawing> e = selectedDrawings.elements();
            while (e.hasMoreElements()) {
                MyDrawing drawing = e.nextElement();
                drawing.setLinewidth(width);
            }
        }
        repaint();
        CurentLinewidth = width;
    }

    public void setshadow(boolean isshadow) {
        if (selectedDrawings != null && !selectedDrawings.isEmpty()) {
            Enumeration<MyDrawing> e = selectedDrawings.elements();
            while (e.hasMoreElements()) {
                MyDrawing drawing = e.nextElement();
                drawing.setshadow(isshadow);
            }
        }
        repaint();
    }

    public void setAlpha(int alpha) {
        if (selectedDrawings != null && !selectedDrawings.isEmpty()) {
            Enumeration<MyDrawing> e = selectedDrawings.elements();
            while (e.hasMoreElements()) {
                MyDrawing drawing = e.nextElement();
                Color fillcolor = new Color(drawing.getFillColor().getRed(), drawing.getFillColor().getGreen(),
                        drawing.getFillColor().getBlue(), alpha);
                drawing.setFillColor(fillcolor);
            }
        }
        repaint();
        Curentalpha = alpha;
    }

    public void clearBuffer() {
        buffer.clear();
    }

    public void copy() {
        clearBuffer();
        Enumeration<MyDrawing> e = selectedDrawings.elements();
        while (e.hasMoreElements()) {
            MyDrawing drawing = e.nextElement().clone();
            buffer.add(drawing);
        }
    }

    public void cut() {
        clearBuffer();
        Enumeration<MyDrawing> e = selectedDrawings.elements();
        while (e.hasMoreElements()) {
            MyDrawing drawing = e.nextElement();
            buffer.add(drawing.clone());
            removeDrawing(drawing);
        }
    }

    public void paste(int x, int y) {
        Enumeration<MyDrawing> e = buffer.elements();
        while (e.hasMoreElements()) {
            MyDrawing clone = e.nextElement().clone();
            clone.setLocation(x, y);
            addDrawing(clone);
        }
        repaint();
    }
}
