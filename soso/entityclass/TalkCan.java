package cn.soso.entityclass;

import cn.soso.service.NoteService;
import cn.soso.service.TalkSevice;

/*
 * 话痨套餐
 */
public class TalkCan extends ServiceCan implements NoteService,TalkSevice{
      private int talkTime;  //通话时长
      private int noteCount;  //短信条数
      
    
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
		System.out.println("话痨套餐：通话时长为" + this.talkTime + "分钟/月，短信条数为"
				+ this.noteCount + "条/月");
	}
	@Override
	public int call(int minCount, MobileCard card)throws Exception {
		int temp=minCount;
		for(int i=0;i<minCount;i++){
			/*
			 第一种情况：套餐剩余通话时长可以支持1分钟通话
			 */
			if(this.talkTime-card.getTalkTime()>=1){
				card.setTalkTime(card.getTalkTime()+1);
			}else if(card.getAcountMoney()>=0.2){
				/*
				第二种情况：套餐通话时长已用完，账户余额可以支付1分钟通话，使用账户余额支付
				*/
				card.setTalkTime(card.getTalkTime()+1);
				card.setAcountMoney(card.getAcountMoney()-0.2);
				card.setConsumLoan(card.getConsumLoan()+0.2);
			}else{
				temp=i;
				throw new Exception("本次已通话"+i+"分钟，您的余额不足，请充值后再使用");
			}
		}
		return temp;
	}
	@Override
	public int send(int noteCount,MobileCard card)throws Exception{
		int temp=noteCount;
		for(int i=0;i<noteCount;i++){
			/*
			 第一种情况：套餐剩余短信条数可以支持1条发送
			 */
			if(this.noteCount-card.getMessage()>=1){
				card.setMessage(card.getMessage()+1);
			}else if(card.getAcountMoney()>=0.1){
				/*
				第二种情况：套餐短信条数已用完，账户余额可以支付1条短信发送，使用账户余额支付
				*/
				card.setMessage(card.getMessage()+1);
				card.setAcountMoney(card.getAcountMoney()-0.1);
				card.setConsumLoan(card.getConsumLoan()+0.1);
			}else{
				temp=i;
				throw new Exception("本次已发送"+i+"条，您的余额不足，请充值后再使用");
			}
		}
		return temp;
	}
	
      
      
}
