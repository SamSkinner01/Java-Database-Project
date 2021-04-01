/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;
import java.util.Scanner;
/**
 *
 * @author Sam
 */
public class Project2 {

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
    
  
    public static void main(String[] args) {
        
        //Get user choice for the menu 
        Scanner userInput = new Scanner(System.in);
        menu();
        String choice = userInput.nextLine();
        
        //Checks to make sure choice is a valid input.
        while(isValidChoice(choice) == false){
            System.out.println("Please enter a valid choice: ");
            choice = userInput.nextLine();
        }
        System.out.println("\n");
        
        
        //Menu -- Sam
        while(null != choice){
            switch (choice) {
                case "1":
                    
                    
                    menu();
                    choice = userInput.nextLine();
                    //Checks to make sure choice is a valid input.
                    while(isValidChoice(choice) == false){
                        System.out.println("Please enter a valid choice: ");
                        choice = userInput.nextLine();
                    }   System.out.println("\n");
                    break;
                case "2":
                    
                    
                    menu();
                    choice = userInput.nextLine();
                    //Checks to make sure choice is a valid input.
                    while(isValidChoice(choice) == false){
                        System.out.println("Please enter a valid choice: ");
                        choice = userInput.nextLine();
                    }   System.out.println("\n");
                    break;
                case "3":
                    
                    
                    menu();
                    choice = userInput.nextLine();
                    //Checks to make sure choice is a valid input.
                    while(isValidChoice(choice) == false){
                        System.out.println("Please enter a valid choice: ");
                        choice = userInput.nextLine();
                    }   System.out.println("\n");
                    break;
                case "4":
                    
                    
                    menu();
                    choice = userInput.nextLine();
                    //Checks to make sure choice is a valid input.
                    while(isValidChoice(choice) == false){
                        System.out.println("Please enter a valid choice: ");
                        choice = userInput.nextLine();
                    }   System.out.println("\n");
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
