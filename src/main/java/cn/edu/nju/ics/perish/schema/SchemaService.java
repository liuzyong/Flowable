package cn.edu.nju.ics.perish.schema;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchemaService {

  private final SchemaRepository schemaRepository;

  public SchemaService(SchemaRepository schemaRepository) {
    this.schemaRepository = schemaRepository;
  }

  public List<SchemaEntity> getAllSchemas() {
    return schemaRepository.findAll();
  }

  public SchemaEntity createSchema(JSONObject schema) {
    return schemaRepository.save(new SchemaEntity(schema));
  }

  public SchemaEntity updateSchema(String id, JSONObject schema) {
    if (schemaRepository.findById(id).get() == null) return null;
    return schemaRepository.save(new SchemaEntity(id, schema));
  }

  public SchemaEntity getSchema(String id) {
    return schemaRepository.findById(id).get();
  }
}
