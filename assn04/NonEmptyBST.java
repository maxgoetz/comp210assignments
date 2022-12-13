package assn04;


//import assn03.Node;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
	private T _element;
	private BST<T> _left;
	private BST<T> _right;

	public NonEmptyBST(T element) {

		_left = new EmptyBST<T>();
		_right = new EmptyBST<T>();
		_element = element;
	}


	// TODO: insert
	@Override
	public BST<T> insert(T element) {
		if (element == null) {
			return this;
		}

		BST temp = this;
		while (true) {
			if (element.compareTo((T) temp.getElement()) > 0) { // comparing if temp (pointer) is less than the inputed element
				if (temp.getRight().isEmpty()) {
					((NonEmptyBST<T>) temp)._right = new NonEmptyBST(element); // if the right element is empty, the element is added to the right
					return this;
				}
				temp = temp.getRight(); // changing temp to its right child
			} else {
				if (temp.getLeft().isEmpty()) { // if temp is greater than the inputed element and its left child is empty element becomes the left child
					((NonEmptyBST<T>) temp)._left = new NonEmptyBST(element);
					return this;
				}
				temp = temp.getLeft(); //else, temp becomes temp left
			}
		}
	}


	// TODO: remove
	@Override
	public BST<T> remove(T element) {
		if (_element == null) {
			return new EmptyBST<>();
		}

		BST current = this;
		BST previous = null;

		while (!current.isEmpty() && current.getElement() != element) {
			previous = current;
			if (current.getElement().compareTo(element) < 0) {
				current = current.getRight();
			} else if (current.getElement().compareTo(element) > 0) {
				current = current.getLeft();
			}
		}
		if (current.isEmpty()) {
			return this;
	}

		// element is the root
		if (_element.compareTo(element) == 0) {
			if (_left.isEmpty() && _right.isEmpty()) {
				return new EmptyBST<T>();
			} else if (_left.isEmpty() && !_right.isEmpty()) {
				_element = _right.getElement();
				_left = _right.getLeft();
				_right = _right.getRight();
			} else if (!_left.isEmpty() && _right.isEmpty()) {
				_element = _left.getElement();
				_right = _left.getLeft();
				_left = _left.getRight();
			} else {
				BST temp = _right;
				BST parent = this;
				while (!temp.getLeft().isEmpty()) {
					parent = temp;
					temp = temp.getLeft();
				}
				if (temp.getRight().isEmpty()) {
					if (parent.getElement() == _element) {
						_element = (T) temp.getElement();
						_right = new EmptyBST<>();
						return this;
					}
					((NonEmptyBST<T>) parent)._left = new EmptyBST<>();
					_element = (T) temp.getElement();
				} else {
					_element = (T) temp.getElement();
					((NonEmptyBST<T>) parent)._left = temp.getRight();
				}
				return this;
			}
		}
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
		if (current.getLeft().isEmpty() && current.getRight().isEmpty()) {
			if (previous.getRight() == current) {
				((NonEmptyBST) previous)._right = new EmptyBST<>();
				return this;
			} else {
				((NonEmptyBST) previous)._left = new EmptyBST<>();
				return this;
			}
		} else if ((current.getLeft().isEmpty()) || (current.getRight().isEmpty())) {
			BST next;
			if (current.getLeft().isEmpty()) {
				next = current.getRight();
			} else {
				next = current.getLeft();
			}
			if (current == previous.getRight()) {
				((NonEmptyBST) previous)._right = next;
			} else {
				((NonEmptyBST) previous)._left = next;
			}
		} else {
			BST rparent = null;
			BST replace = current.getRight();
			while (!replace.getLeft().isEmpty()) {
				rparent = replace;
				replace = replace.getLeft();
			}
				if (!(rparent == null)) {
					((NonEmptyBST) rparent)._left = replace.getRight();
				} else {
					((NonEmptyBST) current)._right = replace.getRight();
				}
				((NonEmptyBST) current)._element = replace.getElement();
			}
		return this;
	}


	// TODO: printPreOrderTraversal
	@Override
	public void printPreOrderTraversal() {
		if (_element == null) {
			return;
		}
		NonEmptyBST temp = null;  //creating new pointer reference variable
		Stack<BST<T>> Prest = new Stack<BST<T>>(); // creating a new stack and pushing the bst to the stack
		Prest.push(this);
		while (!Prest.isEmpty()) {
			temp = (NonEmptyBST) Prest.pop(); //popping the top element from the stack and assigning temp to the next value on the stack
			if (!temp._right.isEmpty()) {
				Prest.push(temp._right); //adds the right element of temp to the stack before the left because right elements should always be printed last
			}
			if (!temp._left.isEmpty()){
				Prest.push(temp._left); //adds the left element of temp to the stack
			}
			System.out.print(temp._element + " ");
		}
	}

	// TODO: printPostOrderTraversal
	@Override
	public void printPostOrderTraversal() {
		if (_element == null) {
			return;
		}
		BST temp = null;
		Stack<T> Orderst = new Stack<T>(); //creating stack to keep track of the order that elements should be printed
		Stack<BST<T>> Leftst = new Stack<BST<T>>(); //creating a stack to keep track of elements with a left child
		Leftst.push(this);
		while (!Leftst.isEmpty()) {
			temp = (NonEmptyBST) Leftst.pop(); //assigning temp the left most element so far if there are no more right elements
			while (!temp.isEmpty()) {
				Orderst.push((T)temp.getElement()); //adding temp to ordered stack
				if (!temp.getLeft().isEmpty()){
					Leftst.push(temp.getLeft()); //adding temp left child to left stack if temp has a left child
				}
				temp = temp.getRight(); //assigning temp to the next possible right value
			}
		}

		while(!Orderst.isEmpty()){ //printing the values
			System.out.print(Orderst.pop() + " ");
		}


	}


	// TODO: printBreadthFirstTraversal
	@Override
	public void printBreadthFirstTraversal() {
		if (_element == null) {
			return;
		}
		BST temp = null;
		Queue<BST> BQueue = new LinkedList<>(); //creating a queue to print values
		BQueue.add(this);
		while(!BQueue.isEmpty()){
			temp = BQueue.remove(); //assigning temp to the next element to be printed of the queue
			System.out.print(temp.getElement() + " ");
			if (!temp.getLeft().isEmpty()) { //adding temps left child to queue if it is not empty because the BST is printed left to right
				BQueue.add(temp.getLeft());
			}
			if (!temp.getRight().isEmpty()){ //adding temps right child to queue if it is not empty
				BQueue.add(temp.getRight());
			}
		}

	}

	@Override
	public int getHeight() {
		return Math.max(_left.getHeight(), _right.getHeight())+1;


	}


	@Override
	public BST<T> getLeft() {
		return _left;
	}

	@Override
	public BST<T> getRight() {
		return _right;
	}

	@Override
	public T getElement() {
		return _element;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

}

//            if (element.compareTo((T) temp.getLeft().getValue()) < 0) {
//                temp.rotateRight();
//            } else if (element.compareTo((T) temp.getLeft().getValue()) > 0) {
//                temp._left = temp.rotateLeft();
//                temp.rotateRight();
//            }
//            return rotateRight();
//        }
//        if (balance < -1) {
//            if (element.compareTo((T) temp.getRight().getValue()) > 0) {
//                return temp.rotateLeft();
//            } else if (element.compareTo((T) temp.getRight().getValue()) < 0) {
//                temp._right = temp.rotateRight();
//                return temp.rotateLeft();
//            }
//        }
//        return null;