package com.dhlg.module.familyTree.faUser.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.familyTree.faUser.entity.FaUser;
import com.dhlg.module.familyTree.faUser.dao.FaUserMapper;
import com.dhlg.module.familyTree.faUser.entity.HomeLink;
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

    final int XN = 8;
    final int YN = 8;
    List<HomeLink> homeLinks;
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
        String userId = "2";
        // 找到该族谱用户
        List<FaUser> users = doMapper.queryByTaleId(id);
        //清空
        homeLinks = new ArrayList<>();
        // 形成族谱数据和两点结合数组
        createdTree(users,userId);
        HashMap<String, Object> data = new HashMap<>();

        data.put("homeList",users);
        data.put("homeLinks",homeLinks);
        return Result.success(data);
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
        me.setY(0);
        me.setY(0);
        HashMap<String, ArrayList<FaUser>> placeMap = new HashMap<>();
        ArrayList<FaUser> placeList = new ArrayList<>();
        placeList.add(me);
        placeMap.put(0+";"+Dictionaries.GROWLEFT, placeList);
        // 生长
        growUser(userMap,userId,placeMap,Dictionaries.GROWLEFT);
        insertXY(userMap,placeMap);
        users.clear();
        for (String key : userMap.keySet()) {
            users.add(userMap.get(key));
        }

        return users;
    }

    private void insertXY(HashMap<String, FaUser> users, HashMap<String, ArrayList<FaUser>> placeMap) {
        for (String key : placeMap.keySet()) {
            List<FaUser> userList = placeMap.get(key);
            String[] split = key.split(";");
            int goX = 0;
            for (FaUser fa:userList) {
                float k = getK(fa.getY());
                if (Dictionaries.GROWLEFT.equals(split[1])){
                    if (fa.getX()!=0){
                        goX = goX - XN;
                        users.get(fa.getId()).setX(goX*k);
                    }else {
                        users.get(fa.getId()).setX(0);
                    }
                }else {
                    if (fa.getX()!=0){
                        goX = goX + XN;
                        users.get(fa.getId()).setX(goX*k);
                    }
                }
                users.get(fa.getId()).setY(-fa.getY());
            }

        }
    }

    private float getK(float y) {
        float k = y/YN;
        if (k == 0){
            return 1;
        }
        if (k < 0){
            return  1/((-k)*2);
        }
        return k+1;
    }

    /**
     *
     * @param userMap 装填用户实体
     * @param userId 用户id
     * @param placeMap 每一行所含有的坐标
     * @param growDire 生长方向
     * @date 2021/3/22 16:40
     * @author xu
     * @return void
     */
    private void growUser(HashMap<String, FaUser> userMap, String userId, HashMap<String, ArrayList<FaUser>> placeMap,String growDire) {
        // 找出我身边的
        FaUser me = userMap.get(userId);
        // 构造连接数据
        creatLinkList(me);
        // 父母
        FaUser parents = userMap.get(me.getParentId());
        if (!StringUtils.isBlank(parents)&&StringUtils.isBlank(parents.getCall())){
            // 判断是父亲（father）还是母亲(mather)
            String sex = parents.getGender().equals(Dictionaries.MAN)?"f":"m";
            // 称谓
            parents.setCall(me.getCall()+sex);
            // 在y轴坐标
            parents.setY(me.getY()+YN);
            // 在x轴坐标
            parents.setX(me.getX());
            // 添加每一层的实体
            addent(placeMap,parents.getY() + ";" + growDire,parents);
            // 生长
            growUser(userMap,parents.getId(),placeMap,growDire);
        }
        // 配偶
        FaUser spouse = userMap.get(me.getSpouseId());
        if (!StringUtils.isBlank(spouse)&&StringUtils.isBlank(spouse.getCall())){
            // 判断是妻子（wife）还是丈夫（husband）
            boolean isMan = spouse.getGender().equals(Dictionaries.MAN);
            String sex = isMan?"h":"w";
            spouse.setCall(me.getCall()+sex);
            // 在y轴坐标
            spouse.setY(me.getY());
            //修改生长方向,主线的夫妻均在右
            if (me.getX() == 0||Dictionaries.GROWRIGHT.equals(growDire)){
                spouse.setX(me.getX()+XN);
                // 添加每一层的实体
                addent(placeMap,spouse.getY() + ";" + Dictionaries.GROWRIGHT,spouse);
                // 生长
                growUser(userMap,spouse.getId(),placeMap,Dictionaries.GROWRIGHT);
            }else {
                spouse.setX(me.getX()-XN);
                // 添加每一层的实体
                addent(placeMap,spouse.getY() + ";" + Dictionaries.GROWLEFT,spouse);
                growUser(userMap,spouse.getId(),placeMap,Dictionaries.GROWLEFT);
            }
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
            boolean flag = true;
            int xnle = Dictionaries.GROWLEFT.equals(growDire)?-XN:XN;
            for (FaUser child:children) {
                if (StringUtils.isBlank(child.getCall())){
                    // 判断是儿子（son）还是女儿(daughter)
                    String sex = child.getGender().equals(Dictionaries.MAN)?"s":"d";
                    child.setCall(me.getCall()+sex);
                    // 在y轴坐标
                    child.setY(me.getY()-YN);
                    if (flag&&child.getY()<0){
                        child.setX(me.getX());
                        flag = false;
                    }else {
                        child.setX(me.getX()+xnle);
                    }
                    // 添加每一层的实体
                    addent(placeMap,child.getY() + ";" + growDire,child);
                    // 生长
                    growUser(userMap,child.getId(),placeMap,growDire);
                }

            }
        }
    }

    private void creatLinkList(FaUser me) {
        // 添加父（母）子
        if (!StringUtils.isBlank(me.getParentId())){
            addLink(me.getId(),me.getParentId());
        }
        // 添加夫妻
        if (!StringUtils.isBlank(me.getSpouseId())){
            addLink(me.getId(),me.getSpouseId());
        }
    }

    private void addLink(String sourceId, String targetId) {
        HomeLink homeLink = new HomeLink();
        homeLink.setSource(sourceId);
        homeLink.setTarget(targetId);
        homeLinks.add(homeLink);
    }

    private void addent(HashMap<String, ArrayList<FaUser>> placeMap, String s,FaUser u) {
        ArrayList<FaUser> faUsers = placeMap.get(s);
        if (StringUtils.isBlank(faUsers)){
            faUsers = new ArrayList<FaUser>();
        }
        faUsers.add(u);
        placeMap.put(s,faUsers);
    }

    @Override
    public Result deleteBatch(List<String> ids) {
        if (!removeByIds(ids)){

            return Result.error(Dictionaries.DELETE_FAILED);
        }
        return Result.success(Dictionaries.DELETE_SUCCESS);
    }
}
