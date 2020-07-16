package cn.edu.nju.ics.perish.instance;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instance")
public class InstanceController {

  private final InstanceService instanceService;

  public InstanceController(InstanceService instanceService) {
    this.instanceService = instanceService;
  }

  @GetMapping
  public List<InstanceEntity> getAllInstances() {
    return instanceService.getAllInstances();
  }

  @PostMapping("/{id}")
  public InstanceEntity createInstanceById(@PathVariable String id, @RequestBody JSONObject parameters) {
    return instanceService.createInstanceById(id, parameters);
  }
}
