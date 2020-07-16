package cn.edu.nju.ics.perish.usertask;

import lombok.Data;
import org.flowable.task.api.Task;

@Data
public class UserTaskEntity {

  String id;
  String name;
  String instanceId;

  public UserTaskEntity(Task task) {
    this.id = task.getId();
    this.name = task.getName();
    this.instanceId = task.getProcessInstanceId();
  }
}
