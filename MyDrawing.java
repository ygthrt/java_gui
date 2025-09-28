import java.awt.*;
import java.io.*;

public class MyDrawing implements Cloneable, Serializable {
    private int x, y, w, h;
    private Color LineColor, fillColor;
    private int Linewidth;
    private boolean isDashed;
    private boolean isshadow;
    private boolean isSelected;
    Shape region;
    final int SIZE = 7;

    public MyDrawing() {
        x = y = 0;
        w = h = 40;
        LineColor = Color.black;
        fillColor = Color.white;
        Linewidth = 1;
        setRegion();
    }

    public MyDrawing(int w, int h, Color lineColor, Color fillColor) {
        x = y = 0;
        this.w = w;
        this.h = h;
        this.LineColor = lineColor;
        this.fillColor = fillColor;
        Linewidth = 1;
        setRegion();
    }

    public void draw(Graphics g) {
        if (isSelected) {
            g.setColor(Color.black);
            g.fillRect(x + w / 2 - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
            g.fillRect(x - SIZE / 2, y + h / 2 - SIZE / 2, SIZE, SIZE);
            g.fillRect(x + w / 2 - SIZE / 2, y + h - SIZE / 2, SIZE, SIZE);
            g.fillRect(x + w - SIZE / 2, y + h / 2 - SIZE / 2, SIZE, SIZE);
            g.fillRect(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
            g.fillRect(x + w - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
            g.fillRect(x - SIZE / 2, y + h - SIZE / 2, SIZE, SIZE);
            g.fillRect(x + w - SIZE / 2, y + h - SIZE / 2, SIZE, SIZE);
        }
    }

    public boolean contains(int x, int y) {
        return false;
    }

    public void setRegion() {
    }

    public void move(int dx, int dy) {
        this.x = this.x + dx;
        this.y = this.y + dy;
        setRegion();
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
        setRegion();
    }

    public void setSize(int w, int h) {
        this.w = w;
        this.h = h;
        setRegion();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public Color getLineColor() {
        return LineColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public int getLinewidth() {
        return Linewidth;
    }

    public void setLineColor(Color lineColor) {
        this.LineColor = lineColor;
    }

    public void setFillColor(Color fillColor) {
        if (fillColor != null) {
            this.fillColor = fillColor;
        } else {
            Color cleaColor = new Color(0, 0, 0, 0);
            this.fillColor = cleaColor;
        }
    }

    public void setLinewidth(int linewidth) {
        Linewidth = linewidth;
    }

    public boolean getDashed() {
        return isDashed;
    }

    public void setDashed(boolean b) {
        isDashed = b;
    }

    public boolean getshadow() {
        return isshadow;
    }

    public void setshadow(boolean b) {
        isshadow = b;
    }

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public MyDrawing clone() {
        try {
            MyDrawing cloned = (MyDrawing) super.clone();
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }

    }
}
