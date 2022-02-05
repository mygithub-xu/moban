package com.dhlg.module.system.mbSysChat.controller;


import com.dhlg.exception.ParamIsNullException;
import com.dhlg.module.system.mbSysChat.entity.MbSysChat;
import com.dhlg.module.system.mbSysChat.service.IMbSysChatService;
import com.dhlg.utils.Result;
import com.dhlg.utils.common.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xu
 * @since 2020-03-14
 */
@RestController
@RequestMapping("/api/mbSystem/mbSysChat")
@CrossOrigin
public class MbSysChatController {

    @Autowired
    IMbSysChatService doService;

    @ApiOperation("发送")
    @PostMapping("/send")
    public Result sendMessge(@RequestBody MbSysChat chat){
        if(StringUtils.isBlank(chat)){
            throw new ParamIsNullException();
        }
        return doService.send(chat);
    }

    @ApiOperation("群发")
    @PostMapping("/sendAll")
    public Result sendMessgeAll(@RequestBody MbSysChat chat){
        if(StringUtils.isBlank(chat)){
            throw new ParamIsNullException();
        }
        return doService.sendAll(chat);
    }
    @ApiOperation("找到未读数据")
    @PostMapping("/findChatList/{id}")
    public Result findChatList(@PathVariable String id){
        if(StringUtils.isBlank(id)){
            throw new ParamIsNullException();
        }
        return doService.findChatList(id);
    }

    @ApiOperation("通过id改变未读状态")
    @PostMapping("/changeChatStatus")
    public void findChatList(@RequestBody List<String> ids){
        if(StringUtils.isBlank(ids)){
            throw new ParamIsNullException();
        }
         doService.changeChatStatus(ids);
    }
}

