package cn.soso.service;

import cn.soso.entityclass.MobileCard;

/*
 * 通话服务接口
 */
public interface TalkSevice {
	   /*
	    * minCount：通话分钟数
	    */
        public int call(int minCount,MobileCard card)throws Exception;  //通话时长
        
        
}
