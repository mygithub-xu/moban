package com.dhlg.module.familyTree.faUser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.familyTree.faUser.entity.FaUser;
import com.dhlg.module.familyTree.faUser.dao.FaUserMapper;
import com.dhlg.module.familyTree.faUser.service.IFaUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.utils.Dictionaries;
import com.dhlg.utils.Parameter.QueryEntity;
import com.dhlg.utils.Result;
import com.dhlg.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dhlg.utils.GetLoginUser;
import com.dhlg.utils.DateUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu
 * @since 2020-05-05
 */
@Service
public class FaUserServiceImpl extends ServiceImpl<FaUserMapper, FaUser> implements IFaUserService {

    @Autowired
    FaUserMapper doMapper;

    @Override
    public Result saveOrUpdateCommon(FaUser faUser) {
        //判断新增还是修改
        if (!StringUtils.isBlank(faUser.getId())) {
            //修改
            faUser.setUpdateTime(DateUtils.getCurrentDate());
            faUser.setCreateUser(GetLoginUser.getCurrentUserId());
            if(!updateById(faUser)){

                return Result.error(Dictionaries.UPDATE_FAILED);
            }

            return Result.success(Dictionaries.SAVE_SUCCESS);
        }
        faUser.setCreateUser(GetLoginUser.getCurrentUserId());
        faUser.setCreateTime(DateUtils.getCurrentDate());
        faUser.setId(StringUtils.uuid());
        if (!save(faUser)){

            return Result.error(Dictionaries.SAVE_FAILED);
        }

        return Result.success(Dictionaries.SAVE_SUCCESS);
    }

    @Override
    public Result delete(String id) {
        if (!removeById(id)){
            return Result.error(Dictionaries.DELETE_FAILED);
        }
        return  Result.success(Dictionaries.DELETE_SUCCESS);
    }

    @Override
    public Result query(QueryEntity<FaUser> parameter) {
        IPage<FaUser> dataList = doMapper.queryByCondition(parameter.getPage(), parameter.getCondition());
        return new Result(dataList);
    }

    @Override
    public Result query(String id) {
        return Result.success(getById(id));
    }

    @Override
    public Result listTree(String id) {
        // 找到当前用户id
        String userId = "1";
        // 找到该族谱用户
        List<FaUser> users = doMapper.queryByTaleId(id);
        // 形成族谱Tree
        List<FaUser> faUserList = createdTree(users,userId);

        return Result.success(faUserList);
    }

    private List<FaUser> createdTree(List<FaUser> users,String userId) {
        HashMap<String, FaUser> userMap = new HashMap<>();
        for (FaUser fauser:users) {
            userMap.put(fauser.getId(),fauser);
        }
        // 找出我
        FaUser me = userMap.get(userId);
        // 给称谓
        me.setCall("I");
        me.setX(0);
        me.setY(0);
        // 生长
        growUser(userMap,userId);
        users.clear();
        for (String key : userMap.keySet()) {
            users.add(userMap.get(key));
        }
        return users;
    }

    private void growUser(HashMap<String, FaUser> userMap, String userId) {
        // 找出我身边的
        FaUser me = userMap.get(userId);
        // 父母
        FaUser parents = userMap.get(me.getParentId());
        if (!StringUtils.isBlank(parents)&&StringUtils.isBlank(parents.getCall())){
            // 判断是父亲（father）还是母亲(mather)
            String sex = parents.getGender().equals(Dictionaries.MAN)?"f":"m";
            // 称谓
            parents.setCall(me.getCall()+sex);
            // 在x轴坐标
            parents.setX(me.getX());
            // 在y轴坐标
            parents.setY(me.getX()+2);
            // 生长
            growUser(userMap,parents.getId());
        }
        // 配偶
        FaUser spouse = userMap.get(me.getSpouseId());
        if (!StringUtils.isBlank(spouse)&&StringUtils.isBlank(spouse.getCall())){
            // 判断是妻子（wife）还是丈夫（husband）
            String sex = spouse.getGender().equals(Dictionaries.MAN)?"h":"w";
            spouse.setCall(me.getCall()+sex);
            // 在x轴坐标
            spouse.setX(me.getX()+2);
            // 在y轴坐标
            spouse.setY(me.getX());
            // 生长
            growUser(userMap,spouse.getId());
        }
        // 孩子
        List<FaUser> children = new ArrayList<>();
        for (String key : userMap.keySet()) {
            FaUser value = userMap.get(key);
            if (userId.equals(value.getParentId())){
                children.add(value);
            }
        }
        if (!StringUtils.isBlank(children)&&children.size()>0){
            for (FaUser child:children) {
                if (StringUtils.isBlank(child.getCall())){
                    // 判断是儿子（son）还是女儿(daughter)
                    String sex = child.getGender().equals(Dictionaries.MAN)?"s":"d";
                    child.setCall(me.getCall()+sex);
                    // 在x轴坐标
                    spouse.setX(me.getX());
                    // 在y轴坐标
                    spouse.setY(me.getX()-2);
                }
                // 生长
                growUser(userMap,child.getId());
            }
        }
    }

    @Override
    public Result deleteBatch(List<String> ids) {
        if (!removeByIds(ids)){

            return Result.error(Dictionaries.DELETE_FAILED);
        }
        return Result.success(Dictionaries.DELETE_SUCCESS);
    }
}
