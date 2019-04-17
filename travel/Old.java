package travel;
/*
 * 老人订单
 */
public class Old extends Adult {
	
	
	
	
	public void show() {
		System.out.println("姓名：" + this.getName() + "，年龄：" + this.getAge()
				+ ",65岁以上老人半价，本次出行金额：" + this.getPrice());
	}
}
