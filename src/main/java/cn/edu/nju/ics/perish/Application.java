package cn.edu.nju.ics.perish;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;

@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public CommandLineRunner init(final RepositoryService repositoryService) {
    return args -> {
      for (File bpmn : new ClassPathResource("definitions").getFile().listFiles())
        repositoryService.createDeployment().addInputStream(bpmn.getName(), new FileInputStream(bpmn)).deploy();

      for (ProcessDefinition definition : repositoryService.createProcessDefinitionQuery().list())
        System.out.println(definition.getResourceName() + ", " + definition.getName() + ", " + definition.getId() + ", "
            + definition.getDeploymentId());
    };
  }
}