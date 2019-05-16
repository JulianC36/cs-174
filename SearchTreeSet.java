import java.util.*;

public class SearchTreeSet {
    
	public static SearchTreeSet test;
	 private Node root;
	 private List<String> wordList;
		 
	public static void main(String[] args) {
		
		  test = new SearchTreeSet();
	        test.insert("apple");
	        test.insert("store");
	        test.insert("car");
	        test.insert("atom");
	        test.insert("place");
	        test.insert("book");
	        test.insert("test");
	        	        
	      
	        for(String s: test.display()){
	            System.out.println(s);
	        }
	        
	        test.delete("test");
	        
	        System.out.println(test.search("test"));
	        System.out.println(test.size());
	        System.out.println(test.isEmpty());
	        
	        test.clearMap();
	        
	        System.out.println(test.size());
	        for(String s: test.display()){
	            System.out.println(s);
	        }
	        System.out.println(test.isEmpty());
	        
	        
		
	}//end of main

	
	
	    public SearchTreeSet(){
	        root = new Node();
	    }

	    public SearchTreeSet(String word){
	        this();
	        this.insert(word);
	        
	    }

	    public void insert(String word){
	    	Node.size++;
	        root.insert(word.toLowerCase());
	    }

	    public List<String> display(){
	        wordList = root.getWords(root, "");
	        return wordList;
	    }

	    public boolean search(String word){
	    	return root.containsWord(root, word);
	    }
	    
	    	    
	    public void delete(String word) {
	    	Node.size--;
		    root.delete(root, word, 0);
		}
	    
	    public int size() {
	    	return Node.size;
	    }
	    
	 
	     public boolean isEmpty() {
	    	int size = Node.size;
	    	if(size == 0) {
	    		return true;
	    	}
	    	else {
	    		return false;
	    	}
	     }
	     
	   public void clearMap() {
		   Node.size = 0;
		   root.clearTree();
		   
	   }
	    
		 
		
}//end of class























