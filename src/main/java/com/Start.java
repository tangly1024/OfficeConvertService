package com;

import com.controller.SimpleController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * date: 2018/1/15 15:44 <br/>
 *
 * @author tangly
 * @since JDK 1.7
 */
@SpringBootApplication
public class Start {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SimpleController.class, args);
    }

}
