package visca;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by dawid on 08.11.16.
 */
@SpringBootApplication
public class Application {
    Visca visca = new Visca();

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
