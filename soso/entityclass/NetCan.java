package cn.soso.entityclass;

import cn.soso.service.NetService;

/*
 * �����ײ�
 */
public class NetCan extends ServiceCan implements NetService{
        private int netData;
        
        public NetCan(){
        	this.netData=3*1024;
        	this.setPrice(68);
        }
		public int getNetData() {
			return netData;
		}

		public void setNetData(int netData) {
			this.netData = netData;
		}

		@Override
		public void showInfo() {
			System.out.println("�����ײͣ���������Ϊ"+this.netData/1024+"GB/��");
		}

	@Override
	public int netPlay(int netData, MobileCard card) throws Exception {
		int temp = netData;
		for (int i = 0; i < netData; i++) {
			if (this.netData -card.getNetData() >= 1) {
				card.setNetData(card.getNetData() + 1);
			} else if (card.getAcountMoney() >=0.1) {
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
		
}
