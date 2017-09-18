package designmode.abstractfactory; 

public class ColorFactory implements AbstractFactory{

	@Override
	public Object getColorClass(Class<? extends Color> clazz) {
		Object o = null;
		try {
			o = Class.forName(clazz.getName()).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return o;
	}

	@Override
	public Object getShapeClass(Class<? extends Shape> clazz) {
		return null;
	}
}
