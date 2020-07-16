package cn.edu.nju.ics.perish.usertask;

import cn.edu.nju.ics.perish.schema.SchemaEntity;
import cn.edu.nju.ics.perish.schema.SchemaService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.ExtensionElement;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserTaskService {

  private final TaskService taskService;
  private final SchemaService schemaService;
  private final RepositoryService repositoryService;

  public UserTaskService(
      TaskService taskService,
      SchemaService schemaService,
      RepositoryService repositoryService) {
    this.taskService = taskService;
    this.schemaService = schemaService;
    this.repositoryService = repositoryService;
  }

  public SchemaEntity getSchemaIdsByTaskId(String taskId) {
    Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
    BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
    FlowElement flowElement = bpmnModel.getFlowElement(task.getTaskDefinitionKey());
    return schemaService.getSchema(flowElement.getDocumentation());
  }

  public List<UserTaskEntity> getTasksByUserId(String userId) {
    return taskService
        .createTaskQuery()
        .taskAssignee(userId)
        .list()
        .stream()
        .map(task -> new UserTaskEntity(task))
        .collect(Collectors.toList());
  }

  public void completeTask(String taskId, JSONObject parameters) {
    taskService.complete(taskId, parameters);
  }

  public JSONObject getOptions(String taskId) {
    Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
    BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
    FlowElement flowElement = bpmnModel.getFlowElement(task.getTaskDefinitionKey());

    Map<String, List<ExtensionElement>> extensionElements = flowElement.getExtensionElements();

    JSONObject result = new JSONObject();

//    for (List<ExtensionElement> elements : extensionElements.values()) {
//      JSONArray jsonArray = new JSONArray();
//      for (ExtensionElement element : elements) {
//        jsonArray.add(element.getElementText());
//      }
//      result.put()
//    }

    for (String key : extensionElements.keySet()) {
      JSONArray jsonArray = new JSONArray();
      for (ExtensionElement element : extensionElements.get(key)) {
        jsonArray.add(element.getElementText());
      }
      result.put(key, jsonArray);
    }

    return result;
  }
}
