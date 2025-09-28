import java.awt.*;
import javax.swing.*;

public class LineColorItem {
    Color SelectedColor = null;
    String other = "Other LineColors";
    JFrame jFrame;

    public LineColorItem(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    public LineColorItem(Color Color) {
        SelectedColor = Color;
    }

    public String toString() {
        if (SelectedColor != null) {
            if (SelectedColor.equals(Color.RED)) {
                return "Red Line";
            } else if (SelectedColor.equals(Color.GREEN)) {
                return "Green Line";
            } else if (SelectedColor.equals(Color.BLUE)) {
                return "Blue Line";
            } else if (SelectedColor.equals(Color.WHITE)) {
                return "WHITE Line";
            } else if (SelectedColor.equals(Color.BLACK)) {
                return "BLACK Line";
            } else {
                return SelectedColor.toString();
            }
        } else {
            return other;
        }
    }

    public Color GetColor() {
        if (SelectedColor != null) {
            return SelectedColor;
        } else {
            JColorChooser colorchooser = new JColorChooser();
            return colorchooser.showDialog(this.jFrame, "色の選択", Color.white);
        }
    }
}
