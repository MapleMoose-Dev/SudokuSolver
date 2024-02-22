/*
 
    TABLE CLASS DOCUMENTATION

    CONSTRUCTORS:
        table(); Creates new table with an empty array
        table(tableType[]); Creates a new table with the sent array

    METHODS:
    size(): int; returns how many elements are in the array
    
    print(): void; Prints out the array in the format: "0 1 2 3 4 ..... \n"

    clear(): void; wipes the array,
    
    insert(int value): void; Inserts 'int' element at the end of the array

    insert(int value, int atIndex): void; Inserts 'int' element at position 'int' array, everything to the right of index gets shifted right

    clone(): table<tableType>; Returns a new table with the same values of this.
        ex. table<tableType> anotherTbl = originalTbl.clone();

    find(int value): int; Searches array for value, returns the first index with value, else '-1'

    remove(int atIndex): void; removes whatever element is at index 'int'. If removed index is before the end, everything past it shifts left
    
    at(int atIndex): tableType; returns whatever element is in the array at index 'int'

    toArray(): int[]: returns the table but in array form. Array will be shrunk to quantity used

    ** DEFINITION - FROZEN: When a table is frozen, it cannot be changed by the table class and is set to read only. 
                            While frozen, the array can still be changed by indexing: 'myTable.array[0] = n'. 
                            table.insert(n , 0); // this function does nothing while the table is frozen. 
    
    freeze(): void; Freeze a table
    
    thaw(): void; Thaws (un-freeze) a table
    
    isFrozen(): boolean; returns true if the table is frozen, returns false if the table is not frozen. 
 */



/**
 * table Class; An array with extra functionality
 * Influenced by lua:
 * https://create.roblox.com/docs/reference/engine/libraries/table
 *
 * @author Noah Bulas
 * @version V 2.0 - Al.28,23
 */
package Table;

public class IntTable extends abs_table {


    private static final int nullValue = -1; // whats considered null in the array
    private static final int defaultTableSize = 10; // What the table starts off as, every time is needs more space, it                           
    private int sizeOfTable = defaultTableSize; // the size of the array
    private int used = 0; // how many elements are in the array
    // ----------------------------------------------------
    // ------------------Variables------------------------
    // ----------------------------------------------------

    // private variables
    protected int[]  array;

    // ----------------------------------------------------
    // ------------------Constructors----------------------
    // ----------------------------------------------------
    /**
     * Default Constructor, initializes table
     */
    public IntTable() {
        array = new int[defaultTableSize];
    }

    /**
     * Constructor, initializes table with given array
     * @param sentArray Array to initialize with
     */
    public IntTable(int[] sentArray) {
        array = new int[sentArray.length];
        
        for (int i = 0; i < sentArray.length; ++i)
            array[i] = sentArray[i];

        sizeOfTable = sentArray.length;
        used = sentArray.length;
      }



      /**
       * Expands current array by int 'defaultTableSize'
       */
      private void expandTable() {
          // If array is full, grow array
          sizeOfTable *= 2;
          int[] newTable = new int[sizeOfTable];
          for (int i = 0; i < used; ++i)
              newTable[i] = array[i];
          array = newTable;
      }



    // ----------------------------------------------------
    // ------------------Member Methods--------------------
    // ----------------------------------------------------
    /**
     * Size of the array (elements used)
     * @return int; number of elements in the array
     */
    public int size() {
        return used;
    }

    /**
     * Prints the array in the format:
     * 0 1 3 4 6 7 8 9 10 11 12 13 14 15
     */
    public void print() {
        for (int i = 0; i < size(); ++i) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    /**
     * Clears the array to empty
     */
    public void clear() {
        if (frozen)
            return;
       used = 0;
    }

    /**
     * Inserts element into the array, (at the end)
     * @param value value that's inserted into the array
     */
    public void insert(int value) {
        if (frozen)
            return;

         if (used >= sizeOfTable)
            expandTable();

        array[used] = value;
        ++used;
    }

    /**
     * Inserts elements into the array at specified index, everything to the right of the index gets shifted right
     * @param value   value that's being inserted into the array
     * @param atIndex what index that value is being inserted at
     */
    public void insert(int value, int atIndex) {
        if (frozen)
            return;

        if (atIndex > used || atIndex < 0)
            return;
            
        if (used >= sizeOfTable)
            expandTable();

        for (int i = used; i >= atIndex; --i) {
            if (i - 1 >= 0)
                array[i] = array[i - 1];
        }

        array[atIndex] = value;
        ++used;
    }

    /**
     * Clones the table, the table being indexed remains unchanged
     * @return a new intTable object.
     */
    public IntTable clone() {
        IntTable newTable; // Create new table
        newTable = new IntTable(array);
        return newTable;
    }

    /**
     * Searches the array for the specified value
     * @param value Value to search for
     * @return returns the index when the value first occurred, otherwise -1
     */
    public int find(int value) {
        for (int i = 0; i < size(); ++i) {
            if (value == array[i])
                return i;
        }
        return -1;
    }


    /**
     * returns whichever element is at the specified index
     * @param atIndex the index to get an element from
     * @return the element at the index
     */
    public int at(int atIndex) {
        return array[atIndex];
    }

    /**
     * Removes whichever element is at the specified index
     * @param atIndex the index to remove an element from
     */
    public void remove(int atIndex) {
        if (frozen)
            return;
        if (atIndex >= used || atIndex < 0)
            return;

        array[atIndex] = nullValue;
        for (int i = atIndex; i < used; ++i) {
            if (i + 1 < sizeOfTable) {
                array[i] = array[i + 1];
            }
        }
        --used;
        array[used] = nullValue;
    }

    /**
     * Sorts the array from least to greatest.
     * ex. {4 10 9 8 2 7 6 5 4 3 2 1 0 -1 -2 -3 -4} to {-4 -3 -2 -1 0 1 2 2 3 4 4 5
     * 6 7 8 9 1 0}
     */
    public void sort() {
        if (frozen)
            return;

        int[] temporaryArray = new int[used]; // Create temporary array
        for (int i = 0; i < used; ++i) // copy everything over
            temporaryArray[i] = array[i];
        java.util.Arrays.sort(temporaryArray); // sort
        for (int i = 0; i < used; ++i) // move everything back
            array[i] = temporaryArray[i];
    }

    /**
     * Resizes the array so its capacity only fits its elements
     * ex. an array of size 30 with 26 elements will become an array of size 26 with
     * 26 elements
     */
    public void shrinkToFit() {
        if (frozen)
            return;

        int[] newArray = new int[used]; // Create temporary array
        for (int i = 0; i < used; ++i) // copy everything over
            newArray[i] = array[i];
        array = newArray;
        sizeOfTable = used;
    }

    /**
     * Searches the array for the specified value
     * 
     * @return returns the table but in array form. Array will be shrunk to quantity used
     */
    public int[] toArray() {
        int[] tempArray = new int[used];
        for (int i = 0; i < used; ++i) {
            tempArray[i] = at(i);
        }
        return tempArray;
    }

}
