package com.dhlg.module.test.lianxi;

public class lanhan {
    // 懒汉
    private  lanhan(){
    }
    private static volatile lanhan lanhan = null;

    public static synchronized lanhan getLanhan(){
        if (lanhan == null){
            lanhan = new lanhan();
        }
        return lanhan;
    }
}
