package com.dhlg.module.system.sysMenu.service.impl;

import com.dhlg.utils.*;
import com.dhlg.module.system.sysButton.entity.SysButton;
import com.dhlg.module.system.sysButton.service.impl.SysButtonServiceImpl;
import com.dhlg.module.system.sysMenu.entity.SysMenu;
import com.dhlg.module.system.sysMenu.dao.SysMenuMapper;
import com.dhlg.module.system.sysMenu.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.module.system.sysUser.dao.SysUserMapper;
import com.dhlg.module.system.sysUser.entity.SysUser;
import com.dhlg.exception.UncheckedException;
import com.dhlg.utils.common.DateUtils;
import com.dhlg.utils.common.StringUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
    private Logger logger = LoggerFactory.getLogger(SysMenuServiceImpl.class);

    @Autowired(required = false)
    SysMenuMapper menuMapper;

    @Autowired
    SysButtonServiceImpl sysButtonService;

    @Autowired(required = false)
    SysUserMapper sysUserMapper;

    @Autowired
    VerificationProperty verificationProperty;

    /**
     * 根据username获取不同的menu的list集合
     * */
    public List<SysMenu> findMenu(SysUser sysUser) {
        List<SysMenu>sysMenuList=new ArrayList<SysMenu>();
        if ("admin".equals(sysUser.getLoginUser())){
            sysMenuList=menuMapper.findMenu();
        }else {
            String[] titleList= StringUtils.stb(sysUser.getRoleIds());
            sysMenuList=menuMapper.findMenuByroleIds(titleList);
        }
        return sysMenuList;
    }
    /**
     * 查询所有节点数据
     * */
    @Override
    public List<SysMenu> getNodeList(Map<String, Object> params) {
        if (MapUtils.isEmpty(params)) {
            params = new HashMap<>();

            params.put("status", "1"); // 状态(是否启用 1启用 0 禁用)
        }
        List<SysMenu> sysMenuList = null;
        List<SysButton> buttonList;
        try{
            buttonList = sysButtonService.list();

            List<SysMenu> sysMenus = menuMapper.queryAll();

            sysMenuList = initChildrenList(new ArrayList<>(sysMenus), buttonList);
            sysMenuList = InitTree.getRootNodes(sysMenuList); // 构造树形数据结构

        } catch (Exception e) {
            logger.error("查询菜单节点树时异常:", e);
        }
        return sysMenuList;
    }
    /**
     * 构造子表菜单
     * */
    private List<SysMenu> initChildrenList(List<SysMenu> sysMenuList, List<SysButton> buttonList) {
        sysMenuList.forEach(sysMenu -> {
            List<SysButton> menuButtonList = new ArrayList<>();
            buttonList.forEach(sysButton -> {
                if (sysMenu.getId().equals(sysButton.getMenuId())) {
                    menuButtonList.add(sysButton);
                }
            });
            sysMenu.setButtonList(menuButtonList);
        });
        return sysMenuList;
    }




    @Override
    @Transactional
    public Result saveNode(SysMenu sysMenu) {
        Result result = new Result();
        //获取当前登入人员id
//        String userId = GetLoginUser.getCurrentUserId();
        String userId = "2";
        String requestId  = StringUtils.uuid();

        String id = sysMenu.getId();
        if (StringUtils.isEmpty(id)) {
            result.setBody(Dictionaries.SAVE_SUCCESS);
            //校验编码是否重复
            boolean isExist = propertyValueIsExist(sysMenu, requestId);
            if (isExist) {
                throw new UncheckedException(Dictionaries.CODE_REPETITION,Dictionaries.CODE_REPETITION,Dictionaries.CODE_REPETITION);
            }

            id = StringUtils.uuid();
            sysMenu.setId(id);
            sysMenu.setCreateUser(userId);
            sysMenu.setCreateTime(DateUtils.getCurrentDate());
            if(!save(sysMenu))  {
                throw new UncheckedException(Dictionaries.SAVE_FAILED,Dictionaries.SAVE_FAILED,Dictionaries.SAVE_FAILED);
            }
        }else{
            //更新
            SysMenu oldMenu = super.getById(sysMenu.getId());
            String oldCode = oldMenu.getMenuCode();
            String newCode = sysMenu.getMenuCode();
            if(!oldCode.equals(newCode)){
                //修改了编码,校验编码是否重复
                boolean isExist = propertyValueIsExist(sysMenu, requestId);
                if (isExist) {
                    throw new UncheckedException(Dictionaries.CODE_REPETITION,Dictionaries.CODE_REPETITION,Dictionaries.CODE_REPETITION);
                }
            }
            result.setBody(Dictionaries.UPDATE_SUCCESS);
            sysMenu.setUpdateUser(userId);
            sysMenu.setUpdateTime(DateUtils.getCurrentDate());
            if(!updateById(sysMenu)) {
                throw new UncheckedException(Dictionaries.UPDATE_FAILED,Dictionaries.UPDATE_FAILED,Dictionaries.UPDATE_FAILED);
            }
        }

        if (!CollectionUtils.isEmpty(sysMenu.getButtonList())) {
            sysButtonService.saveOrUpdateBatch(sysMenu.getButtonList());
        }
//        verificationProperty.unlock(sysMenu,"menu_code",requestId);
        return result;
    }

    //校验编码是否重复
    private boolean propertyValueIsExist(SysMenu sysMenu, String requestId) {
        return verificationProperty.lockAndVerify(sysMenu,
                "menu_code",
                requestId);
    }

    @Override
    @Transactional
    public boolean deleteNode(String id) {
        boolean result = true;
        Map<String, Object> params = new HashMap<>();
        params.put("menu_id", id);
        try{
            removeById(id);
            sysButtonService.removeByMap(params);
        } catch (Exception e) {
            result = false;
            logger.error("删除菜单节点树时异常:", e);
        }
        return result;
    }
}
