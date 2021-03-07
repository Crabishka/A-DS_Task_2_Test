import util.JTableUtils;
import util.SwingUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class MainForm extends JFrame {


    private JPanel MainPanel;
    private JTable InputField;
    private JTable OutputField;
    private JButton ExecuteButton;
    private JButton ReadFromFileButton;

    public MainForm() {
        this.setTitle("Sort");
        this.setContentPane(MainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTableUtils.initJTableForArray(InputField, 100, true, true, false, false);
        JTableUtils.initJTableForArray(OutputField, 100, true, true, false, false);
        OutputField.setRowHeight(25);
        InputField.setRowHeight(25);
        this.pack();

        ExecuteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JTableUtils.readIntArrayFromJTable(InputField);

                } catch (ParseException parseException) {
                    SwingUtils.showInfoMessageBox("Error");
                }
            }
        });

        /*
         * Читать все как массив
         * так удобнее + уже реализовано
         */
    }

}
