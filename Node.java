/*Node.java				Authors: Castilleja, Bedrossian			Date: 5/10/2019
 * a program that creates a node object to be used in a SearchTreeSet. 
 * has functions 
 * 
 */



import java.util.*;


    class Node {

	 //Variables used in addWords

    private boolean terminal;
    private boolean hasChildren;
    private char character;
    private Map<Character, Node> children = new HashMap<>();
    static int size = 0;

    //Variables for search method
    private static List<String> wordList = new ArrayList();;


    public Node(){
        hasChildren = false;
        terminal = false;
    }

    public Node(String word){
        this();
        insert(word);
    }
    
    
    
    public Map<Character, Node> getChildren(){
    	return children;
    }

    /* Recursively add nodes to the tree. Each node contains a map with letters.
     * If the next letter in a word is not inside the map, it is added.
     * */
    public void insert(String word){

    	 char firstChar = word.charAt(0);

        //If current node has no more letters, add subsequent letters
        if (children.get(firstChar) == null){
            this.hasChildren = true;
            // If word has more than 1 character left
            if(word.length() > 1){
                Node tn = new Node();
                children.put(firstChar, tn);
                children.get(firstChar).insert(word.substring(1));
            }
            // If only 1 character remains in the word
            else{
                children.put(firstChar, new Node());
                children.get(firstChar).terminal = true;
            }
            // Once the node was added, set the character value for future reference
            children.get(firstChar).character = firstChar;
        }

        // If first letter is already in the index, send subsequent characters into that node.
        else {
            children.get(firstChar).insert(word.substring(1));
        }
        
       
    }
    
    

    // Static method starts with Root and blank values. 
    public static List<String> getWords(Node node, String chars){

        chars += node.character;
        // Traverse trie adding characters to the string until a word is found
        if(node.terminal){
            wordList.add(chars.trim());
        }
        // If more letters still remain, continue traversing
        if(node.hasChildren){
            for(Character c: node.children.keySet()){
                getWords(node.children.get(c), chars);
            }
        }
        // If the end of a branch is reached, reset the string
        else{
            chars = "";
        }

        // Sort and return the list of words
        Collections.sort(wordList);
        return wordList;
    }


    static boolean hasWord = false;
     static boolean containsWord(Node node, String word){

        char letter = word.charAt(0);
        // If current node does not contain next letter, word isn't in list
        if(node.children.get(letter) == null)
            hasWord = false;
        // If the former if passed, and 1 character remains, check to see if last char node is a word
        else if(word.length() == 1){
            if(node.children.get(letter).terminal == true)
                hasWord = true;
            else
                hasWord = false;
        }
        // If more letters remain, recursively call this method with a substring
        else{
            containsWord(node.children.get(letter), word.substring(1));
        }
        
       return hasWord;
               
    }
     
     
     static boolean delete(Node current, String word, int index) {
    	 
    	    if (index == word.length()) {
		        if (!current.terminal) {
		            return false;
		        }
		        current.terminal = false;
		        return current.children.isEmpty();
		    }
		    char ch = word.charAt(index);
		    Node node = current.children.get(ch);
		    if (node == null) {
		        return false;
		    }
		    boolean shouldDeleteCurrentNode = delete(node, word, index + 1) && !node.terminal;
		 
		    if (shouldDeleteCurrentNode) {
		        current.children.remove(ch);
		        return current.children.isEmpty();
		    }
		    return false;
		}
	
     public void clearTree() {
    	 wordList.clear();
    	 children.clear();
     }
	
}
