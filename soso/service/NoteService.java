package cn.soso.service;

import cn.soso.entityclass.MobileCard;

/*
 * ���ŷ���ӿ�
 */
public interface NoteService {
      /*
       * noteCount��������
       */
	  public int send(int noteCount,MobileCard card)throws Exception;  //���Ͷ�����
}
