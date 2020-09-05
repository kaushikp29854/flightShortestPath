//stack class contains basic functions of a stack implemented as a linked list
//Inserting and deleting occurs at the end of the linked list
public class stack<Element> extends linkedList<Element>{

    //push element to the end of the linked list
    public void push(Element element) {
        add(element);
    }

    //Traverse to the tail and retrieve that element & delete from linked list
    public Element pop() {
    	
    	//retrieving the last element
        Element lastElement = tail.element;

        //**FIXING STRUCTURE OF THE LINKED LIST**
        
        Node previous = null;
        
        //set the current node to head
        Node current = head;

        //traverse linked list updating previous and current nodes
        while (current.next != null) {
            previous = current;
            current = current.next;
        }
      //after iterating through while loop, previous is at the second to last node of LL and current is the 
      //new tail of the linked list
        
        //if previous is not empty
        if (previous != null) {
        	//set the element we popped to Null
            previous.next = null;
            //updating tail value as previous
            tail = previous;
        } else {
            head = null;
            tail = null;
        }

        //decrement the count
        count--;

        //return the popped node
        return lastElement;
    }
    
    // Return the tail but do not remove from the linked list
    public Element peek() {
        if(tail != null) {
            return tail.element;
        }
        //if no tail exists or LL is empty
        return null;
    }
}