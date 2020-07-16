package cn.edu.nju.ics.perish.document;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class DocumentEntity {
  @Id
  String id;
  String taskId;
  String instanceId;
  JSONObject document;

  public DocumentEntity(String taskId, String instanceId) {
    this.taskId = taskId;
    this.instanceId = instanceId;
  }

  public void setDocument(JSONObject document) {
    this.document = document;
  }
}
