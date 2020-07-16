package cn.edu.nju.ics.perish.instance;

import com.alibaba.fastjson.JSONObject;
import org.flowable.engine.RuntimeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstanceService {

  private final RuntimeService runtimeService;

  public InstanceService(RuntimeService runtimeService) {
    this.runtimeService = runtimeService;
  }

  public InstanceEntity createInstanceById(String id, JSONObject parameters) {
    return new InstanceEntity(runtimeService.startProcessInstanceById(id, parameters));
  }

  public List<InstanceEntity> getAllInstances() {
    return runtimeService
        .createProcessInstanceQuery()
        .list()
        .stream()
        .map(processInstance -> new InstanceEntity(processInstance))
        .collect(Collectors.toList());
  }
}
