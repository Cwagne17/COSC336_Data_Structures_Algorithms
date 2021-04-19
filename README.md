# COSC336_Data_Structures_Algorithms

### How to run the program
1. Compile the program using 
```
g++ -o <some name you want to run as executable> main.cpp
```
2. Run the program
```
./<name used in compile>
```
3.  Follow prompts from program

### Requirements
(B1). Implement a Red-Black tree with only operation Insert(). Your program should read from a file that contain
positive integers and should insert those numbers into the RB tree in that order. Note that the input file will only
contain distinct integers. Print your tree by level using positive values for Black color and negative values for Red color.
Do not print out null nodes.
Format for a node: (<Node_value>, <Parent_value>). For example, the following tree is represented as
(12, null)
(5, 12) (15, 12)
(3, 5) (-10, 5) (13, 15) (17, 15)
(-4, 3) (7, -10) (11, -10) (-14, 13)
(-6, 7) (-8, 7)

(B2). Implement a randomized Skip-List with operations Insert(), Delete() and Search(). Your program should read from
the input file just like in (B1). Print out the skip-list using the format given below
16
16 71 91
2 16 71 89 91
2 10 15 16 31 71 86 89 91 96

(B3). Implement Dijkstra’s algorithm on an undirected graph. The input file would contain (1) the pair of source and
destination “u v” in the first row, and (2) the edge list with the associated weight (of type double) for each edge in the
graph. Output the sequence of nodes that are on the shortest path from “u” to “v” with the total weight. Provide your
own test cases. Turn in your code and test cases (i.e., “.java” or “.c/.cpp” and test files) only.
Note: RECURSION is NOT allowed at all. Your implementation can only contain iterative methods.