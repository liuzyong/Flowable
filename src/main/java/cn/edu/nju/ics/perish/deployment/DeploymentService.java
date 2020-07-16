package cn.edu.nju.ics.perish.deployment;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeploymentService {

  final RepositoryService repositoryService;

  public DeploymentService(RepositoryService repositoryService) {
    this.repositoryService = repositoryService;
  }

  ProcessDefinition getDefinition(String deploymentId) {
    return repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult();
  }

  Deployment deployXML(String resourceName, String xml) throws IOException {
    File file = new ClassPathResource("definitions/" + resourceName).getFile();
    FileOutputStream fileOutputStream = new FileOutputStream(file);
    fileOutputStream.write(xml.getBytes());
    fileOutputStream.close();
    return repositoryService.createDeployment().addString(resourceName, xml).deploy();
  }

  String getXML(String resourceName) throws IOException {
    byte[] result = Files.readAllBytes(
        new ClassPathResource("definitions/" + resourceName)
            .getFile()
            .toPath());
    return new String(result, StandardCharsets.UTF_8);
  }

  public DeploymentEntity updateByDeploymentIdAndXML(String deploymentId, String xml) throws IOException {
    String resourceName = getDefinition(deploymentId).getResourceName();
    repositoryService.deleteDeployment(deploymentId);
    return new DeploymentEntity(getDefinition(deployXML(resourceName, xml).getId()));
  }

  public String getDeploymentXML(String deploymentId) throws IOException {
    String resourceName = getDefinition(deploymentId).getResourceName();
    return getXML(resourceName);
  }

  public List<DeploymentEntity> getDeployments(String startUser, String startUserGroups) {

    ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();

    if (startUser != null || startUserGroups != null) {
      List<String> groups =
          startUserGroups == null ?
              new ArrayList<String>() :
              Arrays.asList(startUserGroups.split(","));
      query = query.startableByUserOrGroups(startUser, groups);
    }
    
    return query
        .list()
        .stream()
        .map(processDefinition -> new DeploymentEntity(processDefinition))
        .collect(Collectors.toList());
  }
}
