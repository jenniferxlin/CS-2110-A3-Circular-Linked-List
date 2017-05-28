package LinkedList;
import static org.junit.Assert.*;

import org.junit.Test;

public class CLLTest {

	@Test
	public void testConstructor() {
		CLL<Integer> b= new CLL<Integer>();
		assertEquals("[]", b.toString());
		assertEquals("[]", b.toStringRev());
		assertEquals(0, b.size());
	} 
	@Test
	public void testAppend() {
		CLL<Integer> c= new CLL<Integer>();
		c.append(5);
		assertEquals("[5]", c.toString());		//append to empty list//
		assertEquals("[5]", c.toStringRev());
		assertEquals(1, c.size());

		c.append(-10);
		assertEquals("[5, -10]", c.toString());	//append to one element list//
		assertEquals("[-10, 5]", c.toStringRev());
		assertEquals(2, c.size());

		c.append(300);
		assertEquals("[5, -10, 300]", c.toString());	//append to multi-element list//
		assertEquals("[300, -10, 5]", c.toStringRev());
		assertEquals(3, c.size());
		
		c.append(300);
		assertEquals("[5, -10, 300, 300]", c.toString());	//append existing value to list//
		assertEquals("[300, 300, -10, 5]", c.toStringRev());
		assertEquals(4, c.size());
	}

	@Test
	public void testPrepend() {
		CLL<Integer> c= new CLL<Integer>();
		c.prepend(0);						//prepend to empty list//
		assertEquals("[0]", c.toString());
		assertEquals("[0]", c.toStringRev());
		assertEquals(1, c.size());

		c.prepend(-6);							//prepend to one element list//
		assertEquals("[-6, 0]", c.toString());
		assertEquals("[0, -6]", c.toStringRev());
		assertEquals(2, c.size());

		c.prepend(800);								//prepend to multi-element list//
		assertEquals("[800, -6, 0]", c.toString());
		assertEquals("[0, -6, 800]", c.toStringRev());
		assertEquals(3, c.size());
		
		c.prepend(800);									//prepend existing value to list//
		assertEquals("[800, 800, -6, 0]", c.toString());
		assertEquals("[0, -6, 800, 800]", c.toStringRev());
		assertEquals(4, c.size());
	}

	@Test
	public void testgetNode() {
		CLL<Integer> c= new CLL<Integer>();
		c.append(1);								//get Node of one element list//
		assertEquals("[1]", c.toString());
		assertEquals("[1]", c.toStringRev());
		assertEquals(1, c.size());
		assertEquals(new Integer(1), c.getNode(0).getValue());
		
		try {c.getNode(-1);  fail();}				// h < 0 //
	    catch (IndexOutOfBoundsException e) {System.out.print(e);}
		
		try {c.getNode(1);  fail();}				//h = size//
	    catch (IndexOutOfBoundsException e) {}
		
		try {c.getNode(2);  fail();}				//h > size//
	    catch (IndexOutOfBoundsException e) {}
		
		c.append(4);
		assertEquals("[1, 4]", c.toString());
		assertEquals("[4, 1]", c.toStringRev());
		assertEquals(2, c.size());
		assertEquals(new Integer(4), c.getNode(1).getValue());	//get Node of end//
		assertEquals(new Integer(1), c.getNode(0).getValue());	//get Node of head//
		
		c.prepend(1);
		assertEquals("[1, 1, 4]", c.toString());				
		assertEquals("[4, 1, 1]", c.toStringRev());
		assertEquals(3, c.size());
		assertEquals(new Integer(1), c.getNode(1).getValue());	//get Node of duplicated values//
		assertEquals(new Integer(1), c.getNode(0).getValue());	
		
		c.append(-9);
		c.append(0);
		assertEquals("[1, 1, 4, -9, 0]", c.toString());				
		assertEquals("[0, -9, 4, 1, 1]", c.toStringRev());
		assertEquals(5, c.size());
		assertEquals(new Integer(1), c.getNode(1).getValue());	//get Node in first half//
		assertEquals(new Integer(-9), c.getNode(3).getValue());	//get Node in last half//
		assertEquals(new Integer(4), c.getNode(2).getValue());	//get Node of middle element//
	}
	
	@Test
	public void testinsertAfter() {
		CLL<Integer> c= new CLL<Integer>();
		c.append(1);
		c.insertAfter(2, c.head);			//insert to one element list//
		assertEquals("[1, 2]", c.toString());	
		assertEquals("[2, 1]", c.toStringRev());
		assertEquals(2, c.size());
		assertEquals(new Integer(2), c.getNode(1).getValue());
		
		c.insertAfter(4, c.head.pred);			//insert after end of multi-element list//
		assertEquals("[1, 2, 4]", c.toString());
		assertEquals("[4, 2, 1]", c.toStringRev());
		assertEquals(3, c.size());
		assertEquals(new Integer(4), c.getNode(2).getValue());
		
		c.insertAfter(4, c.head.pred.pred);				//insert duplicate value in list//
		assertEquals("[1, 2, 4, 4]", c.toString());
		assertEquals("[4, 4, 2, 1]", c.toStringRev());
		assertEquals(4, c.size());
		assertEquals(new Integer(4), c.getNode(2).getValue());
		assertEquals(new Integer(4), c.getNode(3).getValue());
		
		c.insertAfter(5, c.head.succ);				//insert in middle of list//
		assertEquals("[1, 2, 5, 4, 4]", c.toString());
		assertEquals("[4, 4, 5, 2, 1]", c.toStringRev());
		assertEquals(5, c.size());
		assertEquals(new Integer(5), c.getNode(2).getValue());
		
		CLL<String> d= new CLL<String>();	// test all methods except remove (below for type String//
		d.append("friday");
		d.prepend("yay");
		d.insertAfter("yay",d.head.pred);
		assertEquals("[yay, friday, yay]", d.toString());
		assertEquals("[yay, friday, yay]", d.toStringRev());
		assertEquals(3, d.size());
		assertEquals(new String("yay"), d.getNode(2).getValue());
		assertEquals(new String("friday"), d.getNode(1).getValue());
		assertEquals(new String("yay"), d.getNode(0).getValue());
		
		CLL<Boolean> b= new CLL<Boolean>();	// tests all methods except remove (below) for type Boolean// 
		b.append(false);
		b.prepend(true);
		b.insertAfter(true,b.head.pred);
		assertEquals("[true, false, true]", b.toString());
		assertEquals("[true, false, true]", b.toStringRev());
		assertEquals(3, b.size());
		assertEquals(new Boolean("true"), b.getNode(0).getValue());
		assertEquals(new Boolean("false"), b.getNode(1).getValue());
		assertEquals(new Boolean("true"), b.getNode(2).getValue());
	}
	
	@Test
	public void testRemove() {
		CLL<Integer> c= new CLL<Integer>();
		c.append(3);
		assertEquals("[3]", c.toString());
		assertEquals("[3]", c.toStringRev());
		assertEquals(1, c.size());
		c.remove(c.head);				//removes from one element list//
		assertEquals("[]", c.toString());
		assertEquals("[]", c.toStringRev());
		assertEquals(0, c.size());
		
		c.append(3); 
		c.prepend(2);
		assertEquals("[2, 3]", c.toString());
		assertEquals("[3, 2]", c.toStringRev());
		assertEquals(2, c.size());
		c.remove(c.head);					//removes from two element list//
		assertEquals("[3]", c.toString());
		assertEquals("[3]", c.toStringRev());
		assertEquals(1, c.size());
		 
		c.prepend(2);	
		c.insertAfter(0, c.head);		
		assertEquals("[2, 0, 3]", c.toString());
		assertEquals("[3, 0, 2]", c.toStringRev());
		assertEquals(3, c.size());
		c.remove(c.head.succ);					//removes element in middle of multi-element list//
		assertEquals("[2, 3]", c.toString());
		assertEquals("[3, 2]", c.toStringRev());
		assertEquals(2, c.size());
		
		c.remove(c.head.pred);					//removes end for multi-element list//
		assertEquals("[2]", c.toString());
		assertEquals("[2]", c.toStringRev());
		assertEquals(1, c.size());
		
		c.append(6);							
		c.append(9);
		assertEquals("[2, 6, 9]", c.toString());
		assertEquals("[9, 6, 2]", c.toStringRev());
		assertEquals(3, c.size());
		c.remove(c.head);						//removes head in multi-element list//
		assertEquals("[6, 9]", c.toString());
		assertEquals("[9, 6]", c.toStringRev());
		assertEquals(2, c.size());
		
		CLL<String> d= new CLL<String>();			//tests remove on type String//
		d.append("friday");
		d.prepend("yay");
		assertEquals("[yay, friday]", d.toString());
		assertEquals("[friday, yay]", d.toStringRev());
		assertEquals(2, d.size());
		assertEquals(new String("yay"), d.getNode(0).getValue());
		d.remove(d.head);
		assertEquals("[friday]", d.toString());
		assertEquals("[friday]", d.toStringRev());
		assertEquals(1, d.size());
		
		CLL<Boolean> b= new CLL<Boolean>();			//tests remove on type Boolean//
		b.append(false);
		b.prepend(true);
		b.prepend(true);
		assertEquals("[true, true, false]", b.toString());
		assertEquals("[false, true, true]", b.toStringRev());
		assertEquals(3, b.size());
		b.remove(b.head.succ);
		assertEquals("[true, false]", b.toString());
		assertEquals("[false, true]", b.toStringRev());
		assertEquals(2, b.size());
	}

}
