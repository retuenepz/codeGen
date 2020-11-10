package core;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;

public class FreemarkerConfig {

    private Configuration config = null;

    public void config() throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_20);
        File file = new File("src\\main\\resources\\template");
        System.out.println(file.getAbsolutePath());
        cfg.setDirectoryForTemplateLoading(file);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        config = cfg;
    }

    public Template getTemplate(String name){
        try {
            return config.getTemplate(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("err");
    }

}
