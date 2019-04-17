package cn.soso.entityclass;
/*
 * 移动卡实体类
 */
public class MobileCard {
       private String idCard; //移动卡号
       private String username;//用户名
       private String password;//密码 
       private ServiceCan suoCan;//所属套餐
       private double consumLoan;//当月消费金额
       private double acountMoney;//账户余额
       private int talkTime; //当月通话时长
       private int message;//当月发送的短信条数
       private int netData;//当月上网流量(MB)
       
    
       
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
