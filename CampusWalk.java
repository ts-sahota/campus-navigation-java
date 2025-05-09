
/*
CS 1027B – Assignment 3
Name: Tanya Sahota
Student Number: 251446953
Email: tsahot@uwo.ca
Created: March 17, 2025
*/

public class CampusWalk {
    private Map map; // Map representing the campus
    
    // Constructor to initialize the map object with the given filename
    public CampusWalk(String filename, boolean showMap) {
    	// If showMap is true, it shows the visual map, otherwise, it hides it
        try {
            map = new Map(filename);
            if (showMap) {
                map.showGUI();
            } else {
                map.hideGUI();
            }
        } catch (Exception e) {
            System.out.println("Error occurred"); // If an exception is caught, "Error occurred" is printed
        }
    }
    // Count and return the number of goose cells surrounding the given cell
    public int neighbourGooseCount(Hexagon cell) {
        int count = 0;
        for (int i = 0; i < 6; i++) { // Hexagon has up to 6 neighbours
            Hexagon neighbour = cell.getNeighbour(i);
            if (neighbour != null && neighbour.isGooseCell()) { // Check if a neighbour is not null before checking its type
                count++;
            }
        }
        return count; 
    }
    // Determine the next cell to walk onto from the current cell
    // Use rules to determine next cell; 
    public Hexagon findBest(Hexagon cell) {
    	
    	// 1. If curr is adjacent to the end cell, go to the end cell
        for (int i = 0; i < 6; i++) {
            Hexagon neighbour = cell.getNeighbour(i);
            if (neighbour != null && neighbour.isEnd()) {
                return neighbour; // Return the Hexagon object representing the next cell to walk onto from the current cell if one exists; otherwise return null
            }
        }
        
        // 2. If curr is adjacent to one or more cells that contain a book, go to the book cell neighbour with the smallest index (0-5)
        for (int i = 0; i < 6; i++) {
            Hexagon neighbour = cell.getNeighbour(i);
            if (neighbour != null && neighbour.isBookCell() && !neighbour.isMarked()) {
                if (neighbourGooseCount(neighbour) < 3) {
                    return neighbour;
                }
            }
        }
        
        // 3. If curr is adjacent to one or more grass cells, go to the grass cell neighbour with the smallest index that has the lowest number of goose cells adjacent to a given cell
        Hexagon bestGrass = null;
        int minGooseCount = Integer.MAX_VALUE;
        for (int i = 0; i < 6; i++) {
            Hexagon neighbour = cell.getNeighbour(i);
            if (neighbour != null && neighbour.isGrassCell() && !neighbour.isMarked()) {
                int gooseCount = neighbourGooseCount(neighbour);
                if (gooseCount < 3 && gooseCount < minGooseCount) {
                    minGooseCount = gooseCount;
                    bestGrass = neighbour;
                }
            }
        }
        if (bestGrass != null) {
            return bestGrass;
        }
        // 4. If curr is adjacent to one or more snow cells, go to the snow cell with the smallest index
        for (int i = 0; i < 6; i++) {
            Hexagon neighbour = cell.getNeighbour(i);
            if (neighbour != null && neighbour.isSnowCell() && !neighbour.isMarked()) {
                if (neighbourGooseCount(neighbour) < 3) {
                    return neighbour;
                }
            }
        }
        
        // 5. If none of these conditions are met, return null to indicate that you cannot proceed and must backtrack
        return null;
    }
    
    public String findPath() {
        ArrayStack<Hexagon> stack = new ArrayStack<>(); // Initialize a stack
        stack.push(map.getStart()); // Push the start cell onto the stack
        map.getStart().markInStack(); // Mark the cell as In-Stack
        boolean running = true; // Set running to true
        StringBuilder path = new StringBuilder(); // Store the path as a string

        while (!stack.isEmpty() && running) { // Loop while the stack isn't empty and running is true
            Hexagon curr = stack.peek(); // Peek at the top cell
            path.append(curr.getID()).append(" "); // Update the path string with curr's ID
            if (curr.isEnd()) { // Check if curr is the exit cell (MC)
                running = false; // If yes, set running to false and stop searching
                break;
            }

            Hexagon next = findBest(curr); // Find the next move using findBest(curr)
            if (next == null) { // If no move exists, backtrack
                stack.pop(); // Pop the stack
                curr.markOutStack(); // Mark the cell as Out-of-Stack
            } else { // If a move exists
                stack.push(next); // Push the next cell onto the stack
                next.markInStack(); // Mark the next cell as In-Stack
            }
        }

        if (!running) { // If the end cell was found, return the path
            return path.toString().trim(); 
        } else { // If the stack is empty, return "No path found"
            return "No path found"; 
        }
    }

    // Call map.exit() (forces map to be closed when a test case is completed)
    public void exit() {
        map.exit();
    }
    
    // Main method for testing and debugging path-finding algorithm
    public static void main(String[] args) {
        Hexagon.TIME_DELAY = 500; 
        String file = "map1.txt"; 
        CampusWalk walk = new CampusWalk(file, true);
        String result = walk.findPath();
        System.out.println(result);
    }
}


