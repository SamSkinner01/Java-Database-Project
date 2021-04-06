/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.sql.*;

/**
 *
 * @author Dermot and  Sam
 */
public class Project2{
    
    //Menu
    public static void menu (){
        System.out.println("1. Enter the instructor ID and I will provide you with the name of the instructor, affiliated department and the location of that department.");
        System.out.println("2. Enter the department name and I will provide you with the location, budget and names of all instructors that work for the department.");
        System.out.println("3. Insert a record about a new instructor.");
        System.out.println("4. Delete a record about an instructor.");
        System.out.println("5. Exit");
    }
    
    //isValidChoice
    public static boolean isValidChoice(String c){
        boolean isValid =  false;
        
        if("1".equals(c) || "2".equals(c) ||"3".equals(c) || "4".equals(c) || "5".equals(c)){
            isValid = true;
        }
        
        return isValid;
    } 
    
    //makeValidChoice
    public static String makeValidChoice(String choice){
        Scanner obj = new Scanner(System.in);        
        while(isValidChoice(choice) == false){
            System.out.println("Please enter a valid choice: ");
            choice = obj.nextLine();
        }
        System.out.println("\n");
        
        return choice;
    }
    
    public static void readFilesIntoDB(Statement s) throws SQLException, FileNotFoundException{
        //Creates the databases and adds the tables. 
        s.executeUpdate("drop database if exists University");
        s.executeUpdate("create database University");
        s.executeUpdate("use University");
        s.executeUpdate("create table department(dept_name varchar(20) PRIMARY KEY,building varchar(15),budget numeric(12,2));"); 
        s.executeUpdate("create table instructor(id int PRIMARY KEY,name varchar(30),dept_name varchar(30),foreign key(dept_name) references department(dept_name));");
        
        //Read data from text files and insert into database       
        //department
        //make sure path is correct on your specific computer
        String departmentFile = "C:\\Users\\Sam\\Desktop\\department.txt";
        Scanner departmentScanner = new Scanner(new File(departmentFile));
        departmentScanner.useDelimiter("\n");

        do {
            String line = departmentScanner.next();
            String[] values = line.split(",");
               
            s.executeUpdate("INSERT INTO department (dept_name, building,budget) VALUES (\"" + values[0] + "\", \"" + values[1] + "\", " + values[2] + ")");

        }
        while (departmentScanner.hasNext());

        departmentScanner.close();
        
        //instructor
        //make sure path is correct on your specific computer
        String instructorFile = "C:\\Users\\Sam\\Desktop\\instructor.txt";
        Scanner instructorScanner = new Scanner(new File(instructorFile));
        instructorScanner.useDelimiter("\n");

        do {
            String line = instructorScanner.next();
            String[] values = line.split(",");
            
           //System.out.println(values[2]);
            
            s.executeUpdate("INSERT INTO instructor (id, name, dept_name) VALUES (" + values[0] + ", \"" + values[1] + "\", \"" + values[2].trim() + "\")");

        }
        while (instructorScanner.hasNext());

        instructorScanner.close();
    }
    
    //Write query inside function to enter into database
    public static void insertInstructor(Statement s) throws SQLException{
        Scanner scan = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        Scanner scan3 = new Scanner(System.in);
       
        System.out.println("Please enter an id: ");
        int id = scan.nextInt();
        while(isValidID(id,s) == false){
            System.out.println("Please enter an unused id: ");
            id = scan.nextInt();
        }
        
        System.out.println("Please enter a name: ");
        String name = scan2.nextLine();
        
        System.out.println("Please enter a department: ");
        String dept_name = scan3.nextLine();
        boolean check2 = checkDept(dept_name, s);
        while(!check2){
            System.out.println("Please enter a valid department: ");
            dept_name = scan3.nextLine();
            check2 = checkDept(dept_name, s);
        }
        s.executeUpdate("INSERT INTO instructor VALUES (" + id + ", \"" + name + "\", \"" + dept_name + "\")");
        
        System.out.println("Instructor added\n");
    }
    
    //Write query inside function to delete from database
    public static void deleteInstructor(Statement s) throws SQLException{
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter an id: ");
        int id = scan.nextInt();
        if(checkID(id,s) == true){
            s.executeUpdate("Delete from instructor where id = " + id); 
            System.out.println("Instructor deleted\n");
        }else{
            System.out.println("ID does not exist.\n");
        }
        
    }
    
    //If ID is already used in the database return false, else that ID can be used.
    public static boolean isValidID(int ID, Statement s) throws SQLException{
        ResultSet rs = s.executeQuery("Select id from instructor");
        boolean isValid = true;
        while(rs.next() && isValid== true){
            int id = rs.getInt("id");
            if (ID == id)
                isValid = false;
                
        }
        return isValid;
    }
    
    //checks if ID exists in DB
    public static boolean checkID(int ID, Statement s)throws SQLException{
        ResultSet rs = s.executeQuery("Select id from instructor");
        boolean check = false;
        while(rs.next() && check==false){
            int id = rs.getInt("id");
            if (ID == id)
                check = true;
                
        }
        return check;
    }
    
    //checks if dept exists in DB
     public static boolean checkDept(String dept_name, Statement s)throws SQLException{
        ResultSet rs = s.executeQuery("Select dept_name from instructor");
        
        boolean check = false;
        while(rs.next() && check==false){
            String name = rs.getString("dept_name");
            if (dept_name.equals(name))
                check = true;
                
        }
        return check;
    }
     
    //Prints instructor name, dept, and dept location based on provided ID
    public static void instructorInfo(int ID,Statement s) throws SQLException{
       ResultSet rs = s.executeQuery("SELECT name,dept_name,building from instructor natural join department where id = "+ID); 
       while(rs.next()){
            System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
            
        }
    }
    
    //Prints dept location, budget, and instructor names in that dept based on provided dept_name
    public static void departmentInfo(String dept_name,Statement s) throws SQLException{
       
       ResultSet rs = s.executeQuery("SELECT building,budget,name from department natural join instructor where dept_name = \""+dept_name.trim()+"\""); 
       while(rs.next()){
            System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
            
        }
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException {
        
        //Connection to JDBC
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");
        
        //Connect to database :: Last is password
        Connection  con = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "password");
        System.out.println("Database connected\n");
        
        Statement s = con.createStatement();
        
        //Read files
        readFilesIntoDB(s);
        
        //Get user choice for the menu 
        Scanner userInput = new Scanner(System.in);
        menu();
        String choice = userInput.nextLine();
        
        //Checks to make sure choice is a valid input.
        choice = makeValidChoice(choice);
        
        
        //Menu
        while(null != choice){
            switch (choice) {
                case "1":
                    s.executeUpdate("use University");
                    System.out.println("Insert ID");
                    Scanner scan = new Scanner(System.in);
                    int ID = scan.nextInt();
                    if (checkID(ID,s) == true){
                        instructorInfo(ID,s);
                        System.out.println("\n");
                    }
                    else
                        System.out.println("The ID doesn't appear in the database.");
                    //Calls menu again and make sures the newly entered choice is still a valid choice.
                    menu();
                    choice = userInput.nextLine();
                    choice = makeValidChoice(choice);
                    break;
                case "2":
                    s.executeUpdate("use University");
                    System.out.println("Insert Department Name");
                    String name = userInput.nextLine();
                    if (checkDept(name,s) == true){
                        departmentInfo(name,s);
                        System.out.println("\n");
                    }
                    else
                        System.out.println("The department name doesn't appear in the database.");
                    
                    //Calls menu again and make sures the newly entered choice is still a valid choice.
                    menu();
                    choice = userInput.nextLine();
                    choice = makeValidChoice(choice);
                    break;
                case "3":
                    
                    insertInstructor(s);
                    //Calls menu again and make sures the newly entered choice is still a valid choice.
                    menu();
                    choice = userInput.nextLine();
                    choice = makeValidChoice(choice);
                    break;
                case "4":
                    
                    deleteInstructor(s);
                    //Calls menu again and make sures the newly entered choice is still a valid choice.
                    menu();
                    choice = userInput.nextLine();
                    choice = makeValidChoice(choice);
                    break;
                case "5":
                    System.out.println("Exiting the program.");
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}
