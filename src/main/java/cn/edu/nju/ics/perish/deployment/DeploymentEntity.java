package cn.edu.nju.ics.perish.deployment;

import lombok.Data;
import org.flowable.engine.repository.ProcessDefinition;

@Data
public class DeploymentEntity {
  String name;
  String id;
  String key;
  String deploymentId;

  public DeploymentEntity(ProcessDefinition processDefinition) {
    this.name = processDefinition.getName();
    this.id = processDefinition.getId();
    this.key = processDefinition.getKey();
    this.deploymentId = processDefinition.getDeploymentId();
  }
}
