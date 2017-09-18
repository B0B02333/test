package designmode.abstractfactory;

public class ShapeFactory implements AbstractFactory{

	@Override
	public Object getColorClass(Class<? extends Color> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getShapeClass(Class<? extends Shape> clazz) {
		  Object obj = null;
	        try {
	            obj = Class.forName(clazz.getName()).newInstance();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (InstantiationException e) {
	            e.printStackTrace();
	        } catch (IllegalAccessException e) {
	            e.printStackTrace();
	        }

	        return obj;
	}
}
