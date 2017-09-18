package designmode.factory;
/**
 *  工厂模式
 *  利用类的反射机制
 * @author Administrator
 *
 */
public class Test {

	public static void main(String[] arges){
		Shape s = (Shape)ShapeFactory.getClass(ShapeSun.class);
		s.draw();
	}
}
