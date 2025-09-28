import java.awt.*;

public class MyStar extends MyDrawing {
    public MyStar(int xpt, int ypt) {
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

        int n = 5;
        int[] xpts = new int[n];
        int[] ypts = new int[n];
        int[] xpts2 = new int[n];
        int[] ypts2 = new int[n];
        double delta = 2 * Math.PI / n;
        int r = Math.max(w, h);

        for (int i = 0; i < n; i++) {
            xpts[i] = (int) (-Math.cos(Math.PI / 2 + delta * i) * r) + x;
            ypts[i] = (int) (-Math.sin(Math.PI / 2 + delta * i) * r) + y;
            xpts2[i] = (int) (Math.cos(Math.PI / 2 + delta * i) * r / 2) + x;
            ypts2[i] = (int) (Math.sin(Math.PI / 2 + delta * i) * r / 2) + y;
        }

        int[] xpts3 = { xpts[0], xpts2[3], xpts[1], xpts2[4], xpts[2], xpts2[0], xpts[3], xpts2[1], xpts[4], xpts2[2] };
        int[] ypts3 = { ypts[0], ypts2[3], ypts[1], ypts2[4], ypts[2], ypts2[0], ypts[3], ypts2[1], ypts[4], ypts2[2] };

        int[] xpts4 = { xpts[0] + 5, xpts2[3] + 5, xpts[1] + 5, xpts2[4] + 5, xpts[2] + 5, xpts2[0] + 5, xpts[3] + 5,
                xpts2[1] + 5, xpts[4] + 5, xpts2[2] + 5 };
        int[] ypts4 = { ypts[0] + 5, ypts2[3] + 5, ypts[1] + 5, ypts2[4] + 5, ypts[2] + 5, ypts2[0] + 5, ypts[3] + 5,
                ypts2[1] + 5, ypts[4] + 5, ypts2[2] + 5 };

        if (getSelected()) {
            for (int i = 0; i < 10; i++) {
                g.setColor(Color.black);
                g.fillRect(xpts3[i] - SIZE / 2, ypts3[i] - SIZE / 2, SIZE, SIZE);
            }
        }

        Graphics2D g2 = (Graphics2D) g;

        if (getshadow()) {
            g2.setColor(Color.BLACK);
            g2.fillPolygon(xpts4, ypts4, 10);
            ;
            g2.setColor(Color.BLACK);
            g2.drawPolygon(xpts4, ypts4, 10);
            ;
        }

        if (getDashed())
            g2.setStroke(new MyDashStroke(getLinewidth()));
        else
            g2.setStroke(new BasicStroke(getLinewidth()));
        g2.setColor(getFillColor());
        g2.fillPolygon(xpts3, ypts3, 10);
        g2.setColor(getLineColor());
        g2.drawPolygon(xpts3, ypts3, 10);
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

        int n = 5;
        int[] xpts = new int[n];
        int[] ypts = new int[n];
        int[] xpts2 = new int[n];
        int[] ypts2 = new int[n];
        double delta = 2 * Math.PI / n;
        int r = Math.max(w, h);

        for (int i = 0; i < n; i++) {
            xpts[i] = (int) (-Math.cos(Math.PI / 2 + delta * i) * r) + x;
            ypts[i] = (int) (-Math.sin(Math.PI / 2 + delta * i) * r) + y;
            xpts2[i] = (int) (Math.cos(Math.PI / 2 + delta * i) * r / 2) + x;
            ypts2[i] = (int) (Math.sin(Math.PI / 2 + delta * i) * r / 2) + y;
        }

        int[] xpts3 = { xpts[0], xpts2[3], xpts[1], xpts2[4], xpts[2], xpts2[0], xpts[3], xpts2[1], xpts[4], xpts2[2] };
        int[] ypts3 = { ypts[0], ypts2[3], ypts[1], ypts2[4], ypts[2], ypts2[0], ypts[3], ypts2[1], ypts[4], ypts2[2] };

        region = new Polygon(xpts3, ypts3, 10);
    }
}
