package com.winter.ejemplos;

public class LinkedList {
	// head of list
	static Node head;

	/* Function to create a node */
	Node newNode(int data) {
		Node x = new Node(data);
		return x;
	}

	/* Function to print linked list */
	void printList() {
		Node temp = head;
		while (temp != null) {
			System.out.print(temp.data + " ");
			temp = temp.next;
		}
	}

	/* function to insert a new_node in a list. */
	void sortedInsert(Node new_node) {
		Node current;
		/* Special case for head node */
		if (head == null || head.data >= new_node.data) {
			new_node.next = head;
			head = new_node;
		} else {
			/* Locate the node before point of insertion. */
			current = head;
			while (current.next != null && current.next.data < new_node.data)
				current = current.next;
			new_node.next = current.next;
			current.next = new_node;
		}
	}

	void deleteNode(Node node, Node n) {
		// When node to be deleted is head node
		if (node == n) {
			if (node.next == null) {
				System.out.println("There is only one node. The list " + "can't be made empty ");
				return;
			}
			/* Copy the data of next node to head */
			node.data = node.next.data;
			// store address of next node
			n = node.next;
			// Remove the link of next node
			node.next = node.next.next;
			// free memory
			System.gc();
			return;
		}
		
		// When not first node, follow the normal deletion process
		// find the previous node
		Node prev = node;
		while (prev.next != null && prev.next != n)
			prev = prev.next;
		
		// Check if node really exists in Linked List
		if (prev.next == null) {
			System.out.println("Given node is not present in Linked List");
			return;
		}
		
		// Remove node from Linked List
		prev.next = prev.next.next;
		// Free memory
		System.gc();
		return;
	}

	/* Linked list Node */
	static class Node {
		int data;
		Node next;

		Node(int d) {
			data = d;
			next = null;
		}
	}

	public static void main(String[] args) {
		LinkedList llist = new LinkedList();
		Node new_node;
		new_node = llist.newNode(5);
		llist.sortedInsert(new_node);
		new_node = llist.newNode(10);
		llist.sortedInsert(new_node);
		new_node = llist.newNode(7);
		llist.sortedInsert(new_node);
		new_node = llist.newNode(3);
		llist.sortedInsert(new_node);
		new_node = llist.newNode(-1);
		llist.sortedInsert(new_node);
		new_node = llist.newNode(9);
		llist.sortedInsert(new_node);
		System.out.println("Created Linked List");
		llist.printList();

		LinkedList list = new LinkedList();
		list.head = new Node(12);
		list.head.next = new Node(15);
		list.head.next.next = new Node(10);
		list.head.next.next.next = new Node(11);
		list.head.next.next.next.next = new Node(5);
		list.head.next.next.next.next.next = new Node(6);
		list.head.next.next.next.next.next.next = new Node(2);
		list.head.next.next.next.next.next.next.next = new Node(3);
		System.out.println("\nGiven Linked List");
		list.printList();
		// Let us delete the node with value 10
		System.out.println("\nDeleting node :" + head.next.next.data);
		list.deleteNode(head, head.next.next);
		System.out.println("\nModified Linked list");
		list.printList();
		// Lets delete the first node
		System.out.println("\nDeleting first Node");
		list.deleteNode(head, head);
		System.out.println("\nModified Linked List");
		list.printList();
	}
}
