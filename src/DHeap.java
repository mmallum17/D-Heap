/***************************************************
 *   Program Title: Implementation of a D-Heap     *
 *   Author:  Marcus Mallum                        *
 *   Class: CSCI-3320,  Spring 2017           	   *
 *   Assignment #2 		                           *
 ***************************************************/

public class DHeap<AnyType extends Comparable<? super AnyType>>
{
    private int currentSize;    // Number of elements in heap
    private AnyType [ ] array;  // The heap array
    private int d;              // d value of d heap

    /********************************************************************
     *  CONSTRUCTOR: DHeap                                              *
     *  PURPOSE: Creates the initial D-Heap using buildHeap()           *
     *  INPUT PARAMETERS: AnyType[] items -> the initial list of items  *
     *                    int d -> the d value of the D-Heap            *
     *  OUTPUT: None 		              		                        *
     ********************************************************************/
    @SuppressWarnings("unchecked")
    public DHeap( AnyType [ ] items , int d)
    {
        // Initialize Values
        setD(d);
        currentSize = items.length;

        // Create heap using array (1-indexed)
        array = (AnyType[]) new Comparable[ ( currentSize + 2 ) * 11 / 10 ];
        int i = 1;
        for( AnyType item : items )
            array[ i++ ] = item;
        buildHeap( );
    }

    /*********************************************************
     *  FUNCTION: setD                                       *
     *  PURPOSE: Sets the D value of the D-Heap              *
     *  INPUT PARAMETERS: int d -> the d value of the D-Heap *
     *  OUTPUT: None 		              		             *
     *********************************************************/
    public void setD(int d)
    {
        this.d = d;
    }

    /******************************************************************
     *  FUNCTION: insert                                              *
     *  PURPOSE: Inserts element into D-Heap, maintaining heap order. *
     *           Duplicates are allowed                               *
     *  INPUT PARAMETERS: AnyType x -> the item to insert             *
     *  OUTPUT: None 		              		                      *
     ******************************************************************/
    public void insert( AnyType x )
    {
        // If array is full, enlarge array
        if( currentSize == array.length - 1 )
            enlargeArray( array.length * 2 + 1 );

        // Insert element and Percolate up
        int hole = ++currentSize;
        for( array[ 0 ] = x; x.compareTo( array[ (hole + d - 2) / d ] ) < 0; hole = (hole + d - 2) / d )
            array[ hole ] = array[ (hole + d - 2) / d ];
        array[ hole ] = x;
    }

    /****************************************************************
     *  FUNCTION: enlargeArray                                      *
     *  PURPOSE: Enlarges array once it becomes full                *
     *  INPUT PARAMETERS: int newSize -> the new size of the array  *
     *  OUTPUT: None 		              		                    *
     ****************************************************************/
    @SuppressWarnings("unchecked")
    private void enlargeArray( int newSize )
    {
        AnyType [] old = array;
        array = (AnyType []) new Comparable[ newSize ]; // Enlarge array
        for( int i = 0; i < old.length; i++ )   // Copy old array to new array
            array[ i ] = old[ i ];
    }

    /*********************************************************
     *  FUNCTION: findMin                                    *
     *  PURPOSE: Find the minimum value in the D-Heap        *
     *  INPUT PARAMETERS: None                               *
     *  OUTPUT: AnyType -> the minimum element in the D-Heap *
     *********************************************************/
    public AnyType findMin( ) throws Exception
    {
        if( isEmpty( ) )    // If heap is empty, throw exception
            throw new Exception( );
        return array[ 1 ];
    }

    /*********************************************************
     *  FUNCTION: deleteMin                                  *
     *  PURPOSE: Removes the smallest element in the D-Heap  *
     *  INPUT PARAMETERS: None                               *
     *  OUTPUT: AnyType -> the minimum element in the D-Heap *
     *********************************************************/
    public AnyType deleteMin( ) throws Exception
    {
        if( isEmpty( ) )    // If heap is empty, throw exception
            throw new Exception( );

        AnyType minItem = findMin( );
        array[ 1 ] = array[ currentSize-- ]; // Move last element to root
        percolateDown( 1 ); // Percolate down from root

        return minItem;
    }

    /**********************************************************
     *  FUNCTION: buildHeap                                   *
     *  PURPOSE: Establishes heap order property for a D-Heap *
     *  INPUT PARAMETERS: None                                *
     *  OUTPUT: None 		              		              *
     **********************************************************/
    public void buildHeap( )
    {
        // Percolate down from second to last level to the root
        for( int i = (currentSize + d - 2) / d; i > 0; i-- )
            percolateDown( i );
    }

    /******************************************************
     *  FUNCTION: isEmpty                                 *
     *  PURPOSE: Tests if D-Heap is empty                 *
     *  INPUT PARAMETERS: None                            *
     *  OUTPUT: boolean -> true if empty, false otherwise *
     ******************************************************/
    public boolean isEmpty( )
    {
        return currentSize == 0;
    }

    /*****************************************************
     *  FUNCTION: toString                               *
     *  PURPOSE: Returns String representation of D-Heap *
     *  INPUT PARAMETERS: None                           *
     *  OUTPUT: String, representation of D-Heap 		 *
     *****************************************************/
    @Override
    public String toString()
    {
        String output = String.format("Output: Heap (d=%d): ", d);
        for(int i = 1; i <= currentSize; i++)   // Add heap elements to output
        {
            output += array[i] + " ";
        }
        return output;
    }

    /*************************************************************************
     *  FUNCTION: percolateDown                                              *
     *  PURPOSE: Percolates down a given hole                                *
     *  INPUT PARAMETERS: int hole -> the index at when the percolate begins *
     *  OUTPUT: None 		              		                             *
     *************************************************************************/
    private void percolateDown( int hole )
    {
        int child;
        AnyType tmp = array[ hole ];

        // Travel parent to child and percolate down
        for( ; (hole * d - d + 2) <= currentSize; hole = child )
        {
            // Get the first child of the parent
            child = hole * d - d + 2;
            int tmpChild = child;

            // Find the smallest child
            for(int i = 0; child + i <= currentSize && i < d ; i++)
            {
                if(array[child + i].compareTo(array[tmpChild]) < 0)
                {
                    tmpChild = child + i;
                }
            }
            child = tmpChild;

            // If smallest child is less than parent, then swap
            if( array[ child ].compareTo( tmp ) < 0 )
                array[ hole ] = array[ child ];
            else    // If parent < smallest child, exit loop
                break;
        }
        array[ hole ] = tmp;
    }
}
