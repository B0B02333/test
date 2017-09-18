package designmode.abstractfactory;

public interface AbstractFactory {
	Object getColorClass(Class<?extends Color> clazz) ;
	Object getShapeClass(Class<?extends Shape> clazz) ;
}
