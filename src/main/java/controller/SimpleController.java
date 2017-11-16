package controller;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.DocumentFormatRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import util.StringUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tangly on 2017/11/15.
 */
@RestController
@ComponentScan
@EnableAutoConfiguration
public class SimpleController {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SimpleController.class, args);
    }


    @Autowired
    private DocumentConverter documentConverter;

    /**
     * 格式信息列表
     */
    @Autowired
    private DocumentFormatRegistry documentFormatRegistry;

    @RequestMapping("/")
    String index() {
        return "访问首页";
    }


    @RequestMapping(value = "/convert")
    String convert(@RequestParam("sourceFile") String sourceFilePath, @RequestParam("targetFile") String targetFilePath) {

        System.out.println("[Start]调用文档转换服务:" + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        try {

            //文件路径格式化
            sourceFilePath = sourceFilePath.replace("/", File.separator).replace("\\",File.separator);
            targetFilePath = targetFilePath.replace("/",File.separator).replace("\\",File.separator);
            System.out.println("sourceFilePath:"+sourceFilePath+" "+ "; targetFilePath:"+targetFilePath);

            File sourceFile = new File(sourceFilePath);
            if(!sourceFile.exists()){
                System.out.println( "error:源文件 "+sourceFilePath+" 不存在");
                return "error:源文件 "+sourceFilePath+" 不存在";
            }
            File targetFile = new File(targetFilePath);
            if(!targetFile.exists()){
//                return "error:目标文件已存在";
                if(!targetFile.createNewFile()){
                    System.out.println("error:创建目标文件失败");
                    return "error:创建目标文件失败";
                }
            }

            String inputExtension = StringUtil.getFileExtension(sourceFile);
//            DocumentFormat inputFormat = documentFormatRegistry.getFormatByFileExtension(inputExtension);
            DocumentFormat outputFormat = documentFormatRegistry.getFormatByFileExtension("pdf");

            //用org.artofsolving.jodconverter
            documentConverter.convert(sourceFile,targetFile,outputFormat);
            //用com
//            documentConverter.convert(sourceFile).to(targetFile).execute();
        } catch (Exception e) {
            System.out.println("[Error] 异常调用文档转换服务:" + e.getMessage() + " " +  new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
            return "error:异常" + e.getMessage();
        }
        System.out.println("[End] 成功调用文档转换服务:" + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        return "success:成功";
    }

}
