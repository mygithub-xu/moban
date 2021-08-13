package com.dhlg.module.test.tProduct.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.test.tProduct.entity.TProduct;
import com.dhlg.module.test.tProduct.dao.TProductMapper;
import com.dhlg.module.test.tProduct.service.ITProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.utils.Dictionaries;
import com.dhlg.utils.Parameter.Parameter;
import com.dhlg.utils.Parameter.QueryEntity;
import com.dhlg.utils.Result;
import com.dhlg.utils.common.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class TProductServiceImpl extends ServiceImpl<TProductMapper, TProduct> implements ITProductService {

    @Autowired
    TProductMapper doMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Override
    public Result saveOrUpdateCommon(TProduct tProduct) {
        //判断新增还是修改
        if (!StringUtils.isBlank(tProduct.getId())) {
            //修改
            if(!updateById(tProduct)){

                return Result.error(Dictionaries.UPDATE_FAILED);
            }

            return Result.success(Dictionaries.SAVE_SUCCESS);
        }
        tProduct.setId(StringUtils.uuid());
        if (!save(tProduct)){

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
    public Result query(QueryEntity<TProduct> parameter) {
        IPage<TProduct> dataList = doMapper.queryByCondition(parameter.getPage(), parameter.getCondition());
        return new Result(dataList);
    }

    @Override
    public Result query(String id) {
        return Result.success(getById(id));
    }

    @Override
    public Result deleteBatch(List<String> ids) {
        if (!removeByIds(ids)){

            return Result.error(Dictionaries.DELETE_FAILED);
        }
        return Result.success(Dictionaries.DELETE_SUCCESS);
    }

    @Override
    public void send(int messge){
        //第一个参数：交换机名字  第二个参数：Routing Key的值  第三个参数：传递的消息对象
        rabbitTemplate.convertAndSend("test.direct","test",messge);
    }

    public void robbingProduct(Integer userId) {
    }
}
