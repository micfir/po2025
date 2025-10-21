package Lab3.src;

public class CodingBat{
    public static void main(String[] args) {
    }
    //Warmup 1_1
    public String backAround(String str) {
        String last = str.substring(str.length() - 1);
        return last + str + last;
    }
    //Warmup 1_2
    public int diff21(int n) {
        if (n <= 21) {
            return 21 - n;
        } else {
            return (n - 21) * 2;
        }
    }
    //String 1
    public String helloName(String name) {
        return "Hello " + name + "!";
    }
    //Array 2
    public int countEvens(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] % 2 == 0) count++;
        }
        return count;
    }

}