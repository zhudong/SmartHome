package com.hyht.smarthome.manager;

/**
 * Created by Administrator on 2017-11-27.
 */

public class BusEvent {

    public static final int GO_HOME_PAGE = 1;


    //使用mTpye来区分来自不同接受者和发送者
    private int mType = 0;
    private Object mParam;
    private String mStrParam;
    private String mStrParam02;
    private int mIntParam;
    private long mLongParam;
    private boolean mBooleanParam;
    private boolean mBooleanParam02;
    private int mIntValue;
    private int mIntValue02;

    public BusEvent(int type, long longparam) {
        this.mType = type;
        this.mLongParam = longparam;
    }

    public BusEvent(int type, int intParam) {
        this.mType = type;
        this.mIntParam = intParam;
    }

    public BusEvent(int type, Object param) {
        mType = type;
        mParam = param;
    }

    public BusEvent(int type, String str) {
        mType = type;
        mStrParam = str;
    }


    public BusEvent(int type, Object param, String str) {
        mType = type;
        mParam = param;
        mStrParam = str;
    }

    public BusEvent(int type, boolean booleanParam, Object param) {
        mType = type;
        mParam = param;
        mBooleanParam = booleanParam;
    }

    public BusEvent(int type, boolean booleanParam, Object param, String strValue) {
        mType = type;
        mParam = param;
        mBooleanParam = booleanParam;
        mStrParam = strValue;
    }

    public BusEvent(int type, boolean booleanParam) {
        mType = type;

        mBooleanParam = booleanParam;
    }

    public BusEvent(int type, boolean booleanParam, boolean booleanParam02) {
        mType = type;

        mBooleanParam = booleanParam;
        mBooleanParam02 = booleanParam02;
    }

    public BusEvent(int type, boolean booleanParam, boolean booleanParam02, int intValue) {
        mType = type;

        mBooleanParam = booleanParam;
        mBooleanParam02 = booleanParam02;
        mIntValue = intValue;
    }

    public BusEvent(int type, boolean booleanParam, boolean booleanParam02, String strValue) {
        mType = type;

        mBooleanParam = booleanParam;
        mBooleanParam02 = booleanParam02;
        mStrParam = strValue;
    }

    public BusEvent(int type, boolean booleanParam, int intValue) {
        mType = type;
        mIntValue = intValue;
        mBooleanParam = booleanParam;
    }

    public BusEvent(int type, boolean booleanParam, int intValue, int intValue02) {
        mType = type;
        mIntValue = intValue;
        mBooleanParam = booleanParam;
        mIntValue02 = intValue02;
    }

    public BusEvent(int type, boolean booleanParam, String stringParam, String strParam02) {
        mType = type;
        mStrParam = stringParam;
        mBooleanParam = booleanParam;
        mStrParam02 = strParam02;
    }

    public BusEvent(int type,  String stringParam, String strParam02) {
        mType = type;
        mStrParam = stringParam;
        mStrParam02 = strParam02;
    }

    public BusEvent(int type, boolean booleanParam, String stringParam) {
        mType = type;
        mStrParam = stringParam;
        mBooleanParam = booleanParam;
    }

    public BusEvent(int type, Object param, int intParam) {
        mType = type;
        mParam = param;
        mIntParam = intParam;
    }

    public BusEvent(int type, long longParam, Object param) {
        this.mType = type;
        this.mLongParam = longParam;
        this.mParam = param;
    }

    public BusEvent(int type, int intParam, String strParam) {
        this.mType = type;
        this.mIntValue = intParam;
        this.mStrParam = strParam;
    }

    public BusEvent(int type, boolean booleanParam, Object param, String className, boolean booleanParam02) {
        mType = type;
        mParam = param;
        mStrParam = className;
        mBooleanParam = booleanParam;
        mBooleanParam02 = booleanParam02;
    }

    public int getType() {
        return mType;
    }

    public Object getParam() {
        return mParam;
    }

    public long getmLongParam() {
        return mLongParam;
    }

    public String getStrParam() {
        return mStrParam;
    }

    public String getStrParam02() {
        return mStrParam02;
    }

    public int getIntParam() {
        return mIntParam;
    }

    public boolean getBooleanParam() {
        return mBooleanParam;
    }

    public int getIntValue() {
        return mIntValue;
    }

    public boolean getBooleanParam02() {
        return mBooleanParam02;
    }

    public int getIntValue02() {
        return mIntValue02;
    }
}
