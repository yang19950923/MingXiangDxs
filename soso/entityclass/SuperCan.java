package cn.soso.entityclass;

import cn.soso.service.NetService;
import cn.soso.service.NoteService;
import cn.soso.service.TalkSevice;

/*
 * �����ײ�
 */
public class SuperCan extends ServiceCan implements NoteService,TalkSevice,NetService{
       private int talkTime;
       private int netData;  //�ײ�����ÿ������MB
       private int noteCount;
    public SuperCan(){
    	this.talkTime=500;
    	this.noteCount=30;
    	this.netData=1*1024;
    	this.setPrice(78);
    }
       
	public int getTalkTime() {
		return talkTime;
	}
	public void setTalkTime(int talkTime) {
		this.talkTime = talkTime;
	}
	public int getNetData() {
		return netData;
	}
	public void setNetData(int netData) {
		this.netData = netData;
	}
	public int getNoteCount() {
		return noteCount;
	}
	public void setNoteCount(int noteCount) {
		this.noteCount = noteCount;
	}
	@Override
	public void showInfo() {
		System.out.println("�����ײͣ�ͨ��ʱ��Ϊ"+this.talkTime+"����/�£���������Ϊ"+this.noteCount+"��/�£���������Ϊ"+this.netData/1024+"GB/��");
	}
	@Override
	public int netPlay(int netData, MobileCard card) throws Exception {
		int temp = netData;
		for (int i = 0; i < netData; i++) {
			/*
			 * ��һ��������ײ�ʣ����������֧��1MB
			 */
			if (this.netData-card.getNetData() >= 1) {
				card.setNetData(card.getNetData() + 1);
			} else if (card.getAcountMoney() >= 0.1) {
				/*
				�ڶ���������ײ����������꣬�˻�������֧��1MB������ʹ���˻����֧��
				*/
				card.setNetData(card.getNetData() + 1);
				card.setAcountMoney(card.getAcountMoney() - 0.1);
				card.setConsumLoan(card.getConsumLoan() + 0.1);
			} else {
				temp = i;
				throw new Exception("������ʹ��" + i + "MB���������㣬���ֵ����ʹ��");
			}
		}
		return temp;
	
	}
	@Override
	public int call(int minCount, MobileCard card) throws Exception {
		int temp=minCount;
		for(int i=0;i<minCount;i++){
			/*
			 ��һ��������ײ�ʣ��ͨ��ʱ������֧��1����ͨ��
			 */
			if(this.talkTime-card.getTalkTime()>=1){
				card.setTalkTime(card.getTalkTime()+1);
			}else if(card.getAcountMoney()>=0.2){
				/*
				�ڶ���������ײ�ͨ��ʱ�������꣬�˻�������֧��1����ͨ����ʹ���˻����֧��
				*/
				card.setTalkTime(card.getTalkTime()+1);
				card.setAcountMoney(card.getAcountMoney()-0.2);
				card.setConsumLoan(card.getConsumLoan()+0.2);
			}else{
				temp=i;
				throw new Exception("������ͨ��"+i+"���ӣ��������㣬���ֵ����ʹ��");
			}
		}
		return temp;
	}
	@Override
	public int send(int noteCount, MobileCard card) throws Exception {
		int temp=noteCount;
		for(int i=0;i<noteCount;i++){
			/*
			 ��һ��������ײ�ʣ����ſ���֧��1������
			 */
			if(this.noteCount*1024-card.getMessage()>=1){
				card.setMessage(card.getMessage()+1);
			}else if(card.getAcountMoney()>=0.1){
				/*
				�ڶ���������ײͶ������������꣬�˻�������֧��1�����ͣ�ʹ���˻����֧��
				*/
				card.setMessage(card.getMessage()+1);
				card.setAcountMoney(card.getAcountMoney()-0.1);
				card.setConsumLoan(card.getConsumLoan()+0.1);
			}else{
				temp=i;
				throw new Exception("�����ѷ���"+i+"�����������㣬���ֵ����ʹ��");
			}
		}
		return temp;
	}

       
       
}
