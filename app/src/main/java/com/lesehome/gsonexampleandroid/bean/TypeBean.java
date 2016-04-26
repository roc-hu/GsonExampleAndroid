package com.lesehome.gsonexampleandroid.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hcp on 16/4/25.
 */
public class TypeBean implements Serializable {

    /**
     * typeString : 1.0.1
     * typeInt : -92
     * typeDouble : 99.87
     * typeFloat : 76.99
     * typeDate1 : 1461579555246
     * typeBoolean1 : true
     * typeBoolean2 : true
     * typeBoolean3 : 1
     * typeBoolean4 : 1
     */

    public String typeString;
    public int typeInt;
    public double typeDouble;
    public float typeFloat;
    public Date typeDate1;//"1461579555246"
    public boolean typeBoolean1;
    public boolean typeBoolean2;
    public boolean typeBoolean3;//不可以为 String类型的 "1" 或者 "0"  解析未 false
    public boolean typeBoolean4;//不可以为 int类型的 1 或者 0  解析报错

    public int state;
}
