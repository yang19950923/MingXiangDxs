package cn.soso.service;

import cn.soso.entityclass.MobileCard;

/*
 * 上网服务接口
 */
public interface NetService {
     /*
      * netData：上网流量
      */
	public int netPlay(int netData,MobileCard card)throws Exception; //上网用了多少流量
}
