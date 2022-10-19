import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Panel extends JPanel {
    JButton red = new JButton("red");
    JButton clear = new JButton("clear");
    JButton blue = new JButton("blue");
    JButton green = new JButton("green");
    JButton rectangle = new JButton("rectangle");
    JButton line = new JButton("line");
    JButton oval = new JButton("oval");
    JButton eraser = new JButton("Eraser");
    JButton freeHand = new JButton("Free Hand");
    Stroke dottedd = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{1, 2}, 0);
    int counter = 0;
    int x1;
    int x2;
    int y1;
    int y2;
    int isDotted;
    int isFilled;
    int canDraw;
    Graphics2D g2;
    String shape;
    Color colorista;
    JCheckBox checkBox = new JCheckBox("Dotted");
    JCheckBox filling = new JCheckBox("Fill");
    int colorCode;
    HashMap<String, Integer[]> dimensions = new HashMap<>();
    ArrayList<String> indexes = new ArrayList<>();
//    ArrayList<Integer[]> eraserIndexes = new ArrayList<>();
//    ArrayList<Integer[]> freeIndexes = new ArrayList<>();
    public Panel() {
        shape = "";
        setBackground(Color.white);
        setLayout(null);
        red.setSize(70, 20);
        red.setLocation(10, 20);
        red.setText("red");
        add(red);
        red.setBackground(Color.red);
        green.setSize(70, 20);
        green.setLocation(100, 20);
        green.setText("green");
        add(green);
        green.setBackground(Color.green);
        add(blue);
        blue.setBackground(Color.blue);
        blue.setSize(70, 20);
        blue.setLocation(200, 20);
        blue.setText("Blue");
        rectangle.setSize(70, 20);
        rectangle.setLocation(400, 20);
        rectangle.setText("rectangle");
        add(rectangle);
        oval.setSize(70, 20);
        oval.setLocation(500, 20);
        oval.setText("Oval");
        add(oval);
        line.setSize(70, 20);
        line.setLocation(600, 20);
        line.setText("line");
        add(line);
        add(checkBox);
        checkBox.setSize(70, 20);
        checkBox.setLocation(800, 20);
        checkBox.setText("dotted");
        add(filling);
        filling.setSize(70, 20);
        filling.setLocation(900, 20);
        filling.setText("filled");
        add(clear);
        clear.setSize(70, 20);
        clear.setLocation(1000, 20);
        clear.setText("Clear");
        add(eraser);
        eraser.setSize(70, 20);
        eraser.setLocation(1300, 20);
        eraser.setText("Free Hand");
        add(freeHand);
        freeHand.setSize(70, 20);
        freeHand.setLocation(1200, 20);
        freeHand.setText("Eraser");
        //this actions
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
                x2 = e.getX();
                y2 = e.getY();
                updateUI();
                canDraw = 1;
            }

            @Override

            public void mouseReleased(MouseEvent e) {
                if(shape.equals("fr")){
                    return;
                }
                indexes.add(shape + counter);
                dimensions.put(shape + counter, new Integer[] {x1, y1, x2, y2, colorCode, isDotted, isFilled});
//

                counter++;
                canDraw = 0;
//                if(shape.equals("eraser")){
//                    eraserIndexes.clear();
//                }
            }


            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (shape.equals("er")){
                    x2 = e.getX();
                    y2 = e.getY();
                    indexes.add(shape + counter);
                    dimensions.put(shape + counter, new Integer[] {x1, y1,x2,y2, colorCode, isDotted, isFilled});
                    counter++;
                    updateUI();
                    x1 = e.getX();
                    y1 = e.getY();

                } else if (shape.equals("fr")) {
                    x2 = e.getX();
                    y2 = e.getY();
                    indexes.add(shape + counter);
                    dimensions.put(shape + counter, new Integer[] {x1, y1,x2,y2, colorCode, isDotted, isFilled});
                    counter++;
                    updateUI();
                    x1 = e.getX();
                    y1 = e.getY();
                } else {
                    x2 = e.getX();
                    y2 = e.getY();
                    updateUI();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        //actions
        red.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                colorCode = 1;
                updateUI();
            }
        });
        green.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                colorCode = 2;
                updateUI();
            }
        });
        blue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                colorCode = 3;
                updateUI();
            }
        });
        rectangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape = "rectangle";
            }
        });
        rectangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape = "oval";
            }
        });
        line.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape = "line";
            }
        });
        oval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape = "oval";
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                indexes.clear();
                dimensions.clear();
                shape = "none";
//                eraserIndexes.clear();
                updateUI();
            }
        });
        eraser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                shape = "er";
                updateUI();
            }
        });
        freeHand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                shape = "fr";
                updateUI();
            }
        });


    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g2 = (Graphics2D) g;
//        g2.setColor(colorista);
        for (int i = 0; i < indexes.size(); i++) {
            g2.setColor(getColor(dimensions.get(indexes.get(i))[4]));
            if (dimensions.get(indexes.get(i))[5] == 1){
                g2.setStroke(dottedd);
            }else {
                g2.setStroke(new BasicStroke(0));
            }
//            g2.setColor(dimensions.get(indexes.get(i))[4]);
            switch (indexes.get(i).charAt(0)) {
                case 'r':
                    if (dimensions.get(indexes.get(i))[6] == 1){
                        g2.fillRect(Math.min(dimensions.get(indexes.get(i))[0],dimensions.get(indexes.get(i))[2]), Math.min(dimensions.get(indexes.get(i))[1],dimensions.get(indexes.get(i))[3]), Math.max(dimensions.get(indexes.get(i))[0],dimensions.get(indexes.get(i))[2])- Math.min(dimensions.get(indexes.get(i))[0],dimensions.get(indexes.get(i))[2]),Math.max(dimensions.get(indexes.get(i))[1],dimensions.get(indexes.get(i))[3])- Math.min(dimensions.get(indexes.get(i))[1],dimensions.get(indexes.get(i))[3]));
                    }else {
                        g2.drawRect(Math.min(dimensions.get(indexes.get(i))[0],dimensions.get(indexes.get(i))[2]), Math.min(dimensions.get(indexes.get(i))[1],dimensions.get(indexes.get(i))[3]), Math.max(dimensions.get(indexes.get(i))[0],dimensions.get(indexes.get(i))[2])- Math.min(dimensions.get(indexes.get(i))[0],dimensions.get(indexes.get(i))[2]),Math.max(dimensions.get(indexes.get(i))[1],dimensions.get(indexes.get(i))[3])- Math.min(dimensions.get(indexes.get(i))[1],dimensions.get(indexes.get(i))[3]));
                    }

                    break;
                case 'l':

                    g2.drawLine(dimensions.get(indexes.get(i))[0], dimensions.get(indexes.get(i))[1], dimensions.get(indexes.get(i))[2] ,dimensions.get(indexes.get(i))[3]);
                    break;
                case 'o':
                    if (dimensions.get(indexes.get(i))[6] == 1){
                        g2.fillOval(Math.min(dimensions.get(indexes.get(i))[0],dimensions.get(indexes.get(i))[2]), Math.min(dimensions.get(indexes.get(i))[1],dimensions.get(indexes.get(i))[3]), Math.max(dimensions.get(indexes.get(i))[0],dimensions.get(indexes.get(i))[2])- Math.min(dimensions.get(indexes.get(i))[0],dimensions.get(indexes.get(i))[2]),Math.max(dimensions.get(indexes.get(i))[1],dimensions.get(indexes.get(i))[3])- Math.min(dimensions.get(indexes.get(i))[1],dimensions.get(indexes.get(i))[3]));
                    }else {
                        g2.drawOval(Math.min(dimensions.get(indexes.get(i))[0],dimensions.get(indexes.get(i))[2]), Math.min(dimensions.get(indexes.get(i))[1],dimensions.get(indexes.get(i))[3]), Math.max(dimensions.get(indexes.get(i))[0],dimensions.get(indexes.get(i))[2])- Math.min(dimensions.get(indexes.get(i))[0],dimensions.get(indexes.get(i))[2]),Math.max(dimensions.get(indexes.get(i))[1],dimensions.get(indexes.get(i))[3])- Math.min(dimensions.get(indexes.get(i))[1],dimensions.get(indexes.get(i))[3]));
                    }
                    break;
                case 'e':
//                    g2.setColor(Color.white);
                        g2.fillOval(Math.min(dimensions.get(indexes.get(i))[0],dimensions.get(indexes.get(i))[2]), Math.min(dimensions.get(indexes.get(i))[1],dimensions.get(indexes.get(i))[3]), 40, 40);


                    break;
                case 'f':
                    g2.setColor(Color.white);
                    g2.fillOval(Math.min(dimensions.get(indexes.get(i))[0],dimensions.get(indexes.get(i))[2]), Math.min(dimensions.get(indexes.get(i))[1],dimensions.get(indexes.get(i))[3]), 40, 40);


                    break;
                default:
                    break;
            }
        }

        if(canDraw == 1){
            if (checkBox.isSelected()){
                g2.setStroke(dottedd);
                isDotted = 1;
            }
            else {
                g2.setStroke(new BasicStroke(0));
                isDotted = 0;
            }
            if (filling.isSelected()){
                isFilled = 1;
            }
            else {
                isFilled = 0;
            }
            g2.setColor(getColor(colorCode));
            if (shape.equals("line")) {
                g2.drawLine(x1, y1, x2, y2);
            } else if (shape.equals("rectangle")) {
                if (filling.isSelected()){
                    if (x1 > x2) {
                        if (y1 > y2) {
                            g2.fillRect(x2, y2, x1 - x2, y1 - y2);
                        }else {
                            g2.fillRect(x2, y1, x1 - x2, y2 - y1);
                        }
                    }else {
                        if (y1 > y2) {
                            g2.fillRect(x1, y2, x2 - x1, y1 - y2);
                        }else {
                            g2.fillRect(x1, y1, x2 - x1, y2 - y1);
                        }
                    }
                }else{
                    if (x1 > x2) {
                        if (y1 > y2) {
                            g2.drawRect(x2, y2, x1 - x2, y1 - y2);
                        }else {
                            g2.drawRect(x2, y1, x1 - x2, y2 - y1);
                        }
                    }else {
                        if (y1 > y2) {
                            g2.drawRect(x1, y2, x2 - x1, y1 - y2);
                        }else {
                            g2.drawRect(x1, y1, x2 - x1, y2 - y1);
                        }
                    }
                }
            }else if(shape.equals("oval")) {
                if (filling.isSelected()){
                    if (x1 > x2) {
                        if (y1 > y2) {
                            g2.fillOval(x2, y2, x1 - x2, y1 - y2);
                        }else {
                            g2.fillOval(x2, y1, x1 - x2, y2 - y1);
                        }
                    }else {
                        if (y1 > y2) {
                            g2.fillOval(x1, y2, x2 - x1, y1 - y2);
                        }else {
                            g2.fillOval(x1, y1, x2 - x1, y2 - y1);
                        }
                    }
                }else{
                    if (x1 > x2) {
                        if (y1 > y2) {
                            g2.drawOval(x2, y2, x1 - x2, y1 - y2);
                        }else {
                            g2.drawOval(x2, y1, x1 - x2, y2 - y1);
                        }
                    }else {
                        if (y1 > y2) {
                            g2.drawOval(x1, y2, x2 - x1, y1 - y2);
                        }else {
                            g2.drawOval(x1, y1, x2 - x1, y2 - y1);
                        }
                    }
                }
        } else if (shape.equals("er")) {
               g2.drawOval(Math.min(x1,x2),Math.min(y1,y2),Math.max(x1,x2)-Math.min(x1,x2),Math.max(y1,y2)-Math.min(y1,y2));
            }else if (shape.equals("fr")) {
                g2.setColor(Color.white);
                g2.drawOval(Math.min(x1,x2),Math.min(y1,y2),Math.max(x1,x2)-Math.min(x1,x2),Math.max(y1,y2)-Math.min(y1,y2));
            }

        }


    }
    public Color getColor(int colorCode) {
        if (colorCode < 4) {
            switch (colorCode) {
                case 1:
                    return Color.red;
                case 2:
                    return Color.GREEN;
                case 3:
                    return Color.BLUE;
            }
        }
        return Color.BLACK;
    }
//    class myActions implements ActionListener{
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            switch ()
//        }
//    }
}
