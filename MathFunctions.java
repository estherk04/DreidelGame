package Functions;

public class MathFunctions
{
    public static void main(String[] args)
    {
        int[] nums = {3, 3, 4, 2};
        int target = 6;

        for (int i = 0; i < nums.length; i++)
        {
            int numOne = nums[i];
            for(int y = 0; y < nums.length; y++)
            {
                int numTwo = nums[y];
                if(numOne == numTwo)
                {
                    numTwo = nums[y++];
                }
                else if (target == numOne + numTwo)
                {
                    System.out.println(numOne + " + " + numTwo + " = " + target);
                    System.exit(0);
                }
            }
        }
        throw new IllegalArgumentException("No match found.");
    }

    public static int fun(int n, int m)
    {
        while (n>1)
        {
            System.out.println("fun: " + n);
            n = n-m;
        }
        return n;
    }
}

