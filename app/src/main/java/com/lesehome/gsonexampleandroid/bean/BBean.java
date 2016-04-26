package com.lesehome.gsonexampleandroid.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hcp on 16/4/25.
 */
public class BBean implements Serializable {

    /**
     * appversion : 5.4 beta1
     * list : [{"title":"【演唱会门票二】只抢不卖！还送10000元大礼包！","url":"http://www.lesehome.com/1"},{"title":"【演唱会门票二】只抢不卖！还送10000元大礼包！","url":"http://www.lesehome.com/2"}]
     */
    public String appversion;
    public String appname;
    public Double price;
    /**
     * title : 【演唱会门票二】只抢不卖！还送10000元大礼包！
     * url : http://www.lesehome.com/1
     */

    public List<ABean> list;

}
