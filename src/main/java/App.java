import core.FreemarkerConfig;
import core.Generator;
import entity.Table;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class App {
    
    public static void main(String[] args) throws IOException, TemplateException {
        Generator generator = new Generator("host","port","dbName","userName","password");

        generator.addTableName("table1");
        generator.addTableName("table2");
        generator.process();
    }


}
