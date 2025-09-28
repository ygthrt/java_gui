public class StateManager {
    public State state;
    public MyCanvas canvas;
    public boolean isDashed;
    public boolean isshadow;
    public boolean isSelected;

    public StateManager(MyCanvas canvas) {
        this.canvas = canvas;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setDashed(boolean isDashed) {
        this.isDashed = isDashed;
    }

    public boolean getDashed() {
        return isDashed;
    }

    public void setshadow(boolean isshadow) {
        this.isshadow = isshadow;
    }

    public boolean getshadow() {
        return isshadow;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public boolean getSelected() {
        return isSelected;
    }

    public void mouseDown(int x, int y) {
        state.mouseDown(x, y);
    }

    public void mouseUp(int x, int y) {
        state.mouseUp(x, y);
    }

    public void mouseDrag(int x, int y) {
        state.mouseDrag(x, y);
    }

    public void addDrawing(MyDrawing d) {
        canvas.mediator.addDrawing(d);
    }

    public void remove(MyDrawing d) {
        canvas.mediator.removeDrawing(d);
    }
}
