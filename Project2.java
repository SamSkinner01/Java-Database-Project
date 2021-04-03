/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;
import java.util.Scanner;
import java.sql.*;

/**
 *
 * @author Dermot
 */
public class Project2{     
    //1 and 2, execute query
    
    
    public static void name(){
        
    }
    
    //Menu built by sam
    public static void menu (){
        System.out.println("1. Enter the instructor ID and I will provide you with the name of the instructor, affiliated department and the location of that department.");
        System.out.println("2. Enter the department name and I will provide you with the location, budget and names of all instructors that work for the department.");
        System.out.println("3. Insert a record about a new instructor.");
        System.out.println("4. Delete a record about an instructor.");
        System.out.println("5. Exit");
    }
    
    //isValidChoice built by sam
    public static boolean isValidChoice(String c){
        boolean isValid =  false;
        
        if("1".equals(c) || "2".equals(c) ||"3".equals(c) || "4".equals(c) || "5".equals(c)){
            isValid = true;
        }
        
        return isValid;
    } 
    
    //makeValidChoice is made by sam
    public static String makeValidChoice(String choice){
        Scanner obj = new Scanner(System.in);        
        while(isValidChoice(choice) == false){
            System.out.println("Please enter a valid choice: ");
            choice = obj.nextLine();
        }
        System.out.println("\n");
        
        return choice;
    }
        public static void readFilesIntoDB(Statement s) throws SQLException{
        //Creates the databases and adds the tables. 
        s.executeUpdate("drop database if exists University");
        s.executeUpdate("create database University");
        s.executeUpdate("use University");
        s.executeUpdate("create table deptartment(dept_name varchar(20) PRIMARY KEY,building varchar(15),budget numeric(12,2));"); 
        s.executeUpdate("create table instructor(id int PRIMARY KEY,name varchar(30),dept_name varchar(30),foreign key(dept_name) references deptartment(dept_name));");
        
        //Read data from text files and insert into database
    }
    
    //Write query inside function to enter into database;
    public static void insertInstructor(Statement s){
        //s.executeUpdate() //Will be an insert statement
    }
    
    //Write query inside function to delete from database
    public static void deleteInstructor(Statement s){
        //s.executeUpdate(); //will be a delete statement
    }
    
    //checks if ID exists in DB
    public static boolean checkID(int ID, Statement s)throws SQLException{
        ResultSet rs = s.executeQuery("Select*from instructor");
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
        ResultSet rs = s.executeQuery("Select*from instructor");
        boolean check = false;
        while(rs.next() && check==false){
            String name = rs.getString("dept_name");
            if (dept_name == name)
                check = true;
                
        }
        return check;
    }
     
    //Prints instructor name, dept, and dept location based on provided ID
    public static void instructorInfo(int ID,Statement s) throws SQLException{
       ResultSet rs = s.executeQuery("SELECT name,dept_name,location from instructor natural join department where id = "+ID); 
       while(rs.next()){
            System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
            
        }
    }
    
    //Prints dept location, budget, and instructor names in that dept based on provided dept_name
    public static void departmentInfo(String dept_name,Statement s) throws SQLException{
       ResultSet rs = s.executeQuery("SELECT distinct location,budget,name from department natural join instructor where dept_name = "+dept_name); 
       while(rs.next()){
            System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
            
        }
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        //Connection to JDBC
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");
        
        //Connect to database :: Last is password
        Connection  con = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "Behan101");
        System.out.println("Database connected\n");
        
        Statement s = con.createStatement();
        
        
        
        //Get user choice for the menu 
        Scanner userInput = new Scanner(System.in);
        menu();
        String choice = userInput.nextLine();
        
        //Checks to make sure choice is a valid input.
        choice = makeValidChoice(choice);
        
        
        //Menu -- Sam
        while(null != choice){
            switch (choice) {
                case "1":
                    s.executeUpdate("use University");
                    System.out.println("Insert ID");
                    Scanner scan = new Scanner(System.in);
                    int ID = scan.nextInt();
                    if (checkID(ID,s) == true){
                        instructorInfo(ID,s);
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
                    }
                    else
                        System.out.println("The department name doesn't appear in the database.");
                    
                    //Calls menu again and make sures the newly entered choice is still a valid choice.
                    menu();
                    choice = userInput.nextLine();
                    choice = makeValidChoice(choice);
                    break;
                case "3":
                    
                    
                    //Calls menu again and make sures the newly entered choice is still a valid choice.
                    menu();
                    choice = userInput.nextLine();
                    choice = makeValidChoice(choice);
                    break;
                case "4":
                    
                    
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
