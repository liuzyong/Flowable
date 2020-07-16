package cn.edu.nju.ics.perish.instance;

import lombok.Data;
import org.flowable.engine.runtime.ProcessInstance;

@Data
public class InstanceEntity {

  String id;
  String deploymentId;

  public InstanceEntity(ProcessInstance processInstance) {
    this.id = processInstance.getId();
    this.deploymentId = processInstance.getDeploymentId();
  }
}
