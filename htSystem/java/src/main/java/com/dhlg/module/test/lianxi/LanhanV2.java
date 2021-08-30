package com.dhlg.module.test.lianxi;

public class LanhanV2 {
    // 单例双重检查锁
    private LanhanV2(){

    }
    private static volatile LanhanV2 lanhanV2 = null;
    private static LanhanV2 getLanhanV2(){
        if (null == lanhanV2){
            synchronized (LanhanV2.class){
                if (null == lanhanV2){
                    lanhanV2 = new LanhanV2();
                }
            }
        }
        return lanhanV2;
    }
}
