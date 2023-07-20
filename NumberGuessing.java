import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessing extends JFrame {

    private JTextField numberField;
    private JButton guessButton;
    private JTable numberTable;
    private JLabel ml, gl;
    private Random rand = new Random();
    private int targetNumber;
    private int n = 0;

    public NumberGuessing() {
        targetNumber = rand.nextInt(100) + 1; // Generate a random number between 1 and 100
        System.out.println(targetNumber);
        setTitle("Number Guessing App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        ml = new JLabel("Guess a number");
        gl = new JLabel("");

        // Create components
        numberField = new JTextField(10);
        guessButton = new JButton("Guess");
        
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                n++;
                // Get the number from the JTextField
                String numberText = numberField.getText();
                    int number = Integer.parseInt(numberText);

                    // Add the number to the JTable
                    DefaultTableModel tableModel = (DefaultTableModel) numberTable.getModel();
                    tableModel.addRow(new Object[]{number});

                    if (number > targetNumber) {
                        gl.setText("Wrong Answer. Guess Lower");
                    } else if (number < targetNumber) {
                        gl.setText("Wrong Answer. Guess Higher");
                    } else if(number == targetNumber){
                        gl.setText("Correct Answer. Number of guesses: " + n);
                        guessButton.setEnabled(false); // Disable the button after the correct guess
                    }

                // Clear the input field after adding the number
                numberField.setText("");
            }
        });

        // Table setup
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Guesses");
        numberTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(numberTable);

        // Panel for left side components (TextField and Button)
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(ml);
        leftPanel.add(numberField);
        leftPanel.add(guessButton);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(scrollPane);
        rightPanel.add(gl);

        // Add components to the frame
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    public static void main(String[] args) {
       new NumberGuessing().setVisible(true);
    }
}
