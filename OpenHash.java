import java.util.*;

class Node { // fields for the keys to store occurences, comparisons, and
    public int numOccur = 0;
    public int numComp = 0;
    public String keyword = "";
}

class OpenHash {

    static String[] readInput(String test)// modify input to ONLY lowercase letters
    {
        String a[] = test.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");// splits the input and deletes all puncuation
        return a;
    }

    public static void main(String args[]) {
        Scanner input_slctn = new Scanner(System.in);// input for menu options
        Scanner input_txt = new Scanner(System.in);// input for test
        ArrayList<LinkedList<Node>> hashTableStrings = new ArrayList<LinkedList<Node>>();// Declare array of linkedlists each cell will hold a linked list
        String[] arr = new String[] {};
        int count_comp = 0;
        int test_count = 0;
        while (true)// maintained until program is exited
        {
            System.out.print("\n ---------------MAIN MENU--------------- \n");
            System.out.println(" 1. Read Input Text");
            System.out.println(" 2. Hash Input Text to Hash Table");
            System.out.println(" 3. Display Words and Occurrences");
            System.out.println(" 4. Display Efficiency Outputs");
            System.out.println(" 5. Exit Program");
            int selection = input_slctn.nextInt();
            // 1.Read Input text
            if (selection == 1) {
                hashTableStrings.clear();// used to reset the Array List such that different inputs can be run
                                         // individually while maintiaing the state of the loop
                System.out.print("Enter Text: ");
                String txt = input_txt.nextLine();// right click text in terminal to paste
                arr = readInput(txt);// holds the array of words
            }
            // 2. Hash Input Text to Hash Table
            else if (selection == 2) {
                int intA = (int) 'a';// ASCII value of lower case a
                int arraySize = 26;// no loading factor needed since we only want the ASCII values of the first letter
                for (int k = 0; k < arraySize; k++) {
                    hashTableStrings.add(null);// adding null values to all cells in ArrayList
                }
                for (int i = 0; i < arr.length; i++)// iterate through array of words
                {
                    String word = arr[i];
                    int sum = 0;
                    count_comp = 0;// used to sum comparisons
                    sum = (int) word.charAt(0) - intA;// find sum of the word and normalize ASCII values to 0-25
                    int hashIndex = sum % hashTableStrings.size();// mod sum by arraysize, in other words, sum % 26
                    if (hashTableStrings.get(hashIndex) != null) // collision
                    {
                        LinkedList<Node> ll = hashTableStrings.get(hashIndex);// finds index of linked list where the
                                                                              // collision occured
                        Node node = null;
                        for (int z = 0; z < ll.size(); z++) {// iterate through
                            node = ll.get(z);
                            count_comp++;// comparison is counted after every iteration
                            if (node.keyword.equals(word)) {
                                break;// duplicate is found
                            }
                            node = null;
                        }
                        if (node == null) {// new word is found
                            node = new Node();
                            node.keyword = word;
                            node.numComp++;
                            ll.add(node);// adds word into linked list
                        }
                        node.numComp += count_comp;// sums the comparisons of every word
                        node.numOccur++;
                    } else // if no collision new linked list needs to be created
                    {
                        Node node = new Node();
                        LinkedList<Node> ll = new LinkedList<Node>();// create linked list of nodes
                        node.keyword = word;
                        // set numComp and numOccur to one because it is a new linked list
                        node.numComp = 1;// comparison to null value
                        node.numOccur = 1;// first occurence of word
                        ll.add(node);
                        hashTableStrings.set(hashIndex, ll);// set new linked list at index"hashIndex"
                    }
                }
                System.out.println("Complete");
            }
            // 3. Display Words and Occurences
            else if (selection == 3) {
                Formatter table = new Formatter();// organizes word count spacing and consistency
                System.out.println("       Key Word" + "      " + "  Word Count");
                System.out.println("   -------------------------------");
                for (int c = 0; c < hashTableStrings.size(); c++)// formatting loop
                {
                    if (hashTableStrings.get(c) != null) {
                        LinkedList<Node> llnodes = hashTableStrings.get(c);// linked list at the hash table index
                        for (int ll = 0; ll < llnodes.size(); ll++) {
                            Node node = llnodes.get(ll);
                            table.format("%15s %10s", node.keyword, node.numOccur);
                            table.format("\n");
                        }
                    }
                }
                System.out.print(table);
            }
            // 4. Display Efficiency Outputs
            else if (selection == 4) {
                int total_comp = 0;
                test_count++;
                System.out.println("Sample Test " + test_count);// format
                System.out.println("___________");
                for (int c = 0; c < hashTableStrings.size(); c++)// printing loop
                {
                    if (hashTableStrings.get(c) != null) {
                        hashTableStrings.get(c).size();// get function associated with linked list
                        LinkedList<Node> llnodes = hashTableStrings.get(c);
                        for (int ll = 0; ll < llnodes.size(); ll++) {
                            Node node = llnodes.get(ll);
                            total_comp += node.numComp;
                        }
                    }
                }
                System.out.println("Input: " + Arrays.toString(arr));
                System.out.println("Input Size: " + arr.length + " words");
                System.out.println("Comparisons: " + total_comp);
            } else if (selection == 5) {
                input_slctn.close();
                input_txt.close();
                System.exit(0);

            }
            else {
                System.out.println("\n Invalid Input");
            }
        }
    }
}
