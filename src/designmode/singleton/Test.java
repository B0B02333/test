package designmode.singleton;

public class Test {

	public static void main(String[] args) {
		test2();
	}
	public static void test(){
		Singleton singleton = Singleton.getInstance();
		System.out.println(singleton);
		Singleton singleton2 = Singleton.getInstance();
		System.out.println(singleton2);
	}
	public static void test2() {
		Singleton2 singleton1 = Singleton2.getInstance();
		System.out.println(singleton1);
		Singleton2 singleton2 = Singleton2.getInstance();
		System.out.println(singleton2);
	}

}
