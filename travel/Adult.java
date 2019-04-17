package travel;
/*
 * 成人订单
 */
public class Adult {
	private String date;		//出行日期
	private String phoneNum;	//手机号码
	private String name;		//姓名
	private int age;			//年龄
	private double price;	    //订单价格
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void show(){
		System.out.println("姓名："+this.name+"，年龄："+this.age+",本次出行金额："+this.price);
	}
	
}
