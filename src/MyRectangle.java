import java.awt.*;

public class MyRectangle extends MyDrawing {

    public MyRectangle(int xpt, int ypt) {
        super();
        setLocation(xpt, ypt);
    }

    public void draw(Graphics g) {
        super.draw(g);
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

        Graphics2D g2 = (Graphics2D) g;

        if (getshadow()) {
            g2.setColor(Color.BLACK);
            g2.fillRect(x + 5, y + 5, w, h);
            g2.setColor(Color.BLACK);
            g2.drawRect(x + 5, y + 5, w, h);
        }

        if (getDashed())
            g2.setStroke(new MyDashStroke(getLinewidth()));
        else
            g2.setStroke(new BasicStroke(getLinewidth()));
        g2.setColor(getFillColor());
        g2.fillRect(x, y, w, h);
        g2.setColor(getLineColor());
        g2.drawRect(x, y, w, h);
        // Color fillColor = getFillColor();
        // Color lineColor = getLineColor();

        // fillColor = new Color(fillColor.getRed(), fillColor.getGreen(),
        // fillColor.getBlue(), alpha);
        // lineColor = new Color(lineColor.getRed(), lineColor.getGreen(),
        // lineColor.getBlue(), alpha);

        // g2.setColor(fillColor);
        // g2.fillRect(x, y, w, h);
        // g2.setColor(lineColor);
        // g2.drawRect(x, y, w, h);
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
        region = new Rectangle(x, y, w, h);
    }
}
