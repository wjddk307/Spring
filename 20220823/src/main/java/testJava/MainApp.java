package testJava;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		// spring컨테이너 호출 & 초기화
		AbstractApplicationContext context = 
				new GenericXmlApplicationContext("applicationContext.xml");
		
		// 객체요청
	    LgTV tv = (LgTV) context.getBean("lgTV");
	    tv.getTv().powerOff();
	    
	    
	}

}
