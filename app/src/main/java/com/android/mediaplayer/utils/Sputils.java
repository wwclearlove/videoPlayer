package com.android.mediaplayer.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.mediaplayer.App;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Sputils {

    /**
     * SP存储常量
     */
    public final static String HEARTBEAT =  "heartbeat";//心跳操作
    public final static String SERVICENAME =  "serviceName";//心跳操作
    public final static String SOUND =  "sound";       //声音是否开关
    public final static String PASSWORD =  "password"; //密码
    public final static String BPMODE =  "mode";       //班牌模式
    public final static String NOTICE =  "notice";     //通知字符串
    public final static String STATE =  "state";       //状态
    public final static String HEARTJSON =  "heartbeatjson"; //get请求心跳接口JSON数据
    public final static String NOTICEVALUE = "noticevalue";  //通知是否开关
    public final static String ADDERIP = "ip";               //ip地址
    public final static String ADDWEBIP = "web";               //ip地址
    public final static String ADDWECOME = "wecome";               //欢迎模式地址
    public final static String ADDBPMODE = "bpmode";               //班牌模式地址
    public final static String ADDBIGMODE = "bigmode";             //大数据模式地址
    public final static String ISHS = "ishs";             //is横屏
//    public final static String ADDERPORT = "port";           //端口号


    public static SharedPreferences mSharedPreferences;


    private static SharedPreferences getSharedPreferences(){
        if(mSharedPreferences==null){
            mSharedPreferences= PreferenceManager.getDefaultSharedPreferences(App.INSTANCE);
        }
        return mSharedPreferences;
    }
    public synchronized static void putString(String key, String value){
        getSharedPreferences().edit().putString(key,value).apply();
    }
    public synchronized static void putInt(String key, int value){
        getSharedPreferences().edit().putInt(key,value).apply();
    }
    public static void putBoolean(String key, Boolean value){
        getSharedPreferences().edit().putBoolean(key,value).apply();
    }
    public static String getString(String key){
      return   getSharedPreferences().getString(key,"");
    }
    public synchronized static int getInt(String key){
        return   getSharedPreferences().getInt(key,0);
    }
    public static Boolean getBolean(String key, Boolean defValue){
        return   getSharedPreferences().getBoolean(key,defValue);
    }
    public static Boolean getBolean(String key){
        return   getSharedPreferences().getBoolean(key,true);
    }
    public static void removeKey(String key){
        getSharedPreferences().edit().remove(key).apply();
    }

    public static <T> void setDataList(String tag, List<T> datalist) {

        if (null == datalist || datalist.size() <= 0)
            return;
        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);

        getSharedPreferences().edit().putString(tag, strJson).apply();
    }

    /**
     * 获取List
     * @param tag
     * @return
     */
    public static  <T> List<T> getDataList(String tag, Class<T[]> clazz) {
        List<T> datalist=new ArrayList<>();
        String strJson = getSharedPreferences().getString(tag, null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        T[] arr = gson.fromJson(strJson, clazz);
        return Arrays.asList(arr);

    }

}
