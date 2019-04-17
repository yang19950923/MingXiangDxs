package cn.soso.entityclass;

import cn.soso.service.NetService;

/*
 * 网虫套餐
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
			System.out.println("网虫套餐：上网流量为"+this.netData/1024+"GB/月");
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
				throw new Exception("本次已使用" + i + "MB，您的余额不足，请充值后再使用");
			}
		}
		return temp;
	}
		
}
