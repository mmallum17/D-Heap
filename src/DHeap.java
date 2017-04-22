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
     *                     int d -> the d value of the D-Heap           *
     *  OUTPUT: None 		              		                        *
     ********************************************************************/
    public DHeap( AnyType [ ] items , int d)
    {
        setD(d);
        currentSize = items.length;
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
        if( currentSize == array.length - 1 )
            enlargeArray( array.length * 2 + 1 );

        // Percolate up
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
    private void enlargeArray( int newSize )
    {
        AnyType [] old = array;
        array = (AnyType []) new Comparable[ newSize ];
        for( int i = 0; i < old.length; i++ )
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
        if( isEmpty( ) )
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
        if( isEmpty( ) )
            throw new Exception( );

        AnyType minItem = findMin( );
        array[ 1 ] = array[ currentSize-- ];
        percolateDown( 1 );

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
        for(int i = 1; i <= currentSize; i++)
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

        for( ; (hole * d - d + 2) <= currentSize; hole = child )
        {
            // Get first child
            child = hole * d - d + 2;
            int tmpChild = child;

            // Find min child
            for(int i = 0; child + i <= currentSize && i < d ; i++)
            {
                if(array[child + i].compareTo(array[tmpChild]) < 0)
                {
                    tmpChild = child + i;
                }
            }
            child = tmpChild;

            // If smallest child is less than parent, swap
            if( array[ child ].compareTo( tmp ) < 0 )
                array[ hole ] = array[ child ];
            else
                break;
        }
        array[ hole ] = tmp;
    }
}
