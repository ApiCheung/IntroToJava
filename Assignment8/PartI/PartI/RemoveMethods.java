package Assignment8.PartI.PartI;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.LinkedList;

public class RemoveMethods {

	public static void remAllStack(Stack<Object> stack, Object item) {
		Stack<Object> res = new Stack<>();
		while(!stack.empty()){
			if(!stack.peek().equals(item)){
				res.push(stack.pop());
			}else{
				stack.pop();
			}

		}
		while(!res.empty()){
			stack.push(res.pop());
		}


	}
	
	public static void remAllQueue(Queue<Object> queue, Object item) {
		Queue<Object> res = new LinkedList<>();
		while(!queue.isEmpty()){
			if(!queue.peek().equals(item)){
				res.offer(queue.poll());
			}else{
				queue.poll();
			}
		}
		while(!res.isEmpty()){
			queue.offer(res.poll());
		}

	}
	
	public static void main(String[] args) {
		Stack<Object> stk = new Stack<Object>();
		stk.push(new Integer(24));
		stk.push(new Integer(2));
		stk.push(new Integer(9));
		stk.push(new Integer(2));
		stk.push(new Integer(7));
		stk.push(new Integer(10));
		stk.push(new Integer(16));
		System.out.println("begin: stk is " + stk);
		RemoveMethods.remAllStack(stk, new Integer(2));
		System.out.println("end: stk is " + stk);
		RemoveMethods.remAllStack(stk, new Integer(24));
		System.out.println("end: stk is " + stk);
		
		Queue<Object> q = new LinkedList<Object>(); // you should probably find a concrete class for this
		q.offer(new Integer(24));
		q.offer(new Integer(2));
		q.offer(new Integer(9));
		q.offer(new Integer(2));
		q.offer(new Integer(7));
		q.offer(new Integer(10));
		q.offer(new Integer(16));
		System.out.println("begin: q is " + q);
		RemoveMethods.remAllQueue(q, new Integer(2));
		System.out.println("end: q is " + q);
		RemoveMethods.remAllQueue(q, new Integer(24));
		System.out.println("end: q is " + q);
		
	}
}
