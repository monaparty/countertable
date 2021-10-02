package me.monaparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import me.monaparty.countertable.ipfs.IPFSPinner;

@SpringBootApplication
@EnableScheduling
//@EnableAutoConfiguration
public class Application {

    public static void main(final String[] args) {
        final ApplicationContext ctx = SpringApplication.run(Application.class, args);
//        IPFSPinner pinner = ctx.getBean(IPFSPinner.class);
    }
}