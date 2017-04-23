/***************************************************
 *   Program Title: D-Heap Driver                  *
 *   Author:  Marcus Mallum                        *
 *   Class: CSCI-3320,  Spring 2017           	   *
 *   Assignment #2 		                           *
 ***************************************************/
import java.util.InputMismatchException;
import java.util.Scanner;

public class DHeapDriver
{
    public static void main( String [ ] args )
    {
        Integer[] heapElements = getInitialHeap(); // Get initial heap elements
        int d = getD(); // Get d value
        DHeap<Integer> dHeap = new DHeap<>(heapElements, d); // Create initial heap
        System.out.println(dHeap + "\n");
        runMenu(dHeap);
    }

    /*********************************************************************
     *  FUNCTION: getInitialHeap                                         *
     *  PURPOSE: Prompts user for initial heap elements and returns them *
     *  INPUT PARAMETERS: None                                           *
     *  OUTPUT: Integer[] -> initial heap elements 		              	 *
     *********************************************************************/
    private static Integer[] getInitialHeap()
    {
        Integer[] heapElements;
        boolean valid;
        Scanner in = new Scanner(System.in);

        // Loop until a valid input is entered
        do
        {
            valid = true;   // Assume input is valid

            // Get input from user
            System.out.print("Enter heap elements: ");
            String input = in.nextLine();
            String[] parsed = input.split(" "); // Parse string

            // Convert String input to Integer array
            heapElements = new Integer[parsed.length];
            for(int i = 0; i < parsed.length; i++)
            {
                try
                {
                    heapElements[i] = Integer.parseInt(parsed[i]);
                    valid = true;
                }
                catch(NumberFormatException NFE) // If invalid input, re-prompt
                {
                    System.out.println("Please enter numbers only!\n");
                    valid = false;  // Used to repeat loop for new values
                    break;
                }
            }
        }while(!valid);

        System.out.println();
        return heapElements;
    }

    /********************************************
     *  FUNCTION: getD                          *
     *  PURPOSE: Gets the D value from the user *
     *  INPUT PARAMETERS: None                  *
     *  OUTPUT: int -> D value given by user 		*
     ********************************************/
    private static int getD()
    {
        Scanner in = new Scanner(System.in);
        boolean valid;
        int d = -1;

        do
        {
            valid = true;
            System.out.print("Enter d: ");
            try
            {
                d = in.nextInt();
                if(d < 1)
                {
                    System.out.println("Please enter a valid value for d!\n");
                    valid = false;
                }
            }
            catch(Exception e)
            {
                in.nextLine();
                System.out.println("Please enter a valid value for d!\n");
                valid = false;
            }
        }while(!valid);

        return d;
    }

    /***********************************************************
     *  FUNCTION: runMenu                                      *
     *  PURPOSE: Runs the menu and performs associated tasks   *
     *  INPUT PARAMETERS: DHeap dHeap -> the D-Heap being used *
     *  OUTPUT: None 		              		               *
     ***********************************************************/
    private static void runMenu(DHeap dHeap)
    {
        Scanner in = new Scanner(System.in);
        int option = -1;
        do
        {
            boolean valid;
            // Display menu and get valid choice from user
            do
            {
                valid = true;
                System.out.print("Press 1) for insert, 2) for deleteMn, 3) for new d value, 4) to quit\n");
                System.out.print("Enter choice: ");
                try     // Try to get a choice from the user
                {
                    option = in.nextInt();
                    if(option < 1 || option > 4)    // If choice is out of range, get new choice
                    {
                        System.out.println("Please enter a valid choice!\n");
                        valid = false;
                    }
                }
                catch(InputMismatchException IME)   // If invalid choice, get new choice
                {
                    in.nextLine();
                    System.out.println("Please enter a valid choice!\n");
                    valid = false;
                }
            }while(!valid);

            switch(option)  // Perform Task
            {
                case 1: // Insert valid input
                    try     // Try to insert element
                    {
                        System.out.print("Enter element to insert: ");
                        int element = in.nextInt();
                        dHeap.insert(element);
                        System.out.println(dHeap + "\n");
                    }
                    catch(InputMismatchException IME) // If invalid element, don't insert
                    {
                        in.nextLine();
                        System.out.println("Invalid element, can't insert!");
                        System.out.println(dHeap + "\n");
                    }
                    break;
                case 2: // deleteMin
                    try
                    {
                        dHeap.deleteMin();
                        System.out.println(dHeap + "\n");
                    }
                    catch(Exception e) // If list is empty
                    {
                        System.out.println("Can't Delete as List is Empty!\n");
                    }
                    break;
                case 3: // New d value
                    int d = getD();
                    dHeap.setD(d);
                    dHeap.buildHeap();
                    System.out.println(dHeap + "\n");
                    break;
                case 4: // Terminate program
                    System.out.println("Program Terminated");
            }

        }while(option != 4);
    }
}