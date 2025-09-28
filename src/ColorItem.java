import java.awt.*;
import javax.swing.*;

public class ColorItem {
    Color SelectedColor = null;
    String other = "Other Colors";
    JFrame jFrame;

    public ColorItem(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    public ColorItem(Color Color) {
        SelectedColor = Color;
    }

    public String toString() {
        if (SelectedColor != null) {
            if (SelectedColor.equals(Color.RED)) {
                return "Red";
            } else if (SelectedColor.equals(Color.GREEN)) {
                return "Green";
            } else if (SelectedColor.equals(Color.BLUE)) {
                return "Blue";
            } else if (SelectedColor.equals(Color.WHITE)) {
                return "WHITE";
            } else if (SelectedColor.equals(Color.BLACK)) {
                return "BLACK";
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
