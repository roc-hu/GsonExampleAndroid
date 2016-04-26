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
     * typeDate1 : 2016-03-19 09:59:38
     * typeDate2 : Aug 19, 2010 4:13:57 PM
     * typeBoolean1 : true
     * typeBoolean2 : true
     * typeBoolean3 : 1
     * typeBoolean4 : 1
     */

    public String typeString;
    public int typeInt;
    public double typeDouble;
    public float typeFloat;
    public String typeDate1;//"1461579555246"
    public String typeDate2;//"2016-03-19 09:59:38"
    public String typeDate3;//"Sat Mar 19 09:59:38 GMT+08:00 2016"
    public boolean typeBoolean1;
    public boolean typeBoolean2;
    public boolean typeBoolean3;//不可以为 String类型的 "1" 或者 "0"  解析未 false
    public boolean typeBoolean4;//不可以为 int类型的 1 或者 0  解析报错

    public State state;

    public enum State{
        MEN,WOMEN
    }

}
