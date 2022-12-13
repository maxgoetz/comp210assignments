package assn06;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {
    // Fields
    private T _value;
    private AVLTree<T> _left;
    private AVLTree<T> _right;
    private int _height;
    private int _size;
    
    public AVLTree() {
        _value = null;
        _left = null;
        _right = null;
        _height = 0;
        _size = 0;
    }

    public AVLTree(T value) {
        _value = value;
        _left = null;
        _right = null;
        _height = 0;
        _size = 0;
    }

    /**
     *
     * Rotates the tree left and returns
     * AVLTree root for rotated result.
     */
    
     private AVLTree<T> rotateLeft() {
         AVLTree root = new AVLTree();
         root._value = _value;
         root._right = _right._left;
         root._left = _left;
         root._height = Math.max(root._left == null ? 0 : root._left._height, root._right == null ? 0 : root._right._height) + 1;
         _value = _right._value;
         _height = _right._height;
         _right = _right._right;
         _left = root;
         // You should implement left rotation and then use this 
         // method as needed when fixing imbalances.
    	 // TODO
         return this;
     }
    
    /**
     *
     * Rotates the tree right and returns
     * AVLTree root for rotated result.
     */ 
     
     private AVLTree<T> rotateRight() {
         AVLTree root = new AVLTree(); //old root, new right
         root._value = _value;
         root._left = _left._right;
         root._right = _right;
         root._height = Math.max(root._left == null ? 0 : root._left._height, root._right == null ? 0 : root._right._height) + 1;
         _value = _left._value;
         _height = _left._height;
         _left = _left._left;
         _right = root;
         // You should implement right rotation and then use this 
         // method as needed when fixing imbalances.
    	 // TODO
         return this;
     }

    @Override
    public boolean isEmpty() {
        return _value == null;
    }

    @Override
    public int height() {
        return _height;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public SelfBalancingBST<T> insert(T element) {
    	// TODO
        // inserting the element into the AVLTree
        AVLTree temp = this;
        Stack<AVLTree<T>> stack = new Stack<>();
        while (true) {
            if (_value == null) {
                _value = element;
                stack.add(temp);
                defineChildrenEmpty(this);
                break;
            }
            stack.add(temp);
            if (element.compareTo((T) temp.getValue()) > 0) {
                if (temp._right._value == null) {
                    temp._right = new AVLTree<T>(element);
                    stack.add(temp._right);
                    defineChildrenEmpty(temp._right);
                    break;
                } temp = temp._right;
            } else {
                if (temp._left._value == null) {
                    temp._left = new AVLTree<T>(element);
                    stack.add(temp._left);
                    defineChildrenEmpty(temp._left);
                    break;
                }
                temp = temp._left;
            }
        }

        while (!stack.isEmpty()) {
            //updating height and making rotations
            AVLTree curr = stack.pop();
            if (Math.abs(curr.getBalance()) <= 1) {
                curr._height = Math.max(curr._left == null ? 0 : curr._left._height, curr._right == null ? 0 : curr._right._height) + 1;
            } //left rotations
            else if ((curr.getBalance()) > 1) {
                if (curr._right.getBalance() > 0) {
                    curr = curr.rotateLeft();
                } else {
                    curr._right = curr._right.rotateRight();
                    curr = curr.rotateLeft();
                    curr._height = curr._height + 1;
                }
            } //right rotations
            else if ((curr.getBalance()) < -1) {
                if (curr._left.getBalance() < 0) {
                    curr = curr.rotateRight();
                } else {
                    curr._left = curr._left.rotateLeft();
                    curr = curr.rotateRight();
                    curr._height = curr._height + 1;
                }
            }
        } _size++;
    return this;
    }

    public void defineChildrenEmpty(AVLTree<T> temp){
         if (temp._left == null){
             temp._left = new AVLTree<>();
         }
        if (temp._right == null){
            temp._right = new AVLTree<>();
        }

    }

    @Override
    public SelfBalancingBST<T> remove(T element) {
        // TODO
        if (_value == null) {
            return new AVLTree<>();
        }

        AVLTree current = this;
        AVLTree previous = null;
        Stack<AVLTree<T>> stack = new Stack<>();


        while (!current.isEmpty()) {
            stack.add(current);
            if (current._value.compareTo(element) < 0) {
                previous = current;
                current = current._right;
            } else if (current._value.compareTo(element) > 0) {
                previous = current;
                current = current._left;
            } else if (current._value.compareTo(element) == 0) {
                break;
            }
        }
        if (current.isEmpty()) {
            return this;
        }
        if (previous == null &&  _right._value == null && _left._value == null) {
            _value = null;
            _height = 0;
            _size = 0;
            return this;
        }
        if (current._left.isEmpty() && current._right.isEmpty()) { //leaf being deleted, doesn't affect height
            if (previous._right == current) {
                previous._right = new AVLTree();
            } else {
                previous._left = new AVLTree();
            }
            stack.pop(); //don't need the last element of the stack when removing a leaf
            while (!(stack.isEmpty())) {
                AVLTree curr = stack.pop();
                if (Math.abs(curr.getBalance()) <= 1) {
                    curr._height = Math.max(curr._left == null ? 0 : curr._left._height, curr._right == null ? 0 : curr._right._height) + 1;
                } //left rotations
                else if ((curr.getBalance()) > 1) {
                    if (curr._right.getBalance() >= 0) {
                        curr = curr.rotateLeft();
                    } else {
                        curr._right = curr._right.rotateRight();
                        curr = curr.rotateLeft();
                        curr._height = curr._height + 1;
                    }
                } //right rotations
                else if ((curr.getBalance()) < -1) {
                    if (curr._left.getBalance() <= 0) {
                        curr = curr.rotateRight();
                    } else {
                        curr._left = curr._left.rotateLeft();
                        curr = curr.rotateRight();
                        curr._height = curr._height + 1;
                    }
                }
            } _size--;
            return this;
        } else if ((current._left.isEmpty() && !current._right.isEmpty()) || (current._right.isEmpty() && !current._left.isEmpty())) {
            AVLTree next;
            if (current._left.isEmpty()) {
                next = current._right;

            } else {
                next = current._left;
            }
            if (!(previous == null)) {
                if (current == previous._right) {
                    (previous)._right = next;
                } else {
                    (previous)._left = next;
                }
            } else {
                if (current._left.isEmpty()) {
                    _value = _right._value;
                    _right = new AVLTree<>();
                } else {
                    _value = _left._value;
                    _left = new AVLTree<>();
                }
            }

            while (!(stack.isEmpty())) {
                AVLTree curr = stack.pop();
                if (Math.abs(curr.getBalance()) <= 1) {
                    curr._height = Math.max(curr._left == null ? 0 : curr._left._height, curr._right == null ? 0 : curr._right._height) + 1;
                } //left rotations
                else if ((curr.getBalance()) > 1) {
                    if (curr._right.getBalance() > 0) {
                        curr = curr.rotateLeft();
                    } else {
                        curr._right = curr._right.rotateRight();
                        curr = curr.rotateLeft();
                        curr._height = curr._height + 1;
                    }
                } //right rotations
                else if ((curr.getBalance()) < -1) {
                    if (curr._left.getBalance() < 0) {
                        curr = curr.rotateRight();
                    } else {
                        curr._left = curr._left.rotateLeft();
                        curr = curr.rotateRight();
                        curr._height = curr._height + 1;
                    }
                }
            }
        } else {
//            Stack<AVLTree<T>> stack2 = new Stack<>();
            AVLTree rparent = null;
            AVLTree replace = current._right;
            if (replace._left == null){
                rparent = current;
                stack.add(current);
            } else {
                while (!replace._left.isEmpty()) {
                    rparent = replace;
                    stack.add(replace);
                    replace = replace._left;
                }
            }
            if (!(rparent == null)) {
                (rparent)._left = replace._right;
            } else {
                (current)._right = replace._right;
            }
            (current)._value = replace._value;
            while (!(stack.isEmpty())) {
                AVLTree curr = stack.pop();
                if (Math.abs(curr.getBalance()) <= 1) {
                    curr._height = Math.max(curr._left == null ? 0 : curr._left._height, curr._right == null ? 0 : curr._right._height) + 1;
                } //left rotations
                else if ((curr.getBalance()) > 1) {
                    if (curr._right.getBalance() > 0) {
                        curr = curr.rotateLeft();
                    } else {
                        curr._right = curr._right.rotateRight();
                        curr = curr.rotateLeft();
                        curr._height = Math.max(curr._left == null ? 0 : curr._left._height, curr._right == null ? 0 : curr._right._height) + 1;
                    }
                } //right rotations
                else if ((curr.getBalance()) < -1) {
                    if (curr._left.getBalance() < 0) {
                        curr = curr.rotateRight();
                    } else {
                        curr._left = curr._left.rotateLeft();
                        curr = curr.rotateRight();
                        curr._height = Math.max(curr._left == null ? 0 : curr._left._height, curr._right == null ? 0 : curr._right._height) + 1;
                    }
                }
            }
//            while (!(stack.isEmpty())) {
//                AVLTree curr = stack.pop();
//                if (Math.abs(curr.getBalance()) <= 1) {
//                    curr._height = Math.max(curr._left == null ? 0 : curr._left._height, curr._right == null ? 0 : curr._right._height) + 1;
//                } //left rotations
//                else if ((curr.getBalance()) > 1) {
//                    if (curr._right.getBalance() > 0) {
//                        curr = curr.rotateLeft();
//                    } else {
//                        curr._right = curr._right.rotateRight();
//                        curr = curr.rotateLeft();
//                        curr._height = curr._height + 1;
//                    }
//                } //right rotations
//                else if ((curr.getBalance()) < -1) {
//                    if (curr._left.getBalance() < 0) {
//                        curr = curr.rotateRight();
//                    } else {
//                        curr._left = curr._left.rotateLeft();
//                        curr = curr.rotateRight();
//                        curr._height = curr._height + 1;
//                    }
//                }
//            }
        } _size--;
        return this;


//        // element is the root
//        if (_value.compareTo(element) == 0) {
//            if (_left.isEmpty() && _right.isEmpty()) {
//                return new AVLTree<>();
//            } else if (_left.isEmpty() && !_right.isEmpty()) { //one child
//                _value = _right._value;
//                _height = _right._height;
//                _left = _right._left;
//                _right = _right._left;
//            } else if (!_left.isEmpty() && _right.isEmpty()) { //one child
//                _value = _left._value;
//                _height = _left._height;
//                _right = _left._left;
//                _left = _left._left;
//            } else { //two children
//                AVLTree temp = _right;
//                AVLTree parent = this;
//                Stack<AVLTree<T>> stack = new Stack<>();
//                while (!temp._left.isEmpty()) { //finding the new value to replace the removed element from. right then all the way left
//                    stack.add(temp);
//                    parent = temp;
//                    temp = temp._left;
//                }
//                if (temp._right.isEmpty()) { //if this is the value we want to use to replace, replace.
//                    if (parent._value == _value) {
//                        _value = (T) temp._value;
//                        return this;
//                    }
//                    parent._left = new AVLTree<>();
//                    _value = (T) temp._value;
//                } else {
//                    _value = (T) temp._value;
//                    parent._left = temp._right;
//                }
//                return this;
//            }
//        }
        // element is not the root
//		BST current = this;
//		BST parent = null;
//
//		while (!current.isEmpty() && current.getElement() != element) {
//			parent = current;
//			if (current.getElement().compareTo(element) < 0) {
//				current = current.getRight();
//			} else if (current.getElement().compareTo(element) > 0) {
//				current = current.getLeft();
//			}
//		}
//		if (current.isEmpty()) {
//			return this;
//
//
    }

    @Override
    public T findMin() {
    	// TODO
        AVLTree current = this;
        while (!current._left.isEmpty()) {
            current = current._left;
        }
        return (T) current._value;
    }

    @Override
    public T findMax() {
    	// TODO
        AVLTree current = this;
        while (!current._right.isEmpty()) {
            current = current._right;
        }
        return (T) current._value;
    }

    @Override
    public boolean contains(T element) {
    	// TODO
        AVLTree current = this;
        while (!current.isEmpty()) {
            if (current._value.compareTo(element) < 0) {
                current = current._right;
            } else if (current._value.compareTo(element) > 0) {
                current = current._left;
            } else if (current._value.compareTo(element) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T getValue() {
        return _value;
    }

    @Override
    public SelfBalancingBST<T> getLeft() {
        if (isEmpty()) {
            return null;
        }
        return _left;
    }

    @Override
    public SelfBalancingBST<T> getRight() {
        if (isEmpty()) {
            return null;
        }

         return _right;
    }

    public int getBalance () {
         return (_right == null? 0:_right._height) - (_left == null? 0:_left._height);
    }

    public void changeHeight (AVLTree parent, AVLTree child) {
         AVLTree temp = child;
         parent._height = child._height;
         child._height = temp._height;
    }

//    public void changeHeightRR (AVLTree parent, AVLTree child) {
//        AVLTree temp = child;
//        parent._height = child._height;
//        child._height = temp._height;
//    }
//
//    public void changeHeightRL (AVLTree parent, AVLTree child) {
//        AVLTree temp = child;
//        parent._height = child._height;
//        child._height = temp._height;
//    }
//
//    public void changeHeightLR (AVLTree parent, AVLTree child) {
//        AVLTree temp = child;
//        parent._height = child._height;
//        child._height = temp._height;
//    }

    public void printBreadthFirstTraversal() {
        if (_value == null) {
            return;
        }
        SelfBalancingBST temp = null;
        Queue<SelfBalancingBST> BQueue = new LinkedList<>(); //creating a queue to print values
        BQueue.add(this);
        while(!BQueue.isEmpty()){
            temp = BQueue.remove(); //assigning temp to the next element to be printed of the queue
            System.out.print(temp.getValue() + " ");
            if (_left != null) { //adding temps left child to queue if it is not empty because the BST is printed left to right
                BQueue.add(temp.getLeft());
            }
            if (_right != null){ //adding temps right child to queue if it is not empty
                BQueue.add(temp.getRight());
            }
        }

    }


}