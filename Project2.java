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
 * @author Sam
 */
public class Project2{     
        
   
    
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
    
    //Will read two text files into db
    public static void readFilesIntoDB(Statement s) throws SQLException{
        s.executeUpdate("drop database if exists University");
        s.executeUpdate("create database University");
        s.executeUpdate("use University");
        s.executeUpdate("create table deptartment(dept_name varchar(20) PRIMARY KEY,building varchar(15),budget numeric(12,2));"); //rewrite query in workbench
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
  
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        //Connection to JDBC
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");
        
        //Connect to database :: Last is password
        Connection  con = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "password");
        System.out.println("Database connected\n");
        
        Statement s = con.createStatement();
        readFilesIntoDB(s);
        
        
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
                    
                    
                    //Calls menu again and make sures the newly entered choice is still a valid choice.
                    menu();
                    choice = userInput.nextLine();
                    choice = makeValidChoice(choice);
                    break;
                case "2":
                    
                    
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
