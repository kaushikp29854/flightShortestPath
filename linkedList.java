

//Element class contains Node class and linked list functions
public class linkedList<Element> {

	//protected varibaled for linked list
    protected Node head;
    protected Node tail;
    protected int count;

    // Create an empty linked list constructor 
    public linkedList() {
    	//set everything to null
    	 head = null;
         tail = null;
         count = 0;
    }

   
    //Check if the element is in the linked list
    public boolean contains(Element element) {
    	//set the current node to head
        Node current = head;

        //traverse through the entire linked list
        while (current != null) {
        	//if the current node contains the element that is passed in we have found the value we are looking for
            if (current.element.equals(element)) {
            	//return true if found
                return true;
            }
            
            //set the current node to the next to traverse
            current = current.next;
        }
        //return false if not found while traversing through the entire linked list
        return false;
    }

    // Check if the list is empty
    public boolean isEmpty() {
    	
    	//check to see if there are any nodes in the linked list by checking if count is zero
    	if(count == 0)
    	{ 
    		//if count is zero return true
    		return true;
    	}else 
    		return  false;
 
    }

    //Get the index of the element in the list
    public int indexOf(Element element) {
    	//set the current node to head
        Node current = head;
        int index = 0;

        //traversing through the linked list 
        while (current != null) { 	
        	//if we find the element return the value of that element
            if (current.element.equals(element)) {
                return index;
            }

            //increment index 
            index++;
            //traverse to the next node
            current = current.next;
        }
        //if element not found return -1
        return -1;
    }

    // Insert a new element into the linked List
    public void add(Element element) {
        Node node = new Node(element);

        if (isEmpty()) {
        	//if it is empty insert a new node setting element to head and tail 
            head = node;
            tail = node;
        } else {
        	//add the new node to the end of the linked list
            tail.next = node;
            tail = node;
        }

        //increment the count of the linked list 
        count++;
    }

    // Get the total number of nodes in the linked list
    public int size() {
        return count;
    }

    // Get an element from the specified index
    public Element get(int index) {
    	
    	//check BASE CASES
        if(index < 0 || index >= size()) {
            return null;
        }

        //create a node and set it to head(USED FOR TRAVERSAL
        Node current = head;

        //keep traversing the linked list until we reach the index
        for(int i = 0; i < index; i++) {
            current = current.next;
        }
        //retun the index
        return current.element;
    }

    // We use nodes to link together to form a linked list
    protected class Node {

        public Element element;
        public Node next;

        // Create a new node
        public Node(Element element) {
            this.element = element;
        }
    }
}