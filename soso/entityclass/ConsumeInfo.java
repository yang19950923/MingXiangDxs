package cn.soso.entityclass;
/*
 * 消费记录
 */
public class ConsumeInfo {
       private String idCard;
       private String type;  
       private int consumData;  //消费数据 通话：分钟   发短信：条   上网：MB
       
       
	public ConsumeInfo(String idCard, String type, int consumData) {
		this.idCard = idCard;
		this.type = type;
		this.consumData = consumData;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getConsumData() {
		return consumData;
	}
	public void setConsumData(int consumData) {
		this.consumData = consumData;
	}
       
       
}
