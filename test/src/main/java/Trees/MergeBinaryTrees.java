package Trees;

/**
 * @author harish.kumar-mbp
 * createdOn 10/03/24
 */
public class MergeBinaryTrees {

    public BinaryTree mergeBinaryTrees(BinaryTree tree1, BinaryTree tree2) {
        if(tree1 == null && tree2 == null){
            return null;
        }
        if(tree1 == null && tree2 !=null){
            tree1 =   tree2;
        }
        else if(tree1 != null && tree2 ==null){
            tree1 =   tree1;

        }
        else{
            tree1.value =   tree1.value + tree2.value;
            tree1.left = mergeBinaryTrees(tree1.left, tree2.left);
            tree1.right = mergeBinaryTrees(tree1.right, tree2.right);

        }

        // Write your code here.
        return tree1;
    }
}
