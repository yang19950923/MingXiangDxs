package cn.soso.service;

import cn.soso.entityclass.MobileCard;

/*
 * 短信服务接口
 */
public interface NoteService {
      /*
       * noteCount：短信数
       */
	  public int send(int noteCount,MobileCard card)throws Exception;  //发送短信数
}
