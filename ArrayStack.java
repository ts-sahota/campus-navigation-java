
/*
CS 1027B – Assignment 3
Name: Tanya Sahota
Student Number: 251446953
Email: tsahot@uwo.ca
Created: March 15, 2025
*/

public class ArrayStack<T> implements StackADT<T> {
    private T[] array; // Create an array to hold items in the stack
    private int top;   // Index of top element
    public ArrayStack() { // Constructor to initialize the array with a capacity of 10
        this(10);  
    }

    // Constructor to initialize array with a capacity of initCapacity
    public ArrayStack(int initCapacity) {
        array = (T[]) new Object[initCapacity];  
        top = initCapacity - 1; // Stack starts empty
    }

    public void push(T element) {
    	// Check if the array is full and, if so, expand its capacity
    	
        if (top == -1) {
            expandCapacity(); 
        }
        
        // Add the element to the top of the stack (rightmost available cell in the array) and update the value of top
        array[top] = element; 
        top--;  
    }
    
    public T pop() throws CollectionException {
    	
    	//  If the stack is empty, throw a CollectionException with the message "Stack is empty"
        if (isEmpty()) {
            throw new CollectionException("Stack is empty");
        }
        
        // Remove and return the element from the top of the stack, and update the value of top
        top++;  
        T element = array[top];  
        array[top] = null;  
        return element; 
    }

    public T peek() throws CollectionException {
    	
    	// If the stack is empty, throw a CollectionException with the message "Stack is empty"
        if (isEmpty()) {
            throw new CollectionException("Stack is empty");
        }
        
        // Return the element from the top of the stack without removing it
        return array[top + 1];  
    }

    // Return true if the stack is empty or false otherwise
    public boolean isEmpty() {
        return top == array.length - 1; 
    }

    // Return the number of elements in the stack
    public int size() {
        return array.length - 1 - top;  
    }

    // Return the length (capacity) of array
    public int getCapacity() {
        return array.length;  
    }

    // Return the top index
    public int getTop() {
        return top;  
    }

    public String toString() {
    	
    	// If the stack is empty, return "Empty stack."
        if (isEmpty()) {
            return "Empty stack.";
        }
        
        // Otherwise, build and return a string containing all the items in the stack starting from the top (first) to the bottom (last), separated by a comma and a single space
        StringBuilder sb = new StringBuilder();  
        for (int i = top + 1; i < array.length; i++) {
            sb.append(array[i]);  
            if (i < array.length - 1) {
                sb.append(", ");  
            }
        }
        return sb.toString();  
    }

    // Only be called when the array is full and another item needs to be pushed on
    private void expandCapacity() {
        int newCapacity;
        
        // If the current array capacity is 15 or less, then expand it by doubling its current capacity
        if (array.length <= 15) {
            newCapacity = array.length * 2;  
        
        // Otherwise, expand it by adding 10 additional spaces in the array
        } else {
            newCapacity = array.length + 10;  
        }

        // Create a new array of type T with a larger capacity
        T[] newArray = (T[]) new Object[newCapacity];

        // Copy elements from old array to new array
        System.arraycopy(array, top + 1, newArray, newCapacity - size(), size());

        // Adjust top index to match new array size
        top = newCapacity - size() - 1;

        array = newArray;

    }
}

