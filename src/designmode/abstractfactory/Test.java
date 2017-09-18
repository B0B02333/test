package designmode.abstractfactory;
/**
 *  抽象工厂模式
 *  利用类的反射机制
 * @author Administrator
 *
 */
public class Test {

	public static void main(String[] args) {
		ColorFactory c= (ColorFactory)FactoryProducer.getFactory(ColorFactory.class);
		ShapeFactory s = (ShapeFactory)FactoryProducer.getFactory(ShapeFactory.class);
		ShapeSun ss = (ShapeSun) s.getShapeClass(ShapeSun.class);
		ss.draw();
	}

}
