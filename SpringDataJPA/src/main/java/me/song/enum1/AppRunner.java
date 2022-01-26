package me.song.enum1;

import me.song.enum1.enum1.EnumMapperValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {
    @Autowired
    ApplicationContext context;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        String[] beans = context.getBeanDefinitionNames();

//        System.out.println(ev.getEm().getClass());
//        System.out.println(ev.getEm().getClass().toString());


//        for (String bean : beans){
//            System.out.println(bean);
//        }

    }
}
