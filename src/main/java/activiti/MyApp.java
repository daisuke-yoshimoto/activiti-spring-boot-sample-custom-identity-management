package activiti;

import java.util.ArrayList;
import java.util.List;

import activiti.identity.CustomGroupManagerFactory;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyApp {

    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }

    @Bean
    InitializingBean usersAndGroupsInitializer(final ProcessEngineConfiguration processEngineConfiguration) {

        return new InitializingBean() {
            public void afterPropertiesSet() throws Exception {
                
                List<SessionFactory> customSessionFactories = new ArrayList<SessionFactory>();
                customSessionFactories.add(new CustomGroupManagerFactory());
                ((ProcessEngineConfigurationImpl) processEngineConfiguration).setCustomSessionFactories(customSessionFactories);
            }
        };
    }
}