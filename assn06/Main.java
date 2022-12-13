package assn06;

public class Main {
    public static void main(String[] args) {

        // Create a new empty tree.
        SelfBalancingBST<Integer> avl_bst = new AVLTree<Integer>();

        // Insert 50 random integers.
        // Note how we need to capture the result of insert back into
        // the variable avl_bst because the post insertion root that is
        // returned might change because of the insertion

        //int[] alist = new int[50];
//        int[] alist = {3, 6, 15, 16, 19, 79, 85, 87, 94, 100};
//        for (int i=0; i<alist.length; i++) {
//            int a = (int) (Math.random()*100);
//            //alist[i] = a;
//            avl_bst = avl_bst.insert(alist[i]);
//        }
//
//        System.out.println(avl_bst.height());
//
//        for (int i=0; i<alist.length; i++) {
//            avl_bst = avl_bst.remove(alist[i]);
//        }
//
//        System.out.println(avl_bst.isEmpty());

        SelfBalancingBST<Integer> avl_tree = new AVLTree<Integer>() {
        };
        avl_tree = avl_tree.insert(20);
        avl_tree = avl_tree.insert(11);
        avl_tree = avl_tree.insert(50);
        avl_tree = avl_tree.insert(4);
        avl_tree = avl_tree.insert(6);
        avl_tree = avl_tree.insert(15);
        avl_tree = avl_tree.insert(3);
        avl_tree = avl_tree.insert(16);
        avl_tree = avl_tree.insert(17);
        avl_tree = avl_tree.insert(2);
        avl_tree = avl_tree.remove(20);
        avl_tree = avl_tree.remove(3);
        avl_tree = avl_tree.remove(4);
//        avl_tree = avl_tree.remove(20);
//        avl_tree = avl_tree.remove(11);
//        avl_tree = avl_tree.remove(50);
//        System.out.println(avl_tree.isEmpty());


        SelfBalancingBST<Integer> avl_tree2 = new AVLTree<Integer>() {
        };
        avl_tree2 = avl_tree2.insert(47);
        avl_tree2 = avl_tree2.insert(52);
        avl_tree2 = avl_tree2.insert(60);
        avl_tree2 = avl_tree2.insert(3);
        avl_tree2 = avl_tree2.insert(7);
        avl_tree2 = avl_tree2.insert(10);
        avl_tree2 = avl_tree2.insert(58);

        avl_tree2 = avl_tree2.remove(7);
//        avl_tree2 = avl_tree2.remove(3);
//        avl_tree2 = avl_tree2.remove(10);


        SelfBalancingBST<Integer> avl_tree3 = new AVLTree<Integer>() {
        };
        avl_tree3 = avl_tree3.remove(98);

        // Now insert 50 integers in increasing order which would
        // cause a simple BST to become very tall but for our
        // self-balancing tree won't be too bad.

//        for (int i=0; i<50; i++) {
//            avl_bst = avl_bst.insert(i);
//        }
//
//        System.out.println(avl_bst.height());
    }
}