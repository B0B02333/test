package designmode.factory;
/**
 *  ����ģʽ
 *  ������ķ������
 * @author Administrator
 *
 */
public class Test {

	public static void main(String[] arges){
		Shape s = (Shape)ShapeFactory.getClass(ShapeSun.class);
		s.draw();
	}
}
