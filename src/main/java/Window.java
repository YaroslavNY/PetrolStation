import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window (String name){
        super();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setName(name);
        setLocationRelativeTo(null);

        createView();
        pack();
        setVisible(true);

    }

    private void createView (){
        JComboBox <Calculation.Cities> comboBox = new JComboBox(Calculation.Cities.values());
        Font font = new Font(Font.DIALOG,  Font.BOLD, 20);
        comboBox.setFont(font);
        JPanel panel = new JPanel();
        setPreferredSize(new Dimension(1100,200));
        JButton calculate = new JButton("Calculate");
        calculate.setSize(300,150);
        calculate.setFont(font);
        JLabel label = new JLabel();
        label.setFont(font);
        JPanel messagePanel = new JPanel();

        calculate.addActionListener(e -> {
            Calculation.Cities chosenCity =  (Calculation.Cities)comboBox.getSelectedItem();
            Calculation calculation = new Calculation();
             label.setName(calculation.findCheapRefill(chosenCity));
             label.setText(calculation.findCheapRefill(chosenCity));
        });

        setLayout(new BorderLayout());

        panel.add(comboBox);
        messagePanel.add(label);
        add(panel, BorderLayout.NORTH);
        add(calculate, BorderLayout.SOUTH);
        add(messagePanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }

}
