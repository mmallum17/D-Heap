/***************************************************
 *   Program Title: D-Heap Driver                  *
 *   Author:  Marcus Mallum                        *
 *   Class: CSCI-3320,  Spring 2017           	   *
 *   Assignment #2 		                           *
 ***************************************************/
import java.util.Scanner;

public class DHeapDriver
{
    public static void main( String [ ] args )
    {
        // Get initial heap elements
        Integer[] heapElements = getInitialHeap();

        // Get d value
        int d = getD();

        // Create initial heap
        DHeap<Integer> dHeap = new DHeap<>(heapElements, d);
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
        // Get initial heap elements
        Scanner in = new Scanner(System.in);
        System.out.print("Enter heap elements: ");
        String input = in.nextLine();
        String[] parsed = input.split(" ");
        Integer[] heapElements = new Integer[parsed.length];
        for(int i = 0; i < parsed.length; i++)
        {
            heapElements[i] = Integer.parseInt(parsed[i]);
        }
        System.out.println();
        return heapElements;
    }

    /********************************************
     *  FUNCTION: getD                          *
     *  PURPOSE: Gets the D value from the user *
     *  INPUT PARAMETERS: None                  *
     *  OUTPUT: int, D value given by user 		*
     ********************************************/
    private static int getD()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter d: ");
        int d = in.nextInt();
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
        int d;
        Scanner in = new Scanner(System.in);
        int option;
        do
        {
            System.out.print("Press 1) for insert, 2) for deleteMn, 3) for new d value, 4) to quit\n");
            System.out.print("Enter choice: ");
            option = in.nextInt();
            switch(option)
            {
                case 1:
                    System.out.print("Enter element to insert: ");
                    int element = in.nextInt();
                    dHeap.insert(element);
                    System.out.println(dHeap + "\n");
                    break;
                case 2:
                    try
                    {
                        dHeap.deleteMin();
                        System.out.println(dHeap + "\n");
                    }
                    catch(Exception e)
                    {
                        System.out.print(e);
                    }
                    break;
                case 3:
                    d = getD();
                    dHeap.setD(d);
                    dHeap.buildHeap();
                    System.out.println(dHeap + "\n");
                    break;
                case 4:
                    System.out.println("Program Terminated");
            }

        }while(option != 4);
    }
}
