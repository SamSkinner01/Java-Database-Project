package project2;
import java.util.Scanner;
/**
 *
 * @author Sam
 */
public class Project2 {

    
    public static void menu (){
        Scanner obj = new Scanner(System.in);
        System.out.println("1. Enter the instructor ID and I will provide you with the name of the instructor, affiliated department and the location of that department.");
        System.out.println("2. Enter the department name and I will provide you with the location, budget and names of all instructors that work for the department.");
        System.out.println("3. Insert a record about a new instructor.");
        System.out.println("4. Delete a record about an instructor.");
        System.out.println("5. Exit");
    }
    
    public static void main(String[] args) {
        menu();
    }
    
}
