import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
public class Library 
{
    public static void main(String[] args) {
    
        Books bks = new Books();
        Borower borower = new Borower();
        
        String brname="",date="";
        int i=0;
        while(i==0)
        { 
            
            System.out.println("\nEnter your choice: ");
            System.out.println("1.Issue a Book");
            System.out.println("2.Add new book to library");
            System.out.println("3.Check borrower data");
            System.out.println("4.return");
            System.out.println("Enter a random Integer to exit");
            Scanner sc= new Scanner(System.in);
            int n = sc.nextInt();
            
            if(n==1){
                brname=sc.nextLine();
                System.out.println("Enter Borrower name:");
                brname=sc.nextLine();
                System.out.println("Enter book name:");
                String bookname=sc.nextLine();
                System.out.println("Enter date in 00/00/0000 format");
                date=sc.nextLine();
                System.out.println(borower.Issue(bookname, brname, date));
            }
            else if(n==2){
                System.out.println("Enter a new book name");
                String bkname=sc.nextLine();
                // bkname=sc.nextLine();
                boolean b =bks.checkBook(bkname);
                if (b==(false))
                bks.addNewBook(bkname);
            }

            else if(n==3){
                System.out.println("Enter Borrower name:");
                brname=sc.nextLine();
                brname=sc.nextLine();
                String userData =borower.checkBorower(brname);
                System.out.println(userData);
                
            }
            else if(n==4)
            {
                brname=sc.nextLine();
                System.out.println("Enter borower name:");
                brname=sc.nextLine();
                System.out.println(borower.Return(brname));
            }
            else
            i=1;
              
        }
            
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
