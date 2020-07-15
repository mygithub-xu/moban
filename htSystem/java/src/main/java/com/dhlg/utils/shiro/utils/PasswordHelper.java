package com.dhlg.utils.shiro.utils;

import com.dhlg.module.system.sysUser.entity.SysUser;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;


public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private String algorithmName="md5";

    private int hashIterations=2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public  void encryptPassword(SysUser user) {

        //设置盐值
        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        //新密码
        String newPassword = new SimpleHash(
                algorithmName,     //自定义的加密算法
                user.getPassword(),//用户输入的密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt = username + salt
                hashIterations     //hash次数
        ).toHex();

        //设置新密码
        user.setPassword(newPassword);

    }



}
