package best_layer;

import javax.swing.*;
import java.io.IOException;

import static best_layer.DataPresenter.setDefaultPathtoFile;

public class GUIPresenter {
    private JPanel mainPanel;
    private JLabel yearLabel;
    private JTextField yearField;
    private JLabel monthLabel;
    private JTextField result;
    private JButton calculateButton;
    private JTextField monthField;
    private JTextField capacityField;

    public GUIPresenter() throws IOException {
        DataAnalyzer viewer = new DataAnalyzer(setDefaultPathtoFile("rent_data_.csv"));
        viewer.InitializeIt();

        calculateButton.addActionListener(e -> {
            int year = Integer.parseInt(yearField.getText());
            int month = Integer.parseInt(monthField.getText());
            if(DataPresenter.checkDates(year,month)){
              result.setText("Expected Revenue: "+ viewer.calculateRevenue(year, month)+"$ for "+DataAnalyzer.months[month]+", " +year);
              capacityField.setText("Reserved Offices: "+viewer.calculateReservedOffices(year,month)+" out of "+viewer.allOffices());
            }else{
                result.setText("Bad Input");
                capacityField.setText("Try Again");
            }

        });
    }


    public static void main(String[] args) throws IOException {

        JFrame frame = new JFrame("Capacity & Revenue Calculator");
        frame.setContentPane(new GUIPresenter().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,256);


        frame.setVisible(true);
    }
}
