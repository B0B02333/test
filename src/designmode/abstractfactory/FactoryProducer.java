package designmode.abstractfactory;

public class FactoryProducer {

	public static Object getFactory(Class<?extends AbstractFactory> clazz){
		Object o = null;
		try {
			o = Class.forName(clazz.getName()).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return o;
		
	}
}
