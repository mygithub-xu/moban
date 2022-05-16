package com.dhlg.module.test.tProduct.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dhlg.module.test.tProduct.entity.TProduct;
import com.dhlg.module.test.tProduct.dao.TProductMapper;
import com.dhlg.module.test.tProduct.service.ITProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlg.module.test.tProductRecord.entity.TProductRecord;
import com.dhlg.module.test.tProductRecord.service.impl.TProductRecordServiceImpl;
import com.dhlg.redis.RedisUtil;
import com.dhlg.utils.Dictionaries;
import com.dhlg.utils.Parameter.Parameter;
import com.dhlg.utils.Parameter.QueryEntity;
import com.dhlg.utils.Result;
import com.dhlg.utils.common.StringUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu
 * @since 2020-05-05
 */
@Slf4j
@Service
public class TProductServiceImpl extends ServiceImpl<TProductMapper, TProduct> implements ITProductService {

    @Autowired
    TProductMapper doMapper;

    @Autowired
    TProductRecordServiceImpl recordService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    RedisUtil redisUtil;

    static BlockingQueue<Runnable> bq = new ArrayBlockingQueue<Runnable>(10);
    static ThreadPoolExecutor poolTaskExecutor = new ThreadPoolExecutor(2,55,50, TimeUnit.MILLISECONDS,bq);

//    @Autowired
//    ThreadPoolTaskExecutor poolTaskExecutor;

//    @PostConstruct
//    private void init(){
//        /**
//         * 消息发送到交换器Exchange后触发回调。
//         * 使用该功能需要开启确认，spring-boot中配置如下：
//         * spring.rabbitmq.publisher-confirms = true
//         */
//        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
//            @Override
//            public void confirm(CorrelationData correlationData, boolean b, String s) {
//                if (b) {
//                    System.out.println("消息已确认 cause:{"+s+"} - {"+correlationData+"}");
//                } else {
//                    System.out.println("消息未确认 cause:{"+s+"} - {"+correlationData+"}");
//                }
//            }
//        });
//        /**
//         * 通过实现ReturnCallback接口，
//         * 如果消息从交换器发送到对应队列失败时触发
//         * 比如根据发送消息时指定的routingKey找不到队列时会触发
//         * 使用该功能需要开启确认，spring-boot中配置如下：
//         * spring.rabbitmq.publisher-returns = true
//         */
//        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
//            @Override
//            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//                log.error("消息被退回:{}", message);
//                log.error("消息使用的交换机:{}", exchange);
//                log.error("消息使用的路由键:{}", routingKey);
//                log.error("描述:{}", replyText);
//            }
//        });
//    }

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
    private int id = 0;
    @Override
    public synchronized void send(){
        id++;
        //第一个参数：交换机名字  第二个参数：Routing Key的值  第三个参数：传递的消息对象
        rabbitTemplate.convertAndSend("test.direct","test",id);
    }

    @Override
    public Result sendMessage(String message) {
//        TProduct tProduct = new TProduct();
//        tProduct.setProductNo(message);
        rabbitTemplate.convertAndSend("test.direct","test",message);
        return Result.success(message);
    }

    @Override
    public Result testxc() {
        long start = System.currentTimeMillis();
        TProduct t = new TProduct();
        // 查找表一
        TProduct product = getById(1);
        t.setProductNo(product.getProductNo());
        // 查找表二
        TProduct product2 = getById(2);
        t.setTotal(product2.getTotal());
        // 时间
        long end = System.currentTimeMillis();
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        map.put("data",t);
        map.put("time",end-start);
        return Result.success(map);
    }



    @Override
    public Result testxc2() {
        long start = System.currentTimeMillis();

        TProduct t = new TProduct();
        // 查找表一
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            TProduct product = getById(1);
            t.setProductNo(product.getProductNo());
        }, poolTaskExecutor);
        // 查找表二
        CompletableFuture<Void> voidCompletableFuture2 = CompletableFuture.runAsync(() -> {
            TProduct product2 = getById(2);
            t.setTotal(product2.getTotal());
        }, poolTaskExecutor);
        try {
            CompletableFuture.allOf(voidCompletableFuture,voidCompletableFuture2).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // 时间
        long end = System.currentTimeMillis();
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        map.put("data",t);
        map.put("time",end-start);
        return Result.success(map);
    }

    public synchronized void  robbingProduct(Integer userId) {
        TProduct product = getById(1);
        if (product != null && product.getTotal() > 0) {
            //更新库存表，库存量减少1。返回1说明更新成功。返回0说明库存已经为0
            product.setTotal(product.getTotal() - 1);
            if(product.getTotal()>=0){
                updateById(product);
                //插入记录
                recordService.insertProductRecord(userId);
                //发送短信
                log.info("用户{}抢单成功", userId);
            }else {
                log.error("用户{}抢单失败1", userId);
            }
        } else {
            log.error("用户{}抢单失败2", userId);
        }
    }

    //string
    void redisStringset(){
        redisUtil.set("string","test string");

    }

    void redisStringget(){
        Object string = redisUtil.get("string");
        System.out.println(string);
    }

    //hash
    void redisHashset(){
        List<TProduct> list = list();
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("hashhash",list);
        redisUtil.hmset("hash",objectObjectHashMap);
    }

    void redishashget(){
        Map<Object, Object> hash = redisUtil.hmget("hash");
        TProduct hashhash = (TProduct) hash.get("hashhash");
        System.out.println(hash);
    }

    //list
    void redisListset(){
        List<TProduct> list = list();
        redisUtil.lSet("list",list);
    }

    void redisListget(){
        List<Object> list = redisUtil.lGet("list", 0, -1);
        System.out.println(list);
    }

}
