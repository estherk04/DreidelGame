package Functions;

public class Searches
{
    // Sequential Search
    public static int sequentialSearchMethod (int [] array, int value)
    {
        int ans = -1;
        for (int index = 0; index < array.length; index++)
        {
            if (array[index] == value)
            {
                ans = index;
                return ans;
            }
        }
        return ans;
    }

    // Binary Search
    public static int binarySearchMethod (int [] array, int value)
    {
        int first = 0;
        int last = array.length - 1;
        boolean found = false;
        int ans = -1;
        while (!found && ans != value)
        {
            int mid = (first + last) / 2;
            if (array[mid] == value)
            {
                ans = value;
                return ans;
            } else if (array[mid] > value)
            {
                last = mid - 1;
            } else
            {
                first = mid + 1;
            }
        }
        return ans;
    }
}
