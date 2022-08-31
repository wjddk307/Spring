package co.micol.prj.aop;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component // Ioc container에 등록
@Aspect // Aop가 수행할 클래스 
public class AopAdvice {
	@Pointcut("execution(* co.micol.prj..*Impl.*(..))")
	public void allPoint() {
		
	}
	
	@Before("allPoint()") // Weaving = 포인트컷이 동작할 시점
	public void beforeLog(JoinPoint jp) { // advice = 수행하는 메소드
		Date date = new Date(); // java 8부터는 LocalDate객체를 권장함.
		String runMethod = jp.getSignature().getName();
		System.out.println(runMethod + "시작시간 = " + date);
	}
	
	@After("allPoint()")
	public void endLog(JoinPoint jp) {
		Date date = new Date(); // java 8부터는 LocalDate객체를 권장함.
		String runMethod = jp.getSignature().getName();
		System.out.println(runMethod + "종료시간 = " + date);
	}

}
