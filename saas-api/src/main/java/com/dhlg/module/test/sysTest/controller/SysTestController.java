package com.dhlg.module.test.sysTest.controller;

import com.alibaba.fastjson.JSON;
import com.dhlg.module.test.sysTest.entity.SysTest;
import com.dhlg.module.test.sysTest.service.ISysTestService;
import com.dhlg.utils.Parameter.QueryEntity;
import com.dhlg.utils.Result;
import com.dhlg.utils.common.ExcelUtils;
import com.dhlg.utils.common.StringUtils;
import com.dhlg.exception.ParamIsNullException;
import com.dhlg.utils.common.UserInfo;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xu
 * @since
 */

@RestController
@RequestMapping("/api/test/sysTest")
@CrossOrigin
public class SysTestController {

        @Autowired
        ISysTestService doService;

        @Autowired
        UserInfo userInfo;

        @Autowired
        public RestHighLevelClient restHighLevelClient;

        @ApiOperation("创建索引")
        @GetMapping("/testCreateIndex")
        public void testCreateIndex() throws IOException {
                CreateIndexRequest request = new CreateIndexRequest("liuyou_index");
                CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
                System.out.println(response.isAcknowledged());// 查看是否创建成功
                System.out.println(response);// 查看返回对象
//                restHighLevelClient.close();
        }

        @ApiOperation("测试获取索引，并判断其是否存在")
        @GetMapping("/testIndexIsExists")
        public void testIndexIsExists() throws IOException {
                GetIndexRequest request = new GetIndexRequest("liuyou_index");
                boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
                System.out.println(exists);// 索引是否存在
//                restHighLevelClient.close();
        }

        @ApiOperation("测试索引删除")
        @GetMapping("/testDeleteIndex")
        public void testDeleteIndex() throws IOException {
                DeleteIndexRequest request = new DeleteIndexRequest("liuyou_index");
                AcknowledgedResponse response = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
                System.out.println(response.isAcknowledged());// 是否删除成功
//                restHighLevelClient.close();
        }


        @ApiOperation("测试添加文档(先创建一个User实体累)")
        @GetMapping("/testAddDocument")
        public void testAddDocument() throws IOException {
                // 创建一个User对象
                User liuyou = new User("liuyou", 18);
                // 创建请求
                IndexRequest request = new IndexRequest("liuyou_index");
                // 制定规则 PUT /liuyou_index/_doc/1
                request.type("_doc");
                request.id("1");// 设置文档ID
                request.timeout(TimeValue.timeValueMillis(1000));// request.timeout("1s")
                // 将我们的数据放入请求中
                request.source(JSON.toJSONString(liuyou), XContentType.JSON);
                // 客户端发送请求，获取响应的结果
                IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
                System.out.println(response.status());// 获取建立索引的状态信息 CREATED
                System.out.println(response);// 查看返回内容 IndexResponse[index=liuyou_index,type=_doc,id=1,version=1,result=created,seqNo=0,primaryTerm=1,shards={"total":2,"successful":1,"failed":0}]
        }


        @ApiOperation("测试获得文档信息")
        @GetMapping("/testGetDocument")
        public void testGetDocument() throws IOException {
                GetRequest request = new GetRequest("liuyou_index","_doc","1");
                GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
                System.out.println(response.getSourceAsString());// 打印文档内容
                System.out.println(request);// 返回的全部内容和命令是一样的
//                restHighLevelClient.close();
        }


        @ApiOperation("测试更新文档内容")
        @GetMapping("/testUpdateDocument")
        public void testUpdateDocument() throws IOException {
                UpdateRequest request = new UpdateRequest("liuyou_index", "_doc","1");
                User user = new User("lmk",11);
                request.doc(JSON.toJSONString(user),XContentType.JSON);
                UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
                System.out.println(response.status()); // OK
//                restHighLevelClient.close();
        }

        @ApiOperation("测试删除文档")
        @GetMapping("/testDeleteDocument")
        public void testDeleteDocument() throws IOException {
                DeleteRequest request = new DeleteRequest("liuyou_index", "_doc","1");
                request.timeout("1s");
                DeleteResponse response = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
                System.out.println(response.status());// OK
        }

        // 查询
        // SearchRequest 搜索请求
        // SearchSourceBuilder 条件构造
        // HighlightBuilder 高亮
        // TermQueryBuilder 精确查询
        // MatchAllQueryBuilder
        // xxxQueryBuilder ...
        @ApiOperation("查询文档")
        @GetMapping("/testSearch")
        public  void testSearch() throws IOException {
                // 1.创建查询请求对象
                SearchRequest searchRequest = new SearchRequest();
                // 2.构建搜索条件
                SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
                // (1)查询条件 使用QueryBuilders工具类创建
                // 精确查询
                TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "liuyou");
                //        // 匹配查询
                //        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
                // (2)其他<可有可无>：（可以参考 SearchSourceBuilder 的字段部分）
                // 设置高亮
                searchSourceBuilder.highlighter(new HighlightBuilder());
                //        // 分页
                //        searchSourceBuilder.from();
                //        searchSourceBuilder.size();
                searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
                // (3)条件投入
                searchSourceBuilder.query(termQueryBuilder);
                // 3.添加条件到请求
                searchRequest.source(searchSourceBuilder);
                // 4.客户端查询请求
                SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
                // 5.查看返回结果
                SearchHits hits = search.getHits();
                System.out.println(JSON.toJSONString(hits));
                System.out.println("=======================");
                for (SearchHit documentFields : hits.getHits()) {
                        System.out.println(documentFields.getSourceAsMap());
                }
        }

        @ApiOperation("批量增加数据")
        @GetMapping("/test1")
        public void test1() throws IOException {
                IndexRequest request = new IndexRequest("bulk");// 没有id会自动生成一个随机ID
                request.source(JSON.toJSONString(new User("liu",1)),XContentType.JSON);
                request.source(JSON.toJSONString(new User("min",2)),XContentType.JSON);
                request.source(JSON.toJSONString(new User("kai",3)),XContentType.JSON);
                IndexResponse index = restHighLevelClient.index(request, RequestOptions.DEFAULT);
                System.out.println(index.status());// created
        }

        // 特殊的，真的项目一般会 批量插入数据
        @ApiOperation("批量增加数据")
        @GetMapping("/testBulk")
        public void testBulk() throws IOException {
                BulkRequest bulkRequest = new BulkRequest();
                bulkRequest.timeout("10s");
                ArrayList<User> users = new ArrayList<>();
                users.add(new User("liuyou-1",1));
                users.add(new User("liuyou-2",2));
                users.add(new User("liuyou-3",3));
                users.add(new User("liuyou-4",4));
                users.add(new User("liuyou-5",5));
                users.add(new User("liuyou-6",6));
                // 批量请求处理
                for (int i = 0; i < users.size(); i++) {
                        bulkRequest.add(
                                // 这里是数据信息
                                new IndexRequest("bulk")
//                                        .id(""+(i + 1)) // 没有设置id 会自定生成一个随机id/
                                        .type("_doc")
                                        .source(JSON.toJSONString(users.get(i)),XContentType.JSON)
                        );
                }
                BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
                System.out.println(bulk.status());// ok
        }

        @ApiOperation("保存或者更新")
        @PostMapping("/saveOrUpdate")
        public Result saveOrUpdate(@RequestBody SysTest sysTest) {
                if(StringUtils.isBlank(sysTest)){
                        throw new ParamIsNullException();
                }
                return  doService.saveOrUpdateCommon(sysTest);
        }

        @ApiOperation("删除（物理删除）")
        @DeleteMapping("/deleteById/{id}")
        public Result delete(@PathVariable String id) {
                new Hashtable<>();
                new HashMap<>();
                return doService.delete(id);
        }

        @ApiOperation("查询")
        @PostMapping("/query")
        public Result query(@RequestBody QueryEntity<SysTest> parameter) {
                if(StringUtils.isBlank(parameter)){
                        throw new ParamIsNullException();
                }
                return  doService.query(parameter);
        }

        @ApiOperation("查询")
        @GetMapping("/queryById/{id}")
        public Result query(@PathVariable String id) {
                System.out.println("aaaa");
                return  doService.query(id);
        }

        @ApiOperation("批量删除（物理删除）")
        @PostMapping("/batchDelete")
        public Result batchDelete(@RequestBody List<String> ids) {

                return doService.deleteBatch(ids);
        }

        @ApiOperation("下载")
        @PostMapping("/down")
        public void down(@RequestBody List<SysTest> data , HttpServletResponse response) {
                for(SysTest test : data){
                        String status =  "1".equals(test.getTestStatus()) ?"启用" : "禁用";
                        test.setTestStatus(status);
                }

                ExcelUtils.downSimple(response,data);
        }

        @ApiOperation("复杂导出模板")
        @PostMapping("/down2")
        public void down2(@RequestBody List<SysTest> data , HttpServletResponse response){
                for(SysTest test : data){
                        String status =  "1".equals(test.getTestStatus()) ?"启用" : "禁用";
                        test.setTestStatus(status);
                }
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("total", 1000);
                ExcelUtils.down("demo.xlsx",response,data,map);
        }

        @ApiOperation("下载")
        @PostMapping("/down3")
        public void down3(HttpServletResponse response) {
                doService.down3(response);
        }

}

