import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//This program is a basic calculator (addition, subtraction, division, multiplication)

//Program layout
public class guicalc extends JFrame implements ActionListener {
    private JTextField textField;
    private double num1 = 0, num2 = 0;
    private char operator = ' ';

        public guicalc() {
        setTitle("Calculator");
        setSize(300, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

                textField = new JTextField();
        textField.setEditable(false);
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        add(textField, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }
    
//Program functionality
    
    public void actionPerformed(ActionEvent e) {
        String input = ((JButton)e.getSource()).getText();

        if (input.matches("[0-9]")) {
        // Append digit to current input

        textField.setText(textField.getText() + input);
    } else if (input.matches("[+\\-*/]")) {
        // Only proceed if there's a number
        if (!textField.getText().isEmpty()) {
            num1 = Double.parseDouble(textField.getText());
            operator = input.charAt(0);
            textField.setText(""); // Prepare for second number
        }
    } else if (input.equals("=")) {
        if (!textField.getText().isEmpty() && operator != ' ') {
            num2 = Double.parseDouble(textField.getText());
            double result = 0;
            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/':
                    if (num2 == 0) {
                        textField.setText("Error");
                        return;
                    }
                    result = num1 / num2;
                    break;
                default:
                    textField.setText("Error");
                    return;
            }
            textField.setText(String.valueOf(result));
            operator = ' '; // Reset operator for next calculation
        }

            
        } else if (input.equals("C")) {
            textField.setText("");
            num1 = num2 = 0;
            operator = ' ';
        }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new guicalc());
    }
}
