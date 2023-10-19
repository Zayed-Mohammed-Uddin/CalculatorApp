import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class App extends JFrame implements ActionListener {
    private JFrame frame;
    private JTextField textField;
    private JPanel panel;
    private JButton[] numberButtons;
    private JButton[] operatorButtons;

    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public App() {
        frame = new JFrame("Calculator v1.0.0");
        Font f = new Font("Arial", Font.BOLD, 21);

        textField = new JTextField(20);
        textField.setBounds(50, 25, 320, 50);
        textField.setEditable(false);
        textField.setFont(f);

        panel = new JPanel();
        panel.setBounds(50, 100, 320, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        numberButtons = new JButton[10];

        for (int i = 0; i < numberButtons.length; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(f);
            numberButtons[i].setFocusable(false);
            numberButtons[i].addActionListener(this);
        }

        operatorButtons = new JButton[9];
        operatorButtons[0] = new JButton("+");
        operatorButtons[1] = new JButton("-");
        operatorButtons[2] = new JButton("*");
        operatorButtons[3] = new JButton("/");
        operatorButtons[4] = new JButton("C");
        operatorButtons[5] = new JButton(".");
        operatorButtons[6] = new JButton("=");
        operatorButtons[7] = new JButton("Del");
        operatorButtons[8] = new JButton("(-)");

        for (int i = 0; i < operatorButtons.length; i++) {
            if (i < 8) {
                operatorButtons[i].setEnabled(false);
            }
        }

        // (-) button
        operatorButtons[8].setBounds(50, 430, 100, 50);

        // delete button
        operatorButtons[7].setBounds(160, 430, 100, 50);

        // clear button
        operatorButtons[4].setBounds(270, 430, 100, 50);

        frame.add(operatorButtons[4]);
        frame.add(operatorButtons[7]);
        frame.add(operatorButtons[8]);

        for (int i = 0; i < operatorButtons.length; i++) {
            operatorButtons[i].setFont(f);
            operatorButtons[i].setFocusable(false);
            operatorButtons[i].addActionListener(this);
        }

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(operatorButtons[0]);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(operatorButtons[1]);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(operatorButtons[2]);
        panel.add(operatorButtons[5]);
        panel.add(numberButtons[0]);
        panel.add(operatorButtons[6]);
        panel.add(operatorButtons[3]);

        ImageIcon icon = new ImageIcon("logo.png", "Calculator");
        frame.setIconImage(icon.getImage());
        frame.setSize(420, 550);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(textField);
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        JOptionPane.showMessageDialog(null, "Created By ZSR");
        new App();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean buttonPressed = false;
        for (int i = 0; i < numberButtons.length; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
                buttonPressed = true;
            }
        }
        if (buttonPressed) {
            for (int i = 0; i < operatorButtons.length; i++) {
                operatorButtons[i].setEnabled(true);
            }
        }
        if (e.getSource() == operatorButtons[5]) {
            textField.setText(textField.getText().concat("."));
        }
        if (e.getSource() == operatorButtons[0]) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }
        if (e.getSource() == operatorButtons[1]) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }
        if (e.getSource() == operatorButtons[2]) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }
        if (e.getSource() == operatorButtons[3]) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }
        if (e.getSource() == operatorButtons[6]) {
            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
                default:
                    break;
            }
            textField.setText(String.valueOf(result));
            num1 = result;
            for (int i = 0; i < operatorButtons.length; i++) {
                if (i < 8) {
                    operatorButtons[i].setEnabled(true);
                }
            }
        }
        if (e.getSource() == operatorButtons[4]) {
            textField.setText("");
            for (int i = 0; i < operatorButtons.length; i++) {
                if (i < 8) {
                    operatorButtons[i].setEnabled(false);
                }
            }
        }
        if (e.getSource() == operatorButtons[7]) {
            String s = textField.getText();
            ArrayList<Character> chars = new ArrayList<>();
            textField.setText("");
            for (Character c : s.toCharArray()) {
                chars.add(c);
            }
            for (int i = 0; i < chars.size() - 1; i++) {
                textField.setText(textField.getText() + chars.get(i));
            }
            if (textField.getText().equals("")) {
                for (int i = 0; i < operatorButtons.length; i++) {
                    if (i < 8) {
                        operatorButtons[i].setEnabled(false);
                    }
                }
            }
            num1 = Double.parseDouble(textField.getText());
        }
        if (e.getSource() == operatorButtons[8]) {
            if (textField.getText().equals("")) {
                textField.setText(textField.getText().concat("-"));
            } else {
                operatorButtons[8].setEnabled(false);
            }
        }
    }
}