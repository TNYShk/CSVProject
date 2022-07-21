package Swing;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
public class CSVEditor extends JFrame implements ActionListener {

    private JTextArea textArea = new JTextArea();
    private JButton read = new JButton("Open File");
    private JButton write = new JButton("Save File");
    private JTextField nameField = new JTextField(20);
    private JLabel file = new JLabel("File Name");
    private JPanel innerWindow =  new JPanel();

    public CSVEditor(){
        super("A Super fantastic Text Editor");
        innerWindow.setLayout(new GridLayout(2,2,1,1));

        innerWindow.add(read);
        innerWindow.add(write);
        innerWindow.add(nameField);
        innerWindow.add(file);

        this.getContentPane().setLayout(new BorderLayout());

        this.getContentPane().add("North",innerWindow);

        this.getContentPane().add( "Center",textArea);
        this.getContentPane().add( new JScrollPane(textArea)  );


       textArea.setFont(new Font("Menlo", Font.ITALIC, 20));
        nameField.setFont(new Font("Andale Mono", Font.PLAIN, 15));

        read.addActionListener(this);
        write.addActionListener(this);
    }
    public static void main(String[] args) {

        CSVEditor window = new CSVEditor();
        window.setSize(600, 600);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    /**
     *  reads from a text file.  IntelliJ will look for it at the Projects' data Folder
     * @param textArea
     * @param fileName
     */
    private void readTextFile(JTextArea textArea, String fileName) {
        String path = "data/";
        path = path.concat(fileName);

        try {
            BufferedReader inStream // Create and
                    = new BufferedReader(new FileReader(path));
            String line = inStream.readLine();            // Read one line
            while (line != null) {                        // While more text
                textArea.append(line + "\n");              // textArea a line
                line = inStream.readLine();               // Read next line
            }
            inStream.close();
        } catch (FileNotFoundException e) {
            textArea.setText("IOERROR: File NOT Found: " + fileName + "\n");
            e.printStackTrace();
        } catch (IOException e) {
            textArea.setText("IOERROR: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    } // end readTextFile
    //writes to a text file.  IntelliJ will look for it at the Project Folder
    private void writeTextFile(JTextArea textArea, String fileName) {
        String path = "data/";
        path = path.concat(fileName);
        try {
            FileWriter outStream = new FileWriter(path);
            outStream.write(textArea.getText());
            outStream.close();
        } catch (IOException e) {
            textArea.setText("IOERROR: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    } // end writeTextFile()
    //watches the button and waits until it is clicked
    @Override
    public void actionPerformed(ActionEvent evt) {
        String fileName = nameField.getText();
        if (evt.getSource() == read) {
            textArea.setText("");
            readTextFile(textArea, fileName);
        } else {
            writeTextFile(textArea, fileName);
        }
    }//end actionPerformed()
}//end class

