package cn.edu.nju.ics.perish.schema;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schema")
public class SchemaController {

  private final SchemaService schemaService;

  public SchemaController(SchemaService schemaService) {
    this.schemaService = schemaService;
  }

  @GetMapping
  public List<SchemaEntity> getAllSchemas() {
    return schemaService.getAllSchemas();
  }

  @GetMapping("/{id}")
  public SchemaEntity getSchema(@PathVariable String id) {
    return schemaService.getSchema(id);
  }

  @PostMapping
  public SchemaEntity createSchema(@RequestBody JSONObject schema) {
    return schemaService.createSchema(schema);
  }

  @PutMapping("/{id}")
  public SchemaEntity updateSchema(@PathVariable String id, @RequestBody JSONObject schema) {
    return schemaService.updateSchema(id, schema);
  }
}
