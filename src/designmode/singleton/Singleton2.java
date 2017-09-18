package designmode.singleton;
/**
 *  ����ģʽ2 ������
 *  
 *  �Ƿ� Lazy ��ʼ������
             �Ƿ���̰߳�ȫ����
            ʵ���Ѷȣ���
            ���������ַ�ʽ�߱��ܺõ� lazy loading���ܹ��ڶ��߳��кܺõĹ��������ǣ�Ч�ʺܵͣ�99% ����²���Ҫͬ����
            �ŵ㣺��һ�ε��òų�ʼ���������ڴ��˷ѡ�
            ȱ�㣺������� synchronized ���ܱ�֤��������������Ӱ��Ч�ʡ�
            getInstance() �����ܶ�Ӧ�ó����Ǻܹؼ����÷���ʹ�ò�̫Ƶ������
 * @author Administrator
 *
 */
public class Singleton2 {

	private Singleton2(){
		
	}
	private static Singleton2 instance;
	
	public static synchronized Singleton2 getInstance(){
		if(instance==null){
			instance = new Singleton2();
		}
		return instance;
	}
	
}
