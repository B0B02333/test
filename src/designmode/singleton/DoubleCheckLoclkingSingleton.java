package designmode.singleton;
/**
 *  ˫��У��������ģʽ
 *  JDK �汾��JDK1.5 ��
 *  �Ƿ� Lazy ��ʼ������
 *  �Ƿ���̰߳�ȫ����
 *  ʵ���Ѷȣ��ϸ���
 *  ���������ַ�ʽ����˫�����ƣ���ȫ���ڶ��߳�������ܱ��ָ����ܡ�
 *  getInstance() �����ܶ�Ӧ�ó���ܹؼ���
 * @author Administrator
 *
 */
public class DoubleCheckLoclkingSingleton {
	private DoubleCheckLoclkingSingleton(){
		
	}
	private static DoubleCheckLoclkingSingleton DCLSingleton;
	public static DoubleCheckLoclkingSingleton getInstance(){
		
		if(DCLSingleton == null){
			synchronized(DoubleCheckLoclkingSingleton.class){
				if(DCLSingleton == null){
					DCLSingleton = new DoubleCheckLoclkingSingleton();
				}
			}
		}
		return DCLSingleton;
	}
	
}
