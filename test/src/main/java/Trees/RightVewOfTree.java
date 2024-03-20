package Trees;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * @author harish.kumar-mbp
 * createdOn 19/02/24
 */
public class RightVewOfTree {

    public static void main(String[] args){
        /**
         *              10
         *              / \
         *             6   15
         *            / \  / \
         *           5  7  12  16
         */
        BinaryTree node = new BinaryTree(10);
        node.left = new BinaryTree(6);
        node.right = new BinaryTree(15);

        node.left.right = new BinaryTree(7);
        node.left.left = new BinaryTree(5);

        node.right.right = new BinaryTree(16);
        node.right.left = new BinaryTree(12);

        printRightViewBFS(node);
    }

    public static void printRightView(BinaryTree root){
        // track the level and print the right most at any level.
        int level = 1;
        int[] lastPrintLevel = {0};
        printRightView(root, level, lastPrintLevel);
    }

    public static void printRightView(BinaryTree root, int level, int[] lastLevel){
        if(root == null)
            return;

        if(lastLevel[0] < level){
            System.out.println(" right view - > " + root.value);
            lastLevel[0] = level;
        }

        printRightView(root.right, level+1, lastLevel);
        printRightView(root.left, level+1, lastLevel);

        return;
    }


    public static void printRightViewBFS(BinaryTree root){
        // track the level and print the right most at any level.
        Queue<BinaryTree> queue = new ArrayDeque<>();
        queue.add(root);

        while(!queue.isEmpty()){

            int size = queue.size();
            // read all the elements in the current level.
            for(int i =0;i<size;i++){
                BinaryTree node = queue.poll();
                if(i == 0){
                    System.out.println("right view node is -> " + node.value);
                }
                if(node.right != null)
                    queue.add(node.right);
                if(node.left != null)
                    queue.add(node.left);
            }

        }
    }

    public static void printLeftViewBFS(BinaryTree root){
        // track the level and print the right most at any level.
        Queue<BinaryTree> queue = new ArrayDeque<>();
        queue.add(root);

        while(!queue.isEmpty()){

            int size = queue.size();
            // read all the elements in the current level.
            for(int i =0;i<size;i++){
                BinaryTree node = queue.poll();
                if(i == size-1){
                    System.out.println("right view node is -> " + node.value);
                }
                if(node.right != null)
                    queue.add(node.right);
                if(node.left != null)
                    queue.add(node.left);
            }

        }
    }
}
