import java.util.*;

public class marbleSolution {

    //This would be initialized to be the full collection of marbles.
    //Probably need to read it in from the... JSON it's stored as?
    private static HashSet<Marble> fullCollection = new HashSet<>();
    private Comparator<Marble> comparisonThing;

    /*
     * Current idea is to first sort the marbles by just immediately placing them in "Bins" (hashsets) based on color.
     * After that, filter out all marbles in the bins with weight < 0.5.
     * Then filter out non-palindromic names.
     * Then sort by ID.
     * I think it's best to do it this way because Color is an over-arching order and immediately gets you closer to a
     * totally ordered set. Then weight for each, which is an easy binary choice and should immediately drop a large
     * amount. Checking the names comes last because iterating over every name is gonna be slow and costly regardless,
     * so it's best to minimize the number of times you have to do that. Finally, sort by id ascending order and combine
     * for the final product. There's probably ways to optimize further, but I think this is fine even for large-ish
     * datasets.
     */
    public static void main(String[] args) {
        System.out.println("Here's where the solution goes");

        HashSet<Marble> redBin = new HashSet<Marble>();
        HashSet<Marble> orangeBin = new HashSet<Marble>();
        HashSet<Marble> yellowBin = new HashSet<Marble>();
        HashSet<Marble> greenBin = new HashSet<Marble>();
        HashSet<Marble> blueBin = new HashSet<Marble>();
        HashSet<Marble> indigoBin = new HashSet<Marble>();
        HashSet<Marble> violetBin = new HashSet<Marble>();

        //Iterates through the full collection and places it into the appropriate bin.
        for (Marble m : fullCollection) {
            switch (m.color) {
                case "red":
                    redBin.add(m);
                    break;
                case "orange":
                    orangeBin.add(m);
                    break;
                case "yellow":
                    yellowBin.add(m);
                    break;
                case "green":
                    greenBin.add(m);
                    break;
                case "blue":
                    blueBin.add(m);
                    break;
                case "indigo":
                    indigoBin.add(m);
                    break;
                case "violet" :
                    violetBin.add(m);
                    break;
                default:
                    break;

            }
        }

        //Up next, go through each of the bins to empty out all the non-palindromic ones.
        //This could end up looking gross, so maybe in the future rearrange the order of steps? We'll see
        for (Marble m: redBin) {
            if (isPalindrome(m.name) == false){
                redBin.remove(m);
            }
        }
        for (Marble m: orangeBin) {
            if (isPalindrome(m.name) == false){
                orangeBin.remove(m);
            }
        }
        for (Marble m: yellowBin) {
            if (isPalindrome(m.name) == false){
                yellowBin.remove(m);
            }
        }
        for (Marble m: greenBin) {
            if (isPalindrome(m.name) == false){
                greenBin.remove(m);
            }
        }
        for (Marble m: blueBin) {
            if (isPalindrome(m.name) == false){
                blueBin.remove(m);
            }
        }
        for (Marble m: indigoBin) {
            if (isPalindrome(m.name) == false){
                indigoBin.remove(m);
            }
        }
        for (Marble m: violetBin) {
            if (isPalindrome(m.name) == false){
                violetBin.remove(m);
            }
        }

        //...Alright yeah, admittedly that looks pretty gross. I'm sure it can be cleaned up, and it certainly wouldn't
        // be terribly difficult. But it's also probably fine for now.

        List<Marble> filteredCollection = new ArrayList<>();

        //Didn't want to use a parameterized constructor call because it'd only take one, afaik
        //Also I like the... not symmetry, but... neatness of this? I dunno.
        filteredCollection.addAll(redBin);
        filteredCollection.addAll(yellowBin);
        filteredCollection.addAll(orangeBin);
        filteredCollection.addAll(greenBin);
        filteredCollection.addAll(blueBin);
        filteredCollection.addAll(indigoBin);
        filteredCollection.addAll(violetBin);

        //got to quickly figure out the sorting, hold on
        //I always forget how wonky comparator is.
        filteredCollection.sort();

    }

    /*
     * Is it super necessary to make an inner class for this? Maybe not. But I think it makes it cleaner and faster for
     * grokking and probably a little simpler to interact with, code-wise.
     */
    class Marble implements Comparator<Marble>, Comparable<Marble> {
        Integer id;
        String color = "";
        String name = "";
        Float weight;

        //One constructor because this is a practice problem, it's not hard to add more or change it to handle missing data
        public Marble (int i, String c, String n, float w){
            this.id = i;
            this.color = c;
            this.name = n;
            this.weight = w;
        }

        @Override
        public String toString(){
            return "id: " + this.id.toString() + ", "
                    + "color: " + this.color + ", "
                    + "name: " + this.name + ", "
                    + "weight: " + this.weight.toString();
        }

        //Let's hope this works. Gotta test this properly soon
        @Override
        public int compare(Marble o1, Marble o2) {
            return o1.id - o2.id;
        }

        //This'n too
        @Override
        public int compareTo(Marble m4){
            return (this.id).compareTo(m4.id);
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