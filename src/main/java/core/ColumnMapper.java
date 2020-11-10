package core;

import entity.Column;
import entity.FieldGen;
import utils.CamelCast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ColumnMapper {
    private static final HashMap<String,String> typeMapping = new HashMap<>();
    static {
        typeMapping.put("bigint","Long");
        typeMapping.put("varchar","String");
        typeMapping.put("int","Integer");
        typeMapping.put("datetime","Date");
        typeMapping.put("tinyint","Integer");
        typeMapping.put("double","Double");
    }

    public List<FieldGen> processColumn(List<Column> columns) {
        List<FieldGen> list = new ArrayList<>();
        if(columns != null && !columns.isEmpty()){
            for (Column column : columns) {
                FieldGen fieldGen = new FieldGen();
                fieldGen.setComment(column.getColumnComment());
                fieldGen.setColumnName(column.getColumnName());
                fieldGen.setName(CamelCast.toCamel(column.getColumnName(),false));
                String columnKey = column.getColumnKey();
                fieldGen.setPk(columnKey!=null && columnKey.equals("PRI"));
                fieldGen.setJavaType(typeMapping.get(column.getDataType()));
                list.add(fieldGen);
            }
        }
        return list;
    }
    
    
}
