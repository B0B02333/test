package designmode.singleton;
/**
 *  ����ģʽ
 * �Ƿ� Lazy ��ʼ������
         �Ƿ���̰߳�ȫ����
         ʵ���Ѷȣ���
         ���������ַ�ʽ���������ʵ�ַ�ʽ������ʵ������������ǲ�֧�ֶ��̡߳���Ϊû�м��� synchronized�������ϸ��������������㵥��ģʽ��
          ���ַ�ʽ lazy loading �����ԣ���Ҫ���̰߳�ȫ���ڶ��̲߳�������������
 *  
 * @author Administrator
 *
 */
public class Singleton {

	private Singleton(){
		
	}
	private static Singleton instance ;
	public static Singleton getInstance(){
		if (instance == null) {  
	        instance = new Singleton();  
	    }  
	    return instance;
	}
}
