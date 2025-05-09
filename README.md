# campus-navigation-java
Pathfinding algorithm using custom stack and backtracking
## 🧠 Overview
This project simulates a student navigating the campus of Western University to reach Middlesex College — while avoiding dangerous geese, snow piles, and picking up books along the way!

Built as part of **CS1027B – Computer Science Fundamentals II**, this program implements a custom `ArrayStack` and a pathfinding algorithm that handles movement through a hex-based map grid.

## 📌 Features
- ✅ Custom generic `ArrayStack` (no built-in Java collections)
- ✅ Stack-based pathfinding with backtracking
- ✅ Logic-based decision-making using cell types: grass, book, snow, goose, start, end
- ✅ Marks visited cells and avoids retracing steps unless backtracking

## 🔍 Files Included
- `ArrayStack.java` – Custom implementation of a generic stack data structure
- `CampusWalk.java` – Main class that loads a map, determines best moves, and finds the path to the exit

> ⚠️ Note: This code depends on instructor-provided files like `Map`, `Hexagon`, and text/image files which are not included due to academic policy. If you're from the same course, you'll need to place this in the same project folder to run it.

## 🧪 Sample Output
The path taken is printed as a list of cell IDs:
6 8 11 14 17 20 21 22 25 28 31 34 37 40 43 46 49 50 53 56 59 60

This represents the sequence of steps from the start cell to Middlesex College while following all the rules.

## 📚 Concepts Used
- Custom data structures (stack)
- Stack-based depth-first search (DFS)
- Recursion
- Decision logic
- Basic GUI interaction (via provided visual map)

## 🧑‍💻 Author
**Tanya Sahota**  
Western University  
March 2025  
CS1027B – Assignment 3
