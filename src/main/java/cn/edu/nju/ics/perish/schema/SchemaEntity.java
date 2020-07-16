package cn.edu.nju.ics.perish.schema;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class SchemaEntity {
  @Id
  String id;
  JSONObject schema;

  public SchemaEntity(JSONObject schema) {
    this.schema = schema;
  }

  public SchemaEntity(String id, JSONObject schema) {
    this.id = id;
    this.schema = schema;
  }
}
