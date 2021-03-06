package basics;

public class SubClass extends SuperClass {
	
	private String subField = "subField";
	private MemberClass memberClass = new MemberClass();

	public SubClass(){
		//test is written before subtest, since super() is called first
		System.out.println("subclass default constructor");
	}
	
	public SubClass(String s) {
		//superclass default constructor is written before subclass one-arg constructor, since super() is called first
		System.out.println("subclass one-arg constructor");
		memberClass.doJob();
	}
	
	private class MemberClass {
		public void doJob(){
			System.out.println("MemberClass.doJob can get access to " + subField);
		}
	}
	
	public static class StaticNestedClass {
		public StaticNestedClass(){
			System.out.println("StaticNestedClass");
			
			/*
			 * Static nested classes can be instantiated before the enclosing outer class and, 
			 * therefore, are denied access to all nonstatic members of the enclosing class.
			 * System.out.println(subField); 
			 */
		}
	}
	
}
