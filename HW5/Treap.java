import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {

    private static class Node<E> {

        public E data;
        public int priority;
        public Node<E> left;
        public Node<E> right;

        /***
         * This method will create a Node with left and right pointers as null
         * @param data the key/data/info to be stored in the node
         * @param priority it shows how significant the node is High priority means high significance
         */
        public Node(E data, int priority) {

            this.data = data;
            left = null;
            right = null;
            this.priority = priority;
        }

        /***
         * It will return the root node after right rotation of a particular node
         * @return the root node after rotation
         */
        public Node<E> rotateRight() {

            Node<E> leftOfRoot;
            Node<E> rightOfLeft;
            if (this.left != null) {
                leftOfRoot = this.left;
            } else {
                leftOfRoot = null;
            }

            if (leftOfRoot.right != null) {
                rightOfLeft = leftOfRoot.right;
            } else {
                rightOfLeft = null;
            }

            leftOfRoot.right = this;
            this.left = rightOfLeft;

            return leftOfRoot;

        }

        /***
         * It will return the root node after left rotation of a particular node
         * @return the root node after rotation
         */
        public Node<E> rotateLeft() {

            Node<E> rightOfRoot;
            Node<E> leftOfRight;

            if (this.right != null) {
                rightOfRoot = this.right;
            } else {
                rightOfRoot = null;
            }

            if (rightOfRoot.left != null) {
                leftOfRight = rightOfRoot.left;
            } else {
                leftOfRight = null;
            }

            rightOfRoot.left = this;
            this.right = leftOfRight;

            return rightOfRoot;

        }
    }

    private Random priorityGenerator;
    private Node<E> root;

    /**
     * This method will create a Treap with root null and will also initialize priorityGenerator
     */
    public Treap() {

        root = null;
        priorityGenerator = new Random();
    }

    /**
     * This method will create a Treap with root null and will also initialize priorityGenerator
     * @param seed Sets the seed of this random number generator using a single long seed
     */
    public Treap(long seed) {

        root = null;
        priorityGenerator = new Random();
        priorityGenerator.setSeed(seed);
    }

    /**
     * Will add a node into the treap if priority not given
     * @param key The data value for node
     * @return It will return true if the node was added successfully
     */
    boolean add(E key) {

        boolean r = add(key, priorityGenerator.nextInt());
        if (r) {
            return true;
        } else
            return false;
    }

    /**
     * This method will add a node into the treap if priority given
     * @param key The data value for node
     * @param priority The priority for node
     * @return It will return true if the node was added successfully
     */
    boolean add(E key, int priority) {

        Node<E> tRoot = root;
        Node<E> node = new Node<E>(key, priority);
        Stack<Node<E>> stack = new Stack<Node<E>>();
        if (tRoot == null) {
            root = node;
            return true;
        }
        while (tRoot != null) {
            if (key.compareTo(tRoot.data) > 0) {
                stack.push(tRoot);
                tRoot = tRoot.right;
            } else if (key.compareTo(tRoot.data) < 0) {
                stack.push(tRoot);
                tRoot = tRoot.left;
            } else {
                return false;
            }
        }


        if (key.compareTo(stack.peek().data) > 0) {
            stack.peek().right = node;
        } else {
            stack.peek().left = node;
        }

        if (reheap(stack, node)) {
            return true;
        }

        return false;
    }

    /**
     * This method is a helper function to add an element
     * @param stack The stack which has the trace from root to the leaf where node
     * @param node The Node which we have added to the leaf which will be used for priority balance
     * @return Will return true if balanced successfully
     */
    private boolean reheap(Stack<Node<E>> stack, Node<E> node) {

        while (!stack.isEmpty() && node.priority > stack.peek().priority) {
            /**
             *  Pop the first element
             */
            Node<E> pNode = stack.pop();
            if (!stack.isEmpty()) {
                Node<E> ppNode = stack.peek();
                if (pNode.right != null && pNode.right.equals(node)) {
                    /**
                     * Right Subtree of previous node
                     */
                    if (ppNode.right != null && ppNode.right.equals(pNode)) {
                        /**
                         * Right Subtree of previous to previous node
                         */
                        ppNode.right = pNode.rotateLeft();

                    } else {
                        /**
                         * Left Subtree of previous to previous node
                         */
                        ppNode.left = pNode.rotateLeft();
                    }
                } else {
                    /**
                     * Left Subtree of previous node
                     */
                    if (ppNode.right != null && ppNode.right.equals(pNode)) {
                        /**
                         * Right Subtree of previous to previous node
                         */
                        ppNode.right = pNode.rotateRight();

                    } else {
                        /**
                         * Left Subtree of previous to previous node
                         */
                        ppNode.left = pNode.rotateRight();
                    }
                }
            } else {
                /**
                 * The element popped is root Node
                 */
                if (pNode.right != null && pNode.right.equals(node)) {
                    /**
                     * Right subtree of root
                     */
                    root = pNode.rotateLeft();

                } else {
                    /**
                     * Left subtree of root
                     */
                    root = pNode.rotateRight();
                }
            }
        }

        return true;
    }

    /**
     * This method deletes the node from treap
     * @param key The data value of node which needs to be deleted
     * @return This method will return a boolean
     */
    boolean delete(E key) {

        Node<E> Dnode = root;
        Node<E> Pnode = null;
        Node<E> Ppnode = null;
        boolean rootD = false;
        /**
         * This will find if the element is present or not
         */
        while (Dnode != null) {
            if (key.compareTo(Dnode.data) > 0) {
                Ppnode = Pnode;
                Pnode = Dnode;
                Dnode = Dnode.right;
            } else if (key.compareTo(Dnode.data) < 0) {
                Ppnode = Pnode;
                Pnode = Dnode;
                Dnode = Dnode.left;
            } else {
                break;
            }
        }

        /**
         * If not found then Dnode will be null
         */
        if (Dnode == null) {
            return false;
        }

        /**
         * If Found and Pnode is null then it means the element to be deleted is Root node
         */
        if(Pnode == null)
        {
            rootD = true;
        }

        /**
         * This function will move the node which needs to be deleted to leaf
         */
        while (Dnode.left != null || Dnode.right != null) {

            if (Dnode.left == null && Dnode.right != null) {
                Ppnode = Pnode;
                Pnode = Dnode.rotateLeft();
            } else if (Dnode.right == null && Dnode.left != null) {
                Ppnode = Pnode;
                Pnode = Dnode.rotateRight();
            } else if (Dnode.right.priority > Dnode.left.priority) {
                Ppnode = Pnode;
                Pnode = Dnode.rotateLeft();
            } else {
                Ppnode = Pnode;
                Pnode = Dnode.rotateRight();
            }

            if(rootD)
            {
                root = Pnode;
                rootD = false;
            }

            if (Ppnode != null) {
                if (Ppnode.right != null && Ppnode.right.equals(Dnode)) {
                    Ppnode.right = Pnode;
                } else {
                    Ppnode.left = Pnode;
                }

            }

        }

        /**
         * This function will check if we have actually bubbled the deletion node to a leaf node
         */
        if (Dnode.left == null && Dnode.right == null) {
            if(Pnode == null)
            {
                root = null;
                return true;
            }
            if (Pnode.right != null) {
                if (Pnode.right.data.compareTo(Dnode.data) == 0) {
                    Pnode.right = null;
                    return true;
                }
            }
            if (Pnode.left != null) {
                if (Pnode.left.data.compareTo(Dnode.data) == 0) {
                    Pnode.left = null;
                    return true;
                }

            }
        }
        return false;

    }

    /**
     * This method will find the element when root and key data value has been given
     * @param root It will hold the pointer to the root of treap
     * @param key The data value which needs to be searched in treap
     * @return It will return true if the node is found
     */
    private boolean find(Node<E> root, E key) {

        Node<E> top = root;
        while (top != null) {
            if (key.compareTo(top.data) > 0) {
                top = top.right;
            } else if (key.compareTo(top.data) < 0) {
                top = top.left;
            } else {
                return true;
            }
        }

        return false;
    }

    /**
     * This method will find the node in treap
     * @param key The data to be searched in treap
     * @return This method will return true if the data is located in treap
     */
    public boolean find(E key) {

        if (find(root, key)) {
            return true;
        }
        return false;
    }

    /**
     * This method will call a preOrder Function recursively to print the treap in PreOrder form
     * @return This will return a result String which has the treap
     */
    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();
        System.out.println("The Treap is as below");
        preOrder(root, 1, result);

        return result.toString();
    }

    /**
     * This function will print the treap in PreOrder form
     * @param node The root Node of treap
     * @param height The current height of the node which needs to be printed
     * @param result The string which will be returned by toString function
     */
    private void preOrder(Node<E> node, int height, StringBuilder result) {

        for (int i = 0; i < height; i++) {
            /**
             * adding 2 spaces according to the height
             */
            result.append("  ");
        }

        if (node == null) {
            /**
             *  If node is null print null also the termination of recursion happens here.
             */

            result.append("null\n");
        } else {
            /**
             * Print the node and do recursion
             */
            result.append("(key= " + node.data.toString() + ", priority= " + node.priority + ")\n");
            preOrder(node.left, height + 1, result);
            preOrder(node.right, height + 1, result);

        }
    }

    /**
     * The main method
     * @param args args parameter
     */
    public static void main(String[] args) {

        //       Treap<Character> tree = new Treap<Character>();
//        tree.add('h', 10);
//        tree.add('k', 8);
//        tree.add('a', 15);
//        tree.add('d', 5);
//        tree.add('z', 25);
//        tree.add('s', 15);
//        if (tree.find('k')) {
//            System.out.println("present");
//        }
//        System.out.println(tree.toString());
//        if (tree.delete('h')) {
//            System.out.println(tree.toString());
//        }
        Treap<Integer> tree = new Treap<Integer>();
        System.out.println(tree.toString());
        tree.add(4, 19);
        tree.add(2, 31);
        tree.add(6, 70);
        tree.add(1, 84);
        tree.add(3, 12);
        tree.add(5, 83);
        tree.add(7, 26);
        tree.add(5,82);


        if (tree.find(3)) {
            System.out.println("present");
        }
        System.out.println(tree.toString());
//        if (tree.delete(15)) {
//            System.out.println(tree.toString());
//        }
   //     System.out.println(tree.toString());
        if (tree.delete(1)) {
            System.out.println(tree.toString());
        }


    }
}
