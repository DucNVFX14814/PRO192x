package Lab.Class;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UtilitiesTest {
	@org.junit.Test
	public void everyNthChar() throws Exception {
//    	fail("This test has not been implemented");
		Utilities utils = new Utilities();
		char[] output = utils.everyNthChar(new char[] { 'h', 'e', 'l', 'l', 'o' }, 2);
		assertArrayEquals(new char[] { 'e', 'l' }, output);
		char[] output2 = utils.everyNthChar(new char[] { 'h', 'e', 'l', 'l', 'o' }, 8);
		assertArrayEquals(new char[] { 'h', 'e', 'l', 'l', 'o' }, output2);
	}

	@org.junit.Test
	public void removePairs() throws Exception {
		Utilities util = new Utilities();
		assertEquals("ABCDEF", util.removePairs("AABCDDEFF"));
		assertEquals("ABCABDEF", util.removePairs("ABCCABDEEF"));
	}

	@org.junit.Test
	public void converter() throws Exception {
//		fail("This test has not been implemented");
		Utilities util = new Utilities();
		assertEquals(300, util.converter(10, 5));
	}

	@org.junit.Test(expected = ArithmeticException.class)
	public void converter_arithmeticException() throws Exception {
		Utilities util = new Utilities();
		util.converter(10, 0);
	}

	@org.junit.Test
	public void nullIfOddLength() throws Exception {
//		fail("This test has not been implemented");
		Utilities util = new Utilities();
		assertNull(util.nullIfOddLength("odd"));
		assertNotNull(util.nullIfOddLength("even"));
	}

}