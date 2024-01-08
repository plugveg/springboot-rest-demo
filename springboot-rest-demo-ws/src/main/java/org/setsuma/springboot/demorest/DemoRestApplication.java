package org.setsuma.springboot.demorest;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.setsuma.springboot.demorest.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "org.setsuma.springboot.demorest")
@EnableJpaRepositories("org.setsuma.springboot.demorest.repositories")
@EntityScan("org.setsuma.springboot.demorest.domain")
public class DemoRestApplication {
    
    private static final Logger LOG = LoggerFactory.getLogger(DemoRestApplication.class);
    
    @Autowired
    private Environment env;
    
    public static void main(String[] args) {
        var app = new SpringApplication(DemoRestApplication.class);        
        addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        LOG.info("Application '{}' is running!",env.getProperty("spring.application.name"));
    }

    /**
     * Initializes application.
     * <p>
     * Spring profiles can be configured with a program arguments --spring.profiles.active=your-active-profile
     * <p>
     * You can find more information on how profiles work with JHipster on <a href="http://jhipster.github.io/profiles/">http://jhipster.github.io/profiles/</a>.
     */
    @PostConstruct
    public void initApplication() {
        if (env.getActiveProfiles().length == 0) {
            LOG.warn("No Spring profile configured, running with default profile: {}", Constants.SPRING_PROFILE_DEVELOPMENT);
        } else {
            LOG.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
            Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
            if (activeProfiles.contains(Constants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(Constants.SPRING_PROFILE_PRODUCTION)) {
                LOG.error("You have misconfigured your application! It should not run " +
                        "with both the 'dev' and 'prod' profiles at the same time.");
            }
        }
    }
    
    /**
     * set a default to use when no profile is configured.
     */
    protected static void addDefaultProfile(SpringApplication app) {
        Map<String, Object> defProperties =  new HashMap<>();
        defProperties.put("spring.config.name", "demo-rest");
        /*
        * The default profile to use when no other profiles are defined
        * This cannot be set in the `application.yml` file.
        * See https://github.com/spring-projects/spring-boot/issues/1219
        */
        defProperties.put("spring.profiles.default", Constants.SPRING_PROFILE_DEVELOPMENT);
        app.setDefaultProperties(defProperties);
    }
}
