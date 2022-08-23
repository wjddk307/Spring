package testJava;

public class LgTV {

	// 생성자에 의한 전달방법
	private TV tv;
/*	public LgTV(TV tv) {
		this.tv = tv;
	}*/
	public TV getTv() {
		return tv;
	}
	
	//setter에 의한 주입방법
	public void setTv(TV tv) {
		this.tv = tv;
	}
}
