package basics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

/*
 * The Optional class was introduced in JDK 8 to reduce the number of places 
 * where a NullPointerException could be generated by code 
 * (and it was frequently used to make the Stream API more robust).
 * 
 *  The intent of Java when releasing Optional was to use it as a return type, 
 *  thus indicating that a method could return an empty value. 
 *  As a matter of fact, the practice of using Optional as a method parameter is even discouraged by some code. 
 */
public class OptionalClass {

	@Test
	public void emptyOptional() {
		//A value is present only if we have created Optional with a non-null value.
	    Optional<String> empty = Optional.empty();
	    assertFalse(empty.isPresent());	    	    
	}
	
	@Test
	public void nonNull() {
	    Optional<String> opt = Optional.of("test");
	    assertTrue(opt.isPresent());
	}
	
	@Test(expected = NullPointerException.class)
	public void argumentPassedToOfCannotBeNull() {
	    Optional.of(null);
	}
	
	@Test
	public void useOfNullableIfNullableArgumentIsPossible() {
	    String name = null;
	    Optional<String> opt = Optional.ofNullable(name);
	    assertFalse(opt.isPresent());
	}
	
	@Test
	public void conditionalAction() {
		//Optional makes us deal with nullable values explicitly as a way of enforcing good programming practice.
	    Optional<String> opt = Optional.of("test");
	    opt.ifPresent(name -> System.out.println(name));
	}
	
	@Test
	public void defaultValueWithOrElse() {
	    String nullName = null;
	    String name = Optional.ofNullable(nullName).orElse("test");
	    assertEquals("test", name);
	}
	
	/*
	 * similar to orElse() but takes a supplier functional interface instead of taking a value
	 */
	@Test
	public void defaultValueWithOrElseGet() {
	    String nullName = null;
	    String name = Optional.ofNullable(nullName).orElseGet(() -> "test");
	    assertEquals("test", name);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void exceptionsWithOrElseThrow() {
	    String nullName = null;
	    Optional.ofNullable(nullName).orElseThrow(IllegalArgumentException::new);
	}
	
	@Test
	public void returnValueWithGet() {
	    Optional<String> opt = Optional.of("test");
	    String name = opt.get();
	    assertEquals("test", name);
	}
	
	@Test
	public void conditionalReturnWithfilter() {
	    Optional<String> name = Optional.of("test");
	    boolean nameIsPresent = 
	    		name.filter(n -> n.equals("test"))
	    			.filter(n -> n.length() == 4)
	    			.isPresent();
	    assertTrue(nameIsPresent);	    
	}
	
	@Test
	public void transformWithMap() {
		List<String> companyNames = Arrays.asList("paypal", "oracle", "", "microsoft", "", "apple");
		Optional<List<String>> listOptional = Optional.of(companyNames);
		
		int size = listOptional
				.map(List::size)
				.orElse(0);
		assertEquals(6, size);
	}
}