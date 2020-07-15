package com.dhlg.config;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Administrator on 2019-02-28.
 */
// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中


public class CodeGenerator {
    private String projectPath = System.getProperty("user.dir");
    private AutoGenerator mpg = new AutoGenerator();
    private String moduleName;

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        CodeGenerator codeGenerator = new CodeGenerator();
        codeGenerator.mpg.setGlobalConfig(codeGenerator.getGlobalConfig());
        codeGenerator.mpg.setPackageInfo(codeGenerator.getPackageConfig());
        codeGenerator.mpg.setStrategy(codeGenerator.getStrategyConfig());
        codeGenerator.mpg.setDataSource(codeGenerator.getDataSourceConfig());
        codeGenerator.mpg.setCfg(codeGenerator.getInjectionConfig());
        codeGenerator.mpg.setTemplate(codeGenerator.getTemplateConfig());
        codeGenerator.mpg.execute();

        List<TableInfo> tableInfoList = codeGenerator.mpg.getTemplateEngine().getConfigBuilder().getTableInfoList();
        for (TableInfo t : tableInfoList) {
            Map<String, Object> objectMap = codeGenerator.mpg.getTemplateEngine().getObjectMap(t);
            String s = JSON.toJSONString(objectMap);
            System.out.println(s);
        }

    }

    /**
     * 全局配置
     *
     * @return GlobalConfig
     */
    private GlobalConfig getGlobalConfig() {
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("xu");
        gc.setOpen(false);
        gc.setSwagger2(false);
        gc.setFileOverride(false);//文件覆盖
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setDateType(DateType.ONLY_DATE);
//        gc.setMapperName("%sDao");
//        gc.setServiceImplName("%sService");
        return gc;
    }

    /**
     * 数据源配置
     *
     * @return DataSourceConfig
     */
    private DataSourceConfig getDataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/moban?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC");
        dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123");
        dsc.setTypeConvert(new MySqlTypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                if (fieldType.contains("varchar")) {
                    return DbColumnType.STRING;
                }
                if (fieldType.contains("decimal")) {
                    return DbColumnType.DOUBLE;
                }
                return super.processTypeConvert(globalConfig, fieldType);
            }
        });
        return dsc;
    }

    /**
     * 生成文件的包路径配置
     *
     * @return PackageConfig
     */
    private PackageConfig getPackageConfig() {
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.dhlg.module");
        pc.setParent("com.dhlg.module."+scanner("分组"));
        pc.setController("controller");
        pc.setMapper("dao");
        pc.setServiceImpl("service.impl");
        pc.setEntity("entity");
        pc.setXml("dao.xml");
        pc.setService("service");
        moduleName = scanner("模块名");
        pc.setModuleName(moduleName);
        return pc;
    }

    /**
     * 自定义配置
     *
     * @return InjectionConfig
     */
    private InjectionConfig getInjectionConfig() {
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                //自定义的模板变量不配置
                HashMap<String, Object> hashMap = new HashMap();
                hashMap.put("utilPackage", "com.dhlg.utils");
                hashMap.put("Parameter", "Parameter");
                hashMap.put("Result", "Result");
                hashMap.put("ID", "Id");
                hashMap.put("StringUtils", "StringUtils");
                hashMap.put("IDMethod", "uuid");
                super.setMap(hashMap);
            }
        };
//        String templatePath = "/templates/Property.java.vm";
        //自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
        //自定义配置会被优先输出
//        FileOutConfig fileOutConfig = new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名
//                return projectPath + "/src/main/java/com/bohe/module/" + moduleName + "/entity/Property/"
//                        + tableInfo.getEntityName() + "Property" + StringPool.DOT_JAVA;
////                return "com.example.demo.module." + moduleName + ".entity.Property"
////                        + tableInfo.getEntityName() + "Property" + StringPool.DOT_JAVA;
//            }
//        };
//        focList.add(fileOutConfig);
//        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    /**
     * 配置模板
     *
     * @return
     */
    private TemplateConfig getTemplateConfig() {

        TemplateConfig tc = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
//        tc.setEntity("templates/entity2.java");
//        tc.setServiceImpl("templates/service.java");
//        tc.setController("templates/controller.java");
//        tc.setMapper("templates/Mapper.java");
//        tc.setService(null);
//        tc.setController(null);
        return tc;
    }

    /**
     * 策略配置
     *
     * @return StrategyConfig
     */
    private StrategyConfig getStrategyConfig() {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //配置生成实体类时使用Lombok工具
        strategy.setEntityLombokModel(true);
        //生成RestControllerStyle风格的Controller
        strategy.setRestControllerStyle(true);
        strategy.setInclude(scanner("表名"));
        strategy.setTablePrefix("agt_prm");
        strategy.setControllerMappingHyphenStyle(true);
        //是否生成实体时，生成字段注解
        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setEntityColumnConstant(true);
        return strategy;
    }
}