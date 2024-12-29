package Functions;
import java.util.*;
import java.util.stream.Collectors;


public class GenericFunctions
{
    public static < E > void printArray( E[] inputArray )
    {
        // Display array elements
        for(E element : inputArray)
        {
            System.out.printf("%s ", element);
        }
        System.out.println();
    }

    public static void main(String args[])
    {
        // Create arrays of Integer, Double and Character
        Integer[] intArray = { 1, 2, 3, 4, 5 };
        Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
        Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };

        System.out.println("Array integerArray contains:");
        printArray(intArray);   // pass an Integer array

        System.out.println("\nArray doubleArray contains:");
        printArray(doubleArray);   // pass a Double array

        System.out.println("\nArray characterArray contains:");
        printArray(charArray);   // pass a Character array
        BreakConcurrentModificationException();

    }

    private static void BreakConcurrentModificationException()
    {
        List<String> toRemove = new ArrayList<String>();
        List<String> myArrayList = new ArrayList<String>();
        try
        {
            myArrayList = Arrays.stream("1,2,3,4,5,6".split(",")).collect(Collectors.toList());
            toRemove = Arrays.stream("1,3,5".split(",")).collect(Collectors.toList());

//            for (String str : myArrayList)
//            {
//                if (toRemove.contains((str)))
//                {
//                    myArrayList.remove(str);
//                }
////            }

            for(int x = 0; x < toRemove.size(); x++)
            {
                String y = toRemove.get(x);
                if (myArrayList.contains(y))
                {
                    myArrayList.remove(y);
                }
            }

            printArray(myArrayList.toArray());
        }
        catch (Exception ex)
        {
            System.out.printf("An error occurred while breaking the process: " + ex.getMessage());
        }

        finally
        {
            //Do something
        }
    }
}
