package designmode.abstractfactory;
/**
 *  ���󹤳�ģʽ
 *  ������ķ������
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
