import java.awt.*;

public class MyHendecagonal extends MyDrawing {
    public MyHendecagonal(int xpt, int ypt) {
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

        int n = 11;
        int[] xpts = new int[n];
        int[] ypts = new int[n];
        int[] xpts2 = new int[n];
        int[] ypts2 = new int[n];
        double delta = 2 * Math.PI / n;
        int r = Math.max(w, h);

        for (int i = 0; i < n; i++) {
            xpts[i] = (int) (Math.cos(-Math.PI / 2 + delta * i) * r) + x;
            ypts[i] = (int) (Math.sin(-Math.PI / 2 + delta * i) * r) + y;
            xpts2[i] = (int) (Math.cos(-Math.PI / 2 + delta * i) * r) + x + 5;
            ypts2[i] = (int) (Math.sin(-Math.PI / 2 + delta * i) * r) + y + 5;
        }

        if (getSelected()) {
            for (int i = 0; i < n; i++) {
                g.setColor(Color.black);
                g.fillRect(xpts[i] - SIZE / 2, ypts[i] - SIZE / 2, SIZE, SIZE);
            }
        }

        Graphics2D g2 = (Graphics2D) g;

        if (getshadow()) {
            g2.setColor(Color.BLACK);
            g2.fillPolygon(xpts2, ypts2, n);
            g2.setColor(Color.BLACK);
            g2.drawPolygon(xpts2, ypts2, n);
        }

        if (getDashed())
            g2.setStroke(new MyDashStroke(getLinewidth()));
        else
            g2.setStroke(new BasicStroke(getLinewidth()));
        g2.setColor(getFillColor());
        g2.fillPolygon(xpts, ypts, n);
        g2.setColor(getLineColor());
        g2.drawPolygon(xpts, ypts, n);
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

        int n = 11;
        int[] xpts = new int[n];
        int[] ypts = new int[n];
        double delta = 2 * Math.PI / n;
        int r = Math.max(w, h);

        for (int i = 0; i < n; i++) {
            xpts[i] = (int) (Math.cos(-Math.PI / 2 + delta * i) * r) + x;
            ypts[i] = (int) (Math.sin(-Math.PI / 2 + delta * i) * r) + y;
        }
        region = new Polygon(xpts, ypts, n);
    }
}
