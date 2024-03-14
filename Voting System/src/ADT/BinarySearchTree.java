package ADT;

import ADT.ArrayQueue;
import Entity.Candidates;
import java.util.Comparator;
import java.util.Iterator;

public class BinarySearchTree<T extends Comparable<T>> implements BinarySearchTreeInterface<T> {

    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public BinarySearchTree(T rootData) {
        root = new Node(rootData);
    }

    public boolean search(T entry) {   //to check wheter the entry is available in binary tree or not

        return getEntry(entry) != null;
    }


    public T getEntry(T entry) {   //return the entry that store in binary search tree
        return findEntry(root, entry);
    }

    private T findEntry(Node rootNode, T entry) {  //to find the entry from binary search tree, since it will store the entry in left and right

        T result = null;

        if (rootNode != null) {
            T rootEntry = rootNode.data;

            if (entry.equals(rootEntry)) { //first entry
                result = rootEntry;
            } else if (entry.compareTo(rootEntry) < 0) { //if the entry is less than rootEntry will find the entry at left
                result = findEntry(rootNode.left, entry); //recursive method to find the entry until successful
            } else {
                result = findEntry(rootNode.right, entry);//if the entry is more than rootEntry will find the entry at right
            }
        }
        return result;
    }

  
    public boolean add(T newEntry) {
        if (root!=null) {   //if root !=null
          
         if (addEntry(root, newEntry)) { //successful add
                return true;

            } else {
                return false;
            }
        
        }else{
              root = new Node(newEntry);  // add the root as the newEntry if the nrott is null
            return true;
           
        }
    }

    private boolean addEntry(Node rootNode, T newEntry) {
        boolean result = true;

        int comparison = newEntry.compareTo(rootNode.data);
        if (comparison == 0) {		// newEntry matches entry in root
            rootNode.data = newEntry;
            return false;
        } else if (comparison < 0) {// newEntry smaller than entry in root
            if (rootNode.left != null) {// if left not null need to gothrought left again
                addEntry(rootNode.left, newEntry);
            } else {
                rootNode.left = new Node(newEntry); //if not null root.left=newEnttry
            }
        } else {														// newEntry > entry in root
            if (rootNode.right != null) {// newEntry largest than entry in root
                addEntry(rootNode.right, newEntry);//// if left not null need to gothrought right again
            } else {
                rootNode.right = new Node(newEntry); //if not null root.right =newEnttry
            }
        }

        return result;
    }

    public T remove(T entry) {  //remove the entry from binary search tree
        ReturnObject oldEntry = new ReturnObject(null);// data field is null

        Node newRoot = removeEntry(root, entry, oldEntry); //delete the entry

        root = newRoot; //replece the existig root with the newRoot that deleted the entry

        return oldEntry.get();//return the deleted entry
    }

    private Node removeEntry(Node rootNode, T entry, ReturnObject oldEntry) {//remove a entry from the tree
        if (rootNode != null) {  //will process this funtion when the root not = null
            T rootData = rootNode.data;  
            int comparison = entry.compareTo(rootData);//compare to know the entry is small than , large than or equals the root data

            if (comparison == 0) {      // the entry equal root data
                oldEntry.set(rootData); //oldEntry == current root data
                rootNode = removeFromRoot(rootNode); //remove the root node
            } else if (comparison < 0) {  // entry < root 
                Node leftChild = rootNode.left;//left child of the current node as a subtree root,
                //then use recursive method to removes an item from that subtree.
                Node subtreeRoot = removeEntry(leftChild, entry, oldEntry);
                rootNode.left = subtreeRoot;
            } else {                      // entry > root 
                Node rightChild = rootNode.right;
                rootNode.right = removeEntry(rightChild, entry, oldEntry);
            }
        }

        return rootNode;
    }

 private Node removeFromRoot(Node rootNode) {
        // Case 1: rootNode has two children
        if (rootNode.left != null && rootNode.right != null) {
            // find node with smallest entry in right subtree
            Node rightSubtreeRoot = rootNode.right;
            Node SmallestNode = findSmallest(rightSubtreeRoot);

            // replace entry in remove root
            rootNode.data = SmallestNode.data;

            // remove node with smallest entry in right subtree
            rootNode.right = removeSmallest(rightSubtreeRoot);
        } 
        // Case 2: rootNode has right child only
        else if (rootNode.right != null) {
            //direcly replace the rootNode by the rootNode.right
            rootNode = rootNode.right;
            //Case 3: rootNode has left child only
        } else {
             //direcly replace the rootNode by the rootNode.left
            rootNode = rootNode.left;
        }

     
        return rootNode;
    }
  //find the smallest entry from the right subtree 
    private Node findSmallest(Node rootNode) {
        if (rootNode.left != null) {
            rootNode = findSmallest(rootNode.left);
        }

        return rootNode;
    }

//remove the smallest entry by replace it with the rootNode
   private Node removeSmallest(Node rootNode) {
        if (rootNode.left != null) {
            Node small = rootNode.left;
            Node root = removeSmallest(small); //find the smallest
            rootNode.left = root; //replace the smallest by remove entry
        } else {
            rootNode = rootNode.right; //direc replace by the right entry
        }

        return rootNode;
    }


    public void clear() {  //make the binary search tree be empty
        root = null;
    }

    public void printTree() {
        printTree(root);
    }

    private void printTree(Node root) { //print all the item in binary search tree
        if (root != null) {
            printTree(root.left);
            System.out.println(root.data);
            printTree(root.right);
        }
    }
    public int getSize(){ // get the size of tree
       return getSize(root);
    }
    public int getSize(Node root) {
    if (root == null) {
        return 0;
    } else {
        int leftSize = getSize(root.left);
        int rightSize = getSize(root.right);
        return leftSize + rightSize + 1;
    }
}

    @Override
    public Iterator<T> getInorderIterator() {
        return new InorderIterator();
    }

    public Iterator<T> getInorderIterator_reverse() {
        return new InorderIterator_reverse();
    }

    private Iterator<Candidates> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // ReturnObject is the type for method removeEntry's 3rd parameter, oldEntry,
    // which is used for returning the removed entry
    private class ReturnObject {

        private T item;

        private ReturnObject(T entry) {
            item = entry;
        }

        public T get() {
            return item;
        }

        public void set(T entry) {
            item = entry;
        }
    }
//tree node

    private class Node {

        private T data;
        private Node left;
        private Node right;

        private Node() {
            this(null);
        }

        private Node(T dataPortion) {
            this(dataPortion, null, null);
        }

        private Node(T data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        private boolean isLeaf() { //the item that do not have left and right child
            return (left == null) && (right == null);
        }

    }

    private class InorderIterator implements Iterator<T> { //use queue to get all the item in the binary search tree(less to more)

        private QueueInterface<T> queue = new ArrayQueue<>();

        public InorderIterator() {
            inorder(root);
        }

        private void inorder(Node treeNode) {
            if (treeNode != null) {
                inorder(treeNode.left); // Traverse right subtree first
                queue.enqueue(treeNode.data);
                inorder(treeNode.right); // Then traverse left subtree
            }
        }

        public boolean hasNext() {
            return !queue.isEmpty();
        }

        public T next() {
            if (!queue.isEmpty()) {
                return queue.dequeue();
            } else {
                return null;
            }
        }

    }

    private class InorderIterator_reverse implements Iterator<T> { //get the item from binary tree (from more to less)

        private QueueInterface<T> queue = new ArrayQueue<>();

        public InorderIterator_reverse() {
            inorder(root);
        }

        private void inorder(Node treeNode) {
            if (treeNode != null) {
                inorder(treeNode.right);
                queue.enqueue(treeNode.data);
                inorder(treeNode.left);
            }

        }

        public boolean hasNext() {
            return !queue.isEmpty();
        }

        public T next() {
            if (!queue.isEmpty()) {
                return queue.dequeue();
            } else {
                return null;
            }
        }

    }

    public Node getRoot() {
        return root;
    }

}
