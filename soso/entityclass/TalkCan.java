package cn.soso.entityclass;

import cn.soso.service.NoteService;
import cn.soso.service.TalkSevice;

/*
 * �����ײ�
 */
public class TalkCan extends ServiceCan implements NoteService,TalkSevice{
      private int talkTime;  //ͨ��ʱ��
      private int noteCount;  //��������
      
    
    public TalkCan(){
    	this.talkTime=500;
    	this.noteCount=30;
    	this.setPrice(58);
    }   
	public int getTalkTime() {
		return talkTime;
	}
	public void setTalkTime(int talkTime) {
		this.talkTime = talkTime;
	}
	public int getNoteCount() {
		return noteCount;
	}
	public void setNoteCount(int noteCount) {
		this.noteCount = noteCount;
	}
	@Override
	public void showInfo() {
		System.out.println("�����ײͣ�ͨ��ʱ��Ϊ" + this.talkTime + "����/�£���������Ϊ"
				+ this.noteCount + "��/��");
	}
	@Override
	public int call(int minCount, MobileCard card)throws Exception {
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
	public int send(int noteCount,MobileCard card)throws Exception{
		int temp=noteCount;
		for(int i=0;i<noteCount;i++){
			/*
			 ��һ��������ײ�ʣ�������������֧��1������
			 */
			if(this.noteCount-card.getMessage()>=1){
				card.setMessage(card.getMessage()+1);
			}else if(card.getAcountMoney()>=0.1){
				/*
				�ڶ���������ײͶ������������꣬�˻�������֧��1�����ŷ��ͣ�ʹ���˻����֧��
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
