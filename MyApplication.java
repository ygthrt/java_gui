import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MyApplication extends JFrame {
    StateManager stateManager;
    MyCanvas canvas;

    public MyApplication() {
        super("My Paint Application");

        canvas = new MyCanvas();
        canvas.setBackground(Color.white);

        canvas.setFocusable(true);

        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout());

        stateManager = new StateManager(canvas);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new SaveItem(stateManager));
        fileMenu.add(new RoadItem(stateManager));
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);

        RectButton rectButton = new RectButton(stateManager);
        jp.add(rectButton);
        OvalButton ovalButton = new OvalButton(stateManager);
        jp.add(ovalButton);
        HendButton hendButton = new HendButton(stateManager);
        jp.add(hendButton);
        StarButton starButton = new StarButton(stateManager);
        jp.add(starButton);
        SelectButton selectButton = new SelectButton(stateManager);
        jp.add(selectButton);
        // DeleteButton deleteButton = new DeleteButton(stateManager);
        // jp.add(deleteButton);
        // CopyButton copyButton = new CopyButton(stateManager);
        // jp.add(copyButton);
        // CutButton cutButton = new CutButton(stateManager);
        // jp.add(cutButton);
        // PasteButton pasteButton = new PasteButton(stateManager);
        // jp.add(pasteButton);
        // SetShadowButton setShadowButton = new SetShadowButton(stateManager);
        // jp.add(setShadowButton);
        JCheckBox shadowCheck = new JCheckBox("shadow");
        shadowCheck.addItemListener(new ShadowCheckListener(stateManager));
        jp.add(shadowCheck);
        JCheckBox dashCheck = new JCheckBox("dash");
        dashCheck.addItemListener(new DashCheckListener(stateManager));
        jp.add(dashCheck);

        JComboBox ColorChoise = new JComboBox<ColorItem>();
        ColorChoise.addItem(new ColorItem(Color.WHITE));
        ColorChoise.addItem(new ColorItem(Color.RED));
        ColorChoise.addItem(new ColorItem(Color.GREEN));
        ColorChoise.addItem(new ColorItem(Color.BLUE));
        ColorChoise.addItem(new ColorItem(Color.BLACK));
        ColorChoise.addItem(new ColorItem(this));
        ColorChoise.addActionListener(new ColorChoiseListener(canvas.mediator, ColorChoise));
        jp.add(ColorChoise);

        JComboBox LineColorChoise = new JComboBox<ColorItem>();
        LineColorChoise.addItem(new LineColorItem(Color.BLACK));
        LineColorChoise.addItem(new LineColorItem(Color.RED));
        LineColorChoise.addItem(new LineColorItem(Color.GREEN));
        LineColorChoise.addItem(new LineColorItem(Color.BLUE));
        LineColorChoise.addItem(new LineColorItem(Color.WHITE));
        LineColorChoise.addItem(new LineColorItem(this));
        LineColorChoise.addActionListener(new LineColorChoiseListener(canvas.mediator, LineColorChoise));
        jp.add(LineColorChoise);

        JComboBox LinewidthChoise = new JComboBox<ColorItem>();
        LinewidthChoise.addItem(1);
        LinewidthChoise.addItem(2);
        LinewidthChoise.addItem(3);
        LinewidthChoise.addItem(4);
        LinewidthChoise.addActionListener(new LinewidthChoiseListener(canvas.mediator, LinewidthChoise));
        jp.add(LinewidthChoise);

        JSlider alphaSlider = new JSlider(0, 255, 255);
        alphaSlider.setPaintTicks(false);
        alphaSlider.addChangeListener(new alphaListener(stateManager, alphaSlider));
        JLabel alphaLabel = new JLabel("透明度");
        JPanel sliderPanel = new JPanel();
        sliderPanel.add(alphaLabel);
        sliderPanel.add(alphaSlider);
        jp.add(sliderPanel);

        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.add(new DeleteItem(stateManager));
        popupMenu.add(new CopyItem(stateManager));
        popupMenu.add(new CutItem(stateManager));
        PasteItem pasteItem = new PasteItem(stateManager);
        popupMenu.add(pasteItem);
        popupMenu.add(new SetShadowItem(stateManager));

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(jp, BorderLayout.NORTH);
        getContentPane().add(canvas, BorderLayout.CENTER);

        MyKeyListener myKeyListener = new MyKeyListener(canvas);
        canvas.addKeyListener(myKeyListener);

        canvas.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (!SwingUtilities.isRightMouseButton(e)) {
                    stateManager.mouseDown(e.getX(), e.getY());
                }
            }

            public void mouseReleased(MouseEvent e) {
                canvas.requestFocusInWindow();
                myKeyListener.x = e.getX();
                myKeyListener.y = e.getY();
                if (!SwingUtilities.isRightMouseButton(e)) {
                    stateManager.mouseUp(e.getX(), e.getY());
                } else {
                    pasteItem.setX(e.getX());
                    pasteItem.setY(e.getY());
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    return;
                }
                stateManager.mouseDrag(e.getX(), e.getY());
            }
        });

        this.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

        });
    }

    public Dimension getPreferredSize() {
        return new Dimension(1200, 800);
    }

    public static void main(String[] args) {
        MyApplication app = new MyApplication();
        app.pack();
        app.setVisible(true);
    }

    class ShadowCheckListener implements ItemListener {
        StateManager stateManager;

        public ShadowCheckListener(StateManager stateManager) {
            this.stateManager = stateManager;
        }

        public void itemStateChanged(ItemEvent e) {
            int state = e.getStateChange();
            if (state == ItemEvent.SELECTED) {
                stateManager.setshadow(true);
            } else {
                stateManager.setshadow(false);
            }
        }
    }

    class DashCheckListener implements ItemListener {
        StateManager stateManager;

        public DashCheckListener(StateManager stateManager) {
            this.stateManager = stateManager;
        }

        public void itemStateChanged(ItemEvent e) {
            int state = e.getStateChange();
            if (state == ItemEvent.SELECTED)
                stateManager.setDashed(true);
            else
                stateManager.setDashed(false);
        }
    }

    class ColorChoiseListener implements ActionListener {
        Mediator med;
        JComboBox jComboBox;

        public ColorChoiseListener(Mediator med, JComboBox jComboBox) {
            this.med = med;
            this.jComboBox = jComboBox;
        }

        public void actionPerformed(ActionEvent e) {
            ColorItem colorItem = (ColorItem) jComboBox.getSelectedItem();
            med.setColor(colorItem.GetColor());
        }
    }

    class LineColorChoiseListener implements ActionListener {
        Mediator med;
        JComboBox jComboBox;

        public LineColorChoiseListener(Mediator med, JComboBox jComboBox) {
            this.med = med;
            this.jComboBox = jComboBox;
        }

        public void actionPerformed(ActionEvent e) {
            LineColorItem LinecolorItem = (LineColorItem) jComboBox.getSelectedItem();
            med.setLineColor(LinecolorItem.GetColor());
        }
    }

    class LinewidthChoiseListener implements ActionListener {
        Mediator med;
        JComboBox jComboBox;

        public LinewidthChoiseListener(Mediator med, JComboBox jComboBox) {
            this.med = med;
            this.jComboBox = jComboBox;
        }

        public void actionPerformed(ActionEvent e) {
            med.setLinewidth((int) jComboBox.getSelectedItem());
        }
    }

    class alphaListener implements ChangeListener {
        Mediator med;
        JSlider jSlider;

        public alphaListener(StateManager stateManager, JSlider jSlider) {
            this.med = stateManager.canvas.mediator;
            this.jSlider = jSlider;
        }

        public void stateChanged(ChangeEvent e) {
            med.setAlpha(jSlider.getValue());
        }
    }

    class MyKeyListener extends KeyAdapter {
        Vector<MyDrawing> deleteDrawings;
        Mediator med;
        int x, y;

        public MyKeyListener(MyCanvas canvas) {
            super();
            deleteDrawings = new Vector<MyDrawing>();
            this.med = canvas.mediator;
            x = 0;
            y = 0;
        }

        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();

            if (e.isControlDown() && keyCode == KeyEvent.VK_C) {
                if (med.selectedDrawings != null &&
                        !med.selectedDrawings.isEmpty()) {
                    med.copy();
                }
            } else if (e.isControlDown() && keyCode == KeyEvent.VK_V) {
                if (med.buffer != null &&
                        !med.buffer.isEmpty()) {
                    med.paste(x, y);
                }
            } else if (e.isControlDown() && keyCode == KeyEvent.VK_X) {
                if (med.selectedDrawings != null &&
                        !med.selectedDrawings.isEmpty()) {
                    med.cut();
                }
            } else if (keyCode == KeyEvent.VK_BACK_SPACE) {
                if (med.selectedDrawings != null &&
                        !med.selectedDrawings.isEmpty()) {
                    Enumeration<MyDrawing> en1 = med.selectedDrawings.elements();
                    while (en1.hasMoreElements()) {
                        MyDrawing drawing = en1.nextElement();
                        deleteDrawings.add(drawing);
                    }
                    Enumeration<MyDrawing> en2 = deleteDrawings.elements();
                    while (en2.hasMoreElements()) {
                        MyDrawing drawing2 = en2.nextElement();
                        drawing2.setSelected(false);
                        med.selectedDrawings.remove(drawing2);
                        med.removeDrawing(drawing2);
                    }
                }
            }
        }
    }
}
