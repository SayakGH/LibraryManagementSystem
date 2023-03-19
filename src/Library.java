import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Library extends JFrame implements ActionListener {

    private JLabel borrowerLabel, bookLabel, dateLabel;
    private JTextField borrowerTextField, bookTextField, dateTextField;
    private JButton issueButton, addButton, checkButton, returnButton;

    public Library() {
        setTitle("Library Management System");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        borrowerLabel = new JLabel("Borrower Name:");
        borrowerLabel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                JLabel label = (JLabel) e.getComponent();
                int fontSize = (int) (label.getWidth() * 0.08); // adjust the multiplier to fit your needs
                label.setFont(new Font(label.getFont().getName(),Font.PLAIN,fontSize));
            }
        });
        borrowerTextField = new JTextField(20);
        borrowerTextField.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                JTextField label = (JTextField) e.getComponent();
                int fontSize = (int) (label.getWidth() * 0.08); // adjust the multiplier to fit your needs
                label.setFont(new Font(label.getFont().getName(), Font.PLAIN, fontSize));
            }
        });

        bookLabel = new JLabel("Book Name:");
        bookLabel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                JLabel label = (JLabel) e.getComponent();
                int fontSize = (int) (label.getWidth() * 0.08); // adjust the multiplier to fit your needs
                label.setFont(new Font(label.getFont().getName(), Font.PLAIN, fontSize));
            }
        });
        bookTextField = new JTextField(20);
        bookTextField.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                JTextField label = (JTextField) e.getComponent();
                int fontSize = (int) (label.getWidth() * 0.08); // adjust the multiplier to fit your needs
                label.setFont(new Font(label.getFont().getName(), Font.PLAIN, fontSize));
            }
        });

        dateLabel = new JLabel("Date (dd/mm/yyyy):");
        dateLabel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                JLabel label = (JLabel) e.getComponent();
                int fontSize = (int) (label.getWidth() * 0.1); // adjust the multiplier to fit your needs
                label.setFont(new Font(label.getFont().getName(), Font.PLAIN, fontSize));
            }
        });
        dateTextField = new JTextField(10);
        dateTextField.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                JTextField label = (JTextField) e.getComponent();
                int fontSize = (int) (label.getWidth() * 0.08); // adjust the multiplier to fit your needs
                label.setFont(new Font(label.getFont().getName(), Font.PLAIN, fontSize));
            }
        });

        issueButton = new JButton("Issue");
        issueButton.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                JButton label = (JButton) e.getComponent();
                int fontSize = (int) (label.getWidth() * 0.08); // adjust the multiplier to fit your needs
                label.setFont(new Font(label.getFont().getName(), Font.PLAIN, fontSize));
            }
        });
        issueButton.addActionListener(this);

        addButton = new JButton("Add Book");
        addButton.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                JButton label = (JButton) e.getComponent();
                int fontSize = (int) (label.getWidth() * 0.08); // adjust the multiplier to fit your needs
                label.setFont(new Font(label.getFont().getName(), Font.PLAIN, fontSize));
            }
        });
        addButton.addActionListener(this);

        checkButton = new JButton("Check Borrower");
        checkButton.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                JButton label = (JButton) e.getComponent();
                int fontSize = (int) (label.getWidth() * 0.08); // adjust the multiplier to fit your needs
                label.setFont(new Font(label.getFont().getName(), Font.PLAIN, fontSize));
            }
        });
        checkButton.addActionListener(this);

        returnButton = new JButton("Return Book");
        returnButton.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                JButton label = (JButton) e.getComponent();
                int fontSize = (int) (label.getWidth() * 0.08); // adjust the multiplier to fit your needs
                label.setFont(new Font(label.getFont().getName(), Font.PLAIN, fontSize));
            }
        });
        returnButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        panel.add(borrowerLabel);
        panel.add(borrowerTextField);
        panel.add(bookLabel);
        panel.add(bookTextField);
        panel.add(dateLabel);
        panel.add(dateTextField);
        panel.add(issueButton);
        panel.add(addButton);
        panel.add(checkButton);
        panel.add(returnButton);

        add(panel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == issueButton) {
            String borrowerName = borrowerTextField.getText();
            String bookName = bookTextField.getText();
            String date = dateTextField.getText();

            Books bks = new Books();
            Borower borower = new Borower();

            JOptionPane.showMessageDialog(this, borower.Issue(bookName, borrowerName, date));
        } else if (e.getSource() == addButton) {
            String bookName = bookTextField.getText();

            Books bks = new Books();

            if (!bks.checkBook(bookName)) {
                bks.addNewBook(bookName);
                JOptionPane.showMessageDialog(this, "Book Successfully Added");
            } else {
                JOptionPane.showMessageDialog(this, "Book Already Exists");
            }
        } else if (e.getSource() == checkButton) {
            String borrowerName = borrowerTextField.getText();

            Borower borower = new Borower();

            JOptionPane.showMessageDialog(this, borower.checkBorower(borrowerName));
        } else if (e.getSource() == returnButton) {
            String borrowerName = borrowerTextField.getText();

            Borower borower = new Borower();

            JOptionPane.showMessageDialog(this, borower.Return(borrowerName));
        }
    }

    public static void main(String[] args) {
        new Library();
    }
}
class Books
{
    Books()
    {
        try{
            File bk = new File("book.txt");
            bk.createNewFile();
        }
        catch(IOException e){
            e.printStackTrace();
        }  
    }

    //to add new books to the library
    void addNewBook(String name)
    {
        try{
            FileWriter wrt = new FileWriter("book.txt",true); 
            wrt.write(name);
            wrt.write("\n");
            wrt.close();
            System.out.println("Book Successfully Added");

        }
        catch(IOException e1){
            System.out.println();
        }
    }

    //to check weather a book is present in library or not
    boolean checkBook(String name){
        File bk = new File("book.txt");
        int check=0;
        String Line="";
        try {
            Scanner sc1 =new Scanner(bk);
            while(sc1.hasNextLine()){
                Line =sc1.nextLine();
                if((Line).equalsIgnoreCase(name)==true)
                {
                    check++;
                    break;
                }
            }
            sc1.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            
        }
        
        if(check==0)
            return (false);
        else
            return (true);

    }

}

class Borower
{
    Borower()
    {
        try{
            File reader1 = new File("borower.txt");
            reader1.createNewFile();
        }
        catch(IOException e){
            e.printStackTrace();
        } 
    }

    //to check wether the borrower has borrowed any books
    String checkBorower(String name)
    {   
        File reader = new File("borower.txt");
        int check=0;
        String Line="";
        try {
            Scanner sc =new Scanner(reader);
            while(sc.hasNextLine()){
                Line =sc.nextLine();
                StringTokenizer st = new StringTokenizer(Line);
                if((st.nextToken()).equalsIgnoreCase(name)==true)
                {
                    check++;
                    break;
                }
            }sc.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            
        }
        if(check==0)
            return ("User notpresent in data base");
        else
            return (Line);
    }

    //To check borrower data present in the data base
    boolean checkBorrowerdata(String name){
        File reader = new File("borower.txt");
        int check=0;
        String Line="";
        try {
            Scanner sc =new Scanner(reader);
            while(sc.hasNextLine()){
                Line =sc.nextLine();
                StringTokenizer st = new StringTokenizer(Line);
                if((st.nextToken()).equalsIgnoreCase(name)==true)
                {
                    check++;
                    break;
                }
            }
            sc.close();

        }
        catch (Exception e) {
            e.printStackTrace();
            
        }
        if(check==0)
            return (true);
        else
            return (false);
    }

    //to check wether a book is already issued or not
    boolean alreadynotIssued(String name){
        File reader = new File("borower.txt");
        int ct=0;
        String Line="";
        try {
            Scanner sc =new Scanner(reader);
            while(sc.hasNextLine()){
                Line =sc.nextLine();
                StringTokenizer st = new StringTokenizer(Line);
                String s =st.nextToken();
                s = st.nextToken();
                if((s).equalsIgnoreCase(name)==true)
                {
                    ct++;
                    break;
                }
            }
            sc.close();
        }
        
        catch (Exception e) {
            e.printStackTrace();
        }
        if(ct==0)
            return (true);
        else
            return (false);
        
    }
     
    //issue a book to a borower
    String Issue(String bookName, String borowerName, String Date){
        Books bk = new Books();
        if(bk.checkBook(bookName)==true && alreadynotIssued(bookName)==true && checkBorrowerdata(borowerName)==true)
        {
            try{
                FileWriter wrt = new FileWriter("borower.txt",true); 
                wrt.write(borowerName);
                wrt.write(" ");
                wrt.write(bookName);
                wrt.write(" ");
                wrt.write(Date);
                wrt.write("\n");
                wrt.close();
    
            }
            catch(IOException e2){
                e2.printStackTrace();
            }
            return "Successfully Issued";
        }
        else
        return "Cannot be Issued";
    }

    //Returning the book
    String Return(String borowerName){
        String st = checkBorower(borowerName);
        if(st.equalsIgnoreCase("User notpresent in data base")==true)
        return "User notpresent in data base";
        else{
            File reader1 = new File("borower.txt");
            File reader2 = new File("temp.txt");
            try {
                
            try{
                reader2.createNewFile();
            }
            catch(IOException e){
                e.printStackTrace();
            }
                //FileWriter wrt1 = new FileWriter("borower.txt",true); 
                FileWriter wrt2 = new FileWriter("temp.txt",true);
                Scanner scan1 = new Scanner(reader1);

                while(scan1.hasNextLine()){
                    String Line1 = scan1.nextLine();
                    System.out.println(Line1);
                    StringTokenizer st1 = new StringTokenizer(Line1);
                    if((st1.nextToken()).equalsIgnoreCase(borowerName)==false)
                    {   wrt2.write(Line1);
                        wrt2.write("\n");
                        
                    }
                }
                scan1.close();
                wrt2.close();
                
                reader1.delete();

                // Rename the output file to input file name
                reader2.renameTo(reader1);
                return ("Successfully returned");

                
            } catch (Exception e){
                return "return unsuccessful";
            } 

        }
        
    }
    
}