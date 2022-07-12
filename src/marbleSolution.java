import java.util.Locale;

public class marbleSolution {

    public static void main(String[] args) {
        System.out.println("Here's where the solution goes");
    }

    /*
     * Is it super necessary to make an inner class for this? Maybe not. But I think it makes it cleaner and faster for
     * grokking and probably a little simpler to interact with, code-wise.
     */
    class Marble {
        int id;
        String color = "";
        String name = "";
        float weight;

        //One constructor because this is a practice problem, it's not hard to add more or change it to handle missing data
        public Marble (int i, String c, String n, float w){
            this.id = i;
            this.color = c;
            this.name = n;
            this.weight = w;
        }
    }

    /*
     * So off the top of my head, there's two simple ways to go about this.
     * 1) Two Counters method. Instantiate two vars that hold the value of the chars at the beginning and end,
     * then check to see if they're equal. If they are, repeat until they hit the same index, then return true.
     * 2) Compare the Reverse. Instantiate another String by copying from the original input, but iterating backwards.
     * Then just check if the two strings are equal. If they differ, false, else return true.
     *
     * I like the second method for this one. Given the nature of the input, they're likely to be short enough Strings
     * that instantiating a second shouldn't be THAT resource intensive. But we'll see, I suppose. The first method
     * also requires futzing about with index variables, and I'm fed up with OutOfBoundsExceptions lately.
     */
    private static boolean isPalindrome(String name){

        name = cleanUpName(name);
        //Create a reversed version of the input
        String reverse = "";
        for (int i = name.length()-1; i >= 0; i--){
            reverse = reverse.concat(String.valueOf(name.charAt(i)));
        }
        //check if the two are equal. 90% sure you can omit the return true statement here, but frankly I've never liked
        //that because I feel it reduces readability.
        if (reverse.equals(name))
            return true;
        else
            return false;
    }


    /*
     * Method for processing names before checking if they're palindromes. Does there already exist a way to do this?
     * Probably. But this isn't particularly complicated soooo
     */
    private static String cleanUpName (String name){
        String output = "";
        output = name.toLowerCase(Locale.ROOT);
        output = output.replaceAll("\\s","");
        output = output.replaceAll("'", "");
        output = output.replaceAll("-", "");
        return output;
    }

}