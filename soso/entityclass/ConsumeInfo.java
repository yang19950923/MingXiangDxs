package cn.soso.entityclass;
/*
 * ���Ѽ�¼
 */
public class ConsumeInfo {
       private String idCard;
       private String type;  
       private int consumData;  //�������� ͨ��������   �����ţ���   ������MB
       
       
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
