# Remember that breadth-first uses a queue ↴ and depth-first uses a stack ↴ (could be the call stack or an actual stack object).
That's not just a clue about implementation, it also helps with figuring out the differences in behavior.
Those differences come from whether we visit nodes in the order we see them (first in, first out) or
we visit the last-seen node first (last in, first out).

#Depth-first search (DFS) is a method for exploring a tree or graph. In a DFS, you go as deep as possible down one path before backing up and trying a different one.

 Depth-first search is like walking through a corn maze. You explore one path, hit a dead end, and go back and try a different one.

 Depth-first search is often compared with breadth-first search.

 Advantages:

 Depth-first search on a binary tree generally requires less memory than breadth-first.
 Depth-first search can be easily implemented with recursion.
 Disadvantages

 A DFS doesn't necessarily find the shortest path to a node, while breadth-first search does.


#Breadth-first search (BFS) is a method for exploring a tree or graph. In a BFS, you first explore all the nodes one step away, then all the nodes two steps away, etc.

 Breadth-first search is like throwing a stone in the center of a pond. The nodes you explore "ripple out" from the starting point.



