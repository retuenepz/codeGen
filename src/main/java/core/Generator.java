package core;

import entity.Column;
import entity.FieldGen;
import entity.Table;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import utils.CamelCast;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Generator {
    private String dbHost="192.168.1.112";
    private String port = "33060";
    private String dbName = "axc_base";
    private String username = "zhangjianyu";
    private String password = "zhangjianyu";

    private String tableName = null;


    static final String  queryTables = "select table_name tableName, engine, table_comment tableComment, create_time createTime\n" +
            "from information_schema.tables\n" +
            "where table_schema = :dbName and table_name = :tableName";
    static final String queryColumn = "SELECT column_name columnName,DATA_TYPE dataType,COLUMN_KEY columnKey,COLUMN_COMMENT columnComment FROM information_schema.COLUMNS\n" +
            "WHERE TABLE_SCHEMA = :dbName AND TABLE_NAME = :tableName";
    FreemarkerConfig freemarkerConfig = null;
    ColumnMapper columnMapper = null;
    Sql2o sql2o = null;
    private String outputDir = "src/main/resources/output/";

    public Generator() {
        init();
    }

    public Generator(String dbHost, String port, String dbName, String username, String password) {
        this.dbHost = dbHost;
        this.port = port;
        this.dbName = dbName;
        this.username = username;
        this.password = password;
        init();
    }

    private void init(){
        freemarkerConfig = new FreemarkerConfig();
        try {
            freemarkerConfig.config();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sql2o = new Sql2o("jdbc:mysql://"+dbHost+":"+port+"/"+dbName, username, password);
        columnMapper = new ColumnMapper();
    }

    public void setTableName(String name ){
        this.tableName = name;
    }
    
    public void process() throws IOException, TemplateException {
        Table table = this.queryTableInfo();
        processTable(table);
        
        List<Column> columns = this.queryColumnInfo();
        List<FieldGen> fieldGens = columnMapper.processColumn(columns);
        if(table == null){
            System.out.println(String.format("表 %s 不存在",tableName));
            return;
        }
        if(columns==null || columns.isEmpty()){
            System.out.println(String.format("表%s的列查询失败",tableName));
            return;
        }

        Map<String,Object> root = new HashMap<>();
        root.put("table",table);
        root.put("fieldList",fieldGens);
        FileOutputStream fileOutputStream = new FileOutputStream(new File(outputDir + "entity.java"));
        Template entity = freemarkerConfig.getTemplate("entity.ftl");
        entity.process(root,new OutputStreamWriter(fileOutputStream,"UTF-8"));
    }


   

    private void processTable(Table table) {
        String tableName = table.getTableName();
        String className = CamelCast.toCamel(tableName, true);
        table.setClassName(className);
    }

    private List<Column> queryColumnInfo() {
        List<Column> columnList = null;
        try (Connection open = sql2o.open()){
            columnList = open.createQuery(queryColumn)
                    .addParameter("dbName", this.dbName)
                    .addParameter("tableName", tableName)
                    .executeAndFetch(Column.class);
        }
        return columnList;
    }

    private Table queryTableInfo() {
        Table table = null;
        try (Connection open = sql2o.open()){
            table = open.createQuery(queryTables)
                    .addParameter("dbName", this.dbName)
                    .addParameter("tableName",tableName)
                    .executeAndFetchFirst(Table.class);
        }
        return table;
    }
    
    private void setOutputDir(String dir){
        if(dir != null ){
            if(!dir.endsWith("/")){
                this.outputDir = dir+"/";
            }else{
                this.outputDir = dir;
            }
        }
    }

}
