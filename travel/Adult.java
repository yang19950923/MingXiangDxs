package travel;
/*
 * ���˶���
 */
public class Adult {
	private String date;		//��������
	private String phoneNum;	//�ֻ�����
	private String name;		//����
	private int age;			//����
	private double price;	    //�����۸�
	
	
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
		System.out.println("������"+this.name+"�����䣺"+this.age+",���γ��н�"+this.price);
	}
	
}
