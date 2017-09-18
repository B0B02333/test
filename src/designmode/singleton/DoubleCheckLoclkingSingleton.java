package designmode.singleton;
/**
 *  双重校验锁单例模式
 *  JDK 版本：JDK1.5 起
 *  是否 Lazy 初始化：是
 *  是否多线程安全：是
 *  实现难度：较复杂
 *  描述：这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
 *  getInstance() 的性能对应用程序很关键。
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
