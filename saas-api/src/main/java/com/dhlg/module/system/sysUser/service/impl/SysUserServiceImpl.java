package com.dhlg.module.system.sysUser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.utils.*;
import com.dhlg.module.system.sysButton.entity.SysButton;
import com.dhlg.module.system.sysButton.service.impl.SysButtonServiceImpl;
import com.dhlg.module.system.sysMenu.entity.SysMenu;
import com.dhlg.module.system.sysMenu.service.impl.SysMenuServiceImpl;;
import com.dhlg.module.system.sysUser.entity.SysUser;
import com.dhlg.module.system.sysUser.dao.SysUserMapper;
import com.dhlg.module.system.sysUser.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.utils.Parameter.Parameter;
import com.dhlg.exception.ParamIsNullException;
import com.dhlg.exception.UncheckedException;
import com.dhlg.redis.RedisUtil;
import com.dhlg.shiro.utils.PasswordHelper;
import com.dhlg.utils.common.DateUtils;
import com.dhlg.utils.common.JwtUtil;
import com.dhlg.utils.common.StringUtils;
import com.dhlg.utils.common.uploadFileUtils;
import com.google.code.kaptcha.Producer;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu
 * @since 2020-01-07
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {


    @Autowired(required = false)
    SysUserMapper sysUserMapper;

    @Autowired(required = false)
    SysMenuServiceImpl sysMenuService ;

    @Autowired(required = false)
    SysButtonServiceImpl buttonService;

    @Autowired
    VerificationProperty verificationProperty;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private PasswordHelper passwordHelper;

    @Value("${common.fileDownSrc}")
    private static String fileDownSrc;

    @Value("${common.password}")
    private String PASSWORD;

    @Autowired
    private Producer producer;

    static final int CODE_TIME = 2*60;
    @Override
    public BufferedImage getCaptcha() {
        String code = producer.createText();
        redisUtil.set(Dictionaries.VERIFICATION_CODE + code, code);
        redisUtil.set(Dictionaries.VERIFICATION_CODE_TIME + code, code);
        // 设置为两分钟
        redisUtil.expire(Dictionaries.VERIFICATION_CODE + code, CODE_TIME);
        redisUtil.expire(Dictionaries.VERIFICATION_CODE_TIME + code, CODE_TIME*2);

        return producer.createImage(code);
    }

    @Override
    public Result checkCode(String code) {
        Object o = redisUtil.get(Dictionaries.VERIFICATION_CODE + code);
        Object o2 = redisUtil.get(Dictionaries.VERIFICATION_CODE_TIME + code);
        if (StringUtils.isBlank(o2)){
            return Result.error("验证码错误");
        }
        if (StringUtils.isBlank(o)){
            return Result.error("验证码过期");
        }
        return Result.success("通过");
    }

    @Override
    public Result login(String body, HttpServletRequest request) {
        // 校验验证码
        Map<String, Object> dataMap = new HashMap<>();
        JSONObject jsonObject = JSONObject.fromObject(body);
        String username = jsonObject.get( "userName").toString();
        String password = jsonObject.get( "passWord").toString();
        String checkCOde = jsonObject.get( "checkCode").toString();
        Result result = checkCode(checkCOde);
        if (result.getCode().equals("500")){
            return result;
        }
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new ParamIsNullException();
        }

        //1. 校验用户是否有效
        SysUser sysUser = findByName(username);
        if (sysUser == null) {
            return Result.error("用户不存在");
        }
        //2.校验用户密码
        if(!sysUser.getPassword().equals(passwordHelper.checkPass(password ,sysUser.getSalt()))){
            return Result.error("用户名或密码错误");
        }

        //3.保存token并返回
        String token = saveToken(sysUser);
        if ("".equals(token)){
            return  new Result("500","","服务器错误");
        }
        dataMap.put("token",  token);

        //4.返回用户数据
        List<SysMenu> sysMenus=sysMenuService.findMenu(sysUser);
        sysMenus= InitTree.getRootNodes(sysMenus);
        sysUser.setMenuData(sysMenus);

        Set<String> buttonUrlList= new HashSet<>();
        List<SysButton> buttonList;
        if ("admin".equals(sysUser.getLoginUser())){
            //这里不允许重复，所以用set代替list
            buttonList= buttonService.findAllButtonUrl();
        }else {
            buttonList= buttonService.findButtonUrl(sysUser.getRoleIds());
        }
        for(SysButton sysMenu:buttonList){
            if (!StringUtils.isBlank(sysMenu.getButtonUrl())){
                buttonUrlList.add(sysMenu.getButtonUrl());
            }
        }
        sysUser.setButtonUrlList(buttonUrlList);
        dataMap.put("user",sysUser);

        return new Result("200",dataMap,"登入成功");
    }

    private String saveToken(SysUser sysUser) {
        // 查看user缓存，单个登录
        tichu(sysUser);
        //保存token
        String token = JwtUtil.sign(sysUser.getLoginUser(), sysUser.getPassword());
        boolean set = redisUtil.set(Dictionaries.PREFIX_USER_TOKEN + token, token);
        if (!set){
            return "";
        }
        //将其缓存时间设置为比jwt大的时间，，这里设置为两倍 单位秒
        redisUtil.expire(Dictionaries.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME * 2/ 1000);
        SysUser vo = new SysUser();
        //保存用户信息
        BeanUtils.copyProperties(sysUser,vo);
        redisUtil.set(Dictionaries.PREFIX_USER_ + token, vo);
        redisUtil.expire(Dictionaries.PREFIX_USER_ + token, JwtUtil.EXPIRE_TIME * 2/ 1000);

        // 保存用户名称
        redisUtil.set(Dictionaries.PREFIX_USER_ID_ + sysUser.getLoginUser(), token);
        redisUtil.expire(Dictionaries.PREFIX_USER_ID_ + sysUser.getLoginUser(), JwtUtil.EXPIRE_TIME * 2/ 1000);
        return token;
    }

    private void tichu(SysUser sysUser) {
        String token = (String)redisUtil.get(Dictionaries.PREFIX_USER_ID_ + sysUser.getLoginUser());
        if (!StringUtils.isBlank(token)){
            redisUtil.del(Dictionaries.PREFIX_USER_TOKEN + token);
            redisUtil.del(Dictionaries.PREFIX_USER_ + token);
            redisUtil.del(Dictionaries.PREFIX_USER_ID_ + sysUser.getLoginUser());
        }

    }

    @Override
    public List<SysUser> getList() {

        return super.list();
    }

    @Override
    public Result saveorupdate(SysUser sysUser) {
        Result result = new Result();
        //判断账户名是否为空
        if (StringUtils.isBlank(sysUser.getLoginUser())){
            throw new UncheckedException(Dictionaries.LOGIN_USER_IS_NULL,Dictionaries.LOGIN_USER_IS_NULL,Dictionaries.LOGIN_USER_IS_NULL);
        }
        //判断更新还是新增
        if (StringUtils.isBlank(sysUser.getId())){
            sysUser.setId(StringUtils.uuid());
            //新增,1.判断账号名是否重名
            boolean isRepeat=verificationProperty.lockAndVerify(sysUser, SysUser.LOGIN_USER,sysUser.getId());
            if (isRepeat){
                throw new UncheckedException(Dictionaries.NAME_REPETITION,Dictionaries.NAME_REPETITION,Dictionaries.NAME_REPETITION);
            }
            sysUser.setCreateTime(DateUtils.getCurrentDate());
            sysUser.setCreateUser(GetLoginUser.getCurrentUserId());
            //初始化密码
            sysUser.setPassword(PASSWORD);
            PasswordHelper passwordHelper=new PasswordHelper();
            passwordHelper.encryptPassword(sysUser);
            result.setCode("200");
            result.setBody(Dictionaries.SAVE_SUCCESS);

            //2.新增操作
            if (!save(sysUser)){
                throw new UncheckedException(Dictionaries.SAVE_FAILED,Dictionaries.SAVE_FAILED,Dictionaries.SAVE_FAILED);
            }
        }else {
            //更新,1.判断账号名是否重名
            boolean isRepeat=verificationProperty.lockAndVerify(sysUser,SysUser.LOGIN_USER,sysUser.getId());
            if (isRepeat){
                throw new UncheckedException(Dictionaries.NAME_REPETITION,Dictionaries.NAME_REPETITION,Dictionaries.NAME_REPETITION);
            }
            Subject currentUser = SecurityUtils.getSubject();

            sysUser.setUpdateTime(DateUtils.getCurrentDate());
            sysUser.setUpdateUser(GetLoginUser.getCurrentUserId());
            //2.更新操作
            if (!updateById(sysUser)){
                throw new UncheckedException(Dictionaries.UPDATE_FAILED,Dictionaries.UPDATE_FAILED,Dictionaries.UPDATE_FAILED);
            }
            result.setCode("200");
            result.setBody(Dictionaries.UPDATE_SUCCESS);
        }
        return result;
    }

    @Override
    public Result queryByCondition(Parameter parameter) {
        //如果前端传递的分页数据为空，则设置为默认
        parameter.setDefault();
        IPage<SysUser> projectTeamIPage = sysUserMapper.queryByCondition(parameter.getPage(), parameter);

        return new Result(projectTeamIPage);
    }

    @Override
    public Result batchDelete(Set<String> ids) {
        if (super.removeByIds(ids)){
            return new Result("200",Dictionaries.DELETE_SUCCESS,Dictionaries.DELETE_SUCCESS);
        }
        return new Result("400",Dictionaries.DELETE_FAILED,Dictionaries.DELETE_FAILED);
    }

    @Override
    public Result deleteById(String id) {
        Result result = new Result();
        result.setBody(Dictionaries.DELETE_SUCCESS);
        if (!removeById(id)){
            result.setBody(Dictionaries.DELETE_FAILED);
        }
        return result;
    }

    @Override
    public Result resetPassword(String id) {
        Result result = new Result();
        result.setBody(Dictionaries.RESET_PASSWORD_SUCCESS);
        SysUser sysUser = getById(id);
        if (null == sysUser) {
            result.setBody(Dictionaries.RESET_PASSWORD_FAILED);
            return result;
        }
        sysUser.setPassword(PASSWORD);
        PasswordHelper passwordHelper = new PasswordHelper();
        passwordHelper.encryptPassword(sysUser);
        if (!updateById(sysUser)) {
            result.setBody(Dictionaries.RESET_PASSWORD_FAILED);
        }
        return result;
    }

    @Override
    public Result queryById(String id) {

        return new Result(super.getById(id));
    }

    @Override
    public Result queryHeadPortraitById(String id) {
            Result result = new Result();
            SysUser sysUser = super.getById(id);
            result.setBody(sysUser);
            return result;
    }

    @Override
    public Result userUpdateUserInfo(SysUser sysUser) {
        if (StringUtils.isNotBlank(sysUser.getHeadPortraitSrc())) {
            //对base64进行转换
            String url = uploadFileUtils.uploadImgByHead(sysUser.getHeadPortraitSrc(),fileDownSrc);
            //通知更新了头像，将原有头像删除，保存现有头像-----交给mq

            if ("-1".equals(url)){
                return new Result("500","",Dictionaries.UPLOAD_ERROR);
            }
            sysUser.setHeadPortrait(url);
        }
        boolean flag = super.updateById(sysUser);
        if (!flag) {
            return new Result("500","",Dictionaries.UPDATE_FAILED);
        }
        return new Result("200",sysUser.getHeadPortrait(),Dictionaries.UPDATE_SUCCESS);
    }

    @Override
    public Result updatePassword(Parameter parameter) {
        Result result = new Result();
        result.setBody(Dictionaries.UPDATE_PASSWORD_SUCCESS);
        //1.得到前端具体参数(id、原密码、新密码)
        Map<String, String> condition = parameter.getCondition();
        String id = condition.get("id");
        String password = condition.get("password");
        String newPassword = condition.get("newPassword");

        //2.比较原密码是否正确
        SysUser sysUser = super.getById(id);
        String pass = passwordHelper.checkPass(sysUser.getPassword(),sysUser.getSalt());
        if (password.equals(pass)) {
            return Result.error("原密码不正确");
        }
        SysUser sysObj = super.getById(id);
        //用户名
        sysUser.setId(id);
        sysUser.setUserName(sysObj.getUserName());
        sysUser.setLoginUser(sysObj.getLoginUser());
        //新密码进行加密
        sysUser.setPassword(newPassword);
        //是否重置
        passwordHelper.encryptPassword(sysUser);
        boolean flag = super.updateById(sysUser);
        if (!flag) {
            result.buildFailedResult(Dictionaries.UPDATE_PASSWORD_FAILED, Dictionaries.UPDATE_PASSWORD_FAILED,
                    Dictionaries.UPDATE_PASSWORD_FAILED);
        }
        return result;
    }

    @Override
    public void logout(HttpServletRequest request) {
        //清除redis缓存
        String token = request.getHeader(Dictionaries.ACCESS_TOKEN);
        if (StringUtils.isBlank(token)){
            return;
        }
        String username = JwtUtil.getUsername(token);
        // 获取用户

        // 更改用户状态

        // 删除用户信息
        redisUtil.del(Dictionaries.PREFIX_USER_TOKEN + token);
        redisUtil.del(Dictionaries.PREFIX_USER_ + token);
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }


    public SysUser findByName(String loginUser) {
        //创建条件构造器
        QueryWrapper<SysUser> queryWrapper_user = new QueryWrapper<>();
        //手机和登入名都可以登入
        queryWrapper_user.eq("login_user", loginUser).or().eq("mobile_phone",loginUser);
        return super.getOne(queryWrapper_user);
    }

}
