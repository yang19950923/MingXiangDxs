package cn.soso.service;

import cn.soso.entityclass.MobileCard;

/*
 * ͨ������ӿ�
 */
public interface TalkSevice {
	   /*
	    * minCount��ͨ��������
	    */
        public int call(int minCount,MobileCard card)throws Exception;  //ͨ��ʱ��
        
        
}
