package cn.edu.nju.ics.perish.usertask;

import cn.edu.nju.ics.perish.schema.SchemaEntity;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usertask")
public class UserTaskController {

  private final UserTaskService userTaskService;

  public UserTaskController(UserTaskService userTaskService) {
    this.userTaskService = userTaskService;
  }

  @GetMapping
  public List<UserTaskEntity> getTasksByUserId(@RequestParam String userId) {
    return userTaskService.getTasksByUserId(userId);
  }

  @GetMapping("/{taskId}")
  public SchemaEntity getSchema(@PathVariable String taskId) {
    return userTaskService.getSchemaIdsByTaskId(taskId);
  }

  @GetMapping("/{taskId}/options")
  public JSONObject getOptions(@PathVariable String taskId) {
    return userTaskService.getOptions(taskId);
  }

  @PostMapping("/{taskId}")
  public void completeTask(@PathVariable String taskId, @RequestBody JSONObject parameters) {
    userTaskService.completeTask(taskId, parameters);
  }


}
