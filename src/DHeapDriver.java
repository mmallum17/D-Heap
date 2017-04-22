/*****************************************
 *   Program Title: DHeapDriver          *
 *   Author:  Marcus Mallum              *
 *   Class: CSCI3320,  Spring 2017       *
 *   Assignment #2 		                 *
 *****************************************/
import java.util.Scanner;

public class DHeapDriver
{
    public static void main( String [ ] args )
    {
        // Get heap elements
        Scanner in = new Scanner(System.in);
        System.out.print("Enter heap elements: ");
        String input = in.nextLine();
        String[] parsed = input.split(" ");
        Integer[] heapElements = new Integer[parsed.length];
        for(int i = 0; i < parsed.length; i++)
        {
            heapElements[i] = Integer.parseInt(parsed[i]);
            //System.out.printf("%d ", heapElements[i]);
        }
        System.out.println();

        // Get d value
        System.out.print("Enter d: ");
        int d = in.nextInt();

        // Create initial heap
        DHeap<Integer> dHeap = new DHeap<Integer>(heapElements, d);
        System.out.println(dHeap + "\n");
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
                    System.out.print("Enter d: ");
                    d = in.nextInt();
                    dHeap.setD(d);
                    dHeap.buildHeap();
                    System.out.println(dHeap + "\n");
                    break;
                case 4:
                    System.out.println("Program Terminated");
            }

        }while(option != 4);
        /*int numItems = 10000;
        DHeap<Integer> h = new DHeap<>( );
        int i = 37;

        for( i = 37; i != 0; i = ( i + 37 ) % numItems )
            h.insert( i );
        for( i = 1; i < numItems; i++ )
            if( h.deleteMin( ) != i )
                System.out.println( "Oops! " + i );*/
    }
}
