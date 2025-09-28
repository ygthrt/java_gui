import java.awt.*;

public class MyDashStroke extends BasicStroke{
    private static float pattern[] = {10, 15};

    public MyDashStroke(float linwidth){
        super(linwidth, CAP_BUTT, JOIN_BEVEL, 1.0f, pattern, 0);
    }
}
