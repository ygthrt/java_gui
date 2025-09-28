import java.awt.*;
import java.awt.geom.Ellipse2D;

public class MyOval extends MyDrawing {
    public MyOval(int xpt, int ypt) {
        super();
        setLocation(xpt, ypt);
    }

    public void draw(Graphics g) {
        int x = getX();
        int y = getY();
        int w = getW();
        int h = getH();

        if (w < 0) {
            x += w;
            w *= -1;
        }

        if (h < 0) {
            y += h;
            h *= -1;
        }

        if (getSelected()) {
            g.setColor(Color.black);
            g.fillRect(x + w / 2 - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
            g.fillRect(x - SIZE / 2, y + h / 2 - SIZE / 2, SIZE, SIZE);
            g.fillRect(x + w / 2 - SIZE / 2, y + h - SIZE / 2, SIZE, SIZE);
            g.fillRect(x + w - SIZE / 2, y + h / 2 - SIZE / 2, SIZE, SIZE);
        }

        Graphics2D g2 = (Graphics2D) g;

        if (getshadow()) {
            g2.setColor(Color.BLACK);
            g2.fillOval(x + 5, y + 5, w, h);
            g2.setColor(Color.BLACK);
            g2.drawOval(x + 5, y + 5, w, h);
        }

        if (getDashed())
            g2.setStroke(new MyDashStroke(getLinewidth()));
        else
            g2.setStroke(new BasicStroke(getLinewidth()));
        g2.setColor(getFillColor());
        g2.fillOval(x, y, w, h);
        g2.setColor(getLineColor());
        g2.drawOval(x, y, w, h);
    }

    public boolean contains(int x, int y) {
        return region.contains(x, y);
    }

    public void setRegion() {
        int x = getX();
        int y = getY();
        int w = getW();
        int h = getH();

        if (w < 0) {
            x += w;
            w *= -1;
        }

        if (h < 0) {
            y += h;
            h *= -1;
        }
        region = new Ellipse2D.Double(x, y, w, h);
    }
}
