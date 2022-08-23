Kyler Sood
5734940 | sood0027@umn.edu
CSCI 4041 (Algorithms and Data Structures) Spring 2022 with Dr. James Moen

Below are details of enclosed files:

Node.java
A Node class used to develop the singly linked list data structure. This has two attributes: a value and a pointer to the next node.

LinkedList.java
Wrapper class for the linked lists. Contains methods that operate on linked lists (add, remove, merge, etc). that are used in the Graph algorithms.

Polynomial.java
Polynomial class used to represent chromatic polynomials. Represents coefficients as an array and keeps track of the degree of the polynomial.
If a leading coefficient becomes zero, it accordingly adjusts the polynomial degree. Has add, subtract, and evaluate functions.

Graph.java
Graph class using a linked adjacency structure. Contains delete/contract methods and constructors from both a linked list, adjacency matrix,
and manual construction from vertices and edges.
Note: This class is used without separate Vertex/Edge classes. Because the methods are not implemented in-place (which would destroy the graph
during the chromatic polynomial computation), implementing these classes is unnecessary and requires that we make a deep copy of the graph each time.

You can construct a graph by inputting a square, symmetric adjacency matrix into the constructor and using the chromatic polynomial methods to
determine the chromatic polynomial of the graph. There are two methods, one which displays the before/after outcome of each deletion/contraction 
operation and one which does not display this.


Kyler Sood CSCI 4041 Honors Project.pdf
A .pdf writeup containing details about the deletion-contraction algorithm and an introduction to chromatic polynomials.