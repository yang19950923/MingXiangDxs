package cn.soso.entityclass;
/*
 * �ƶ���ʵ����
 */
public class MobileCard {
       private String idCard; //�ƶ�����
       private String username;//�û���
       private String password;//���� 
       private ServiceCan suoCan;//�����ײ�
       private double consumLoan;//�������ѽ��
       private double acountMoney;//�˻����
       private int talkTime; //����ͨ��ʱ��
       private int message;//���·��͵Ķ�������
       private int netData;//������������(MB)
       
    
       
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ServiceCan getSuoCan() {
		return suoCan;
	}
	public void setSuoCan(ServiceCan suoCan) {
		this.suoCan = suoCan;
	}
	public double getConsumLoan() {
		return consumLoan;
	}
	public void setConsumLoan(double consumLoan) {
		this.consumLoan = consumLoan;
	}
	public double getAcountMoney() {
		return acountMoney;
	}
	public void setAcountMoney(double acountMoney) {
		this.acountMoney = acountMoney;
	}
	public int getTalkTime() {
		return talkTime;
	}
	public void setTalkTime(int talkTime) {
		this.talkTime = talkTime;
	}
	public int getMessage() {
		return message;
	}
	public void setMessage(int message) {
		this.message = message;
	}
	public int getNetData() {
		return netData;
	}
	public void setNetData(int netData) {
		this.netData = netData;
	}
       
       
}
