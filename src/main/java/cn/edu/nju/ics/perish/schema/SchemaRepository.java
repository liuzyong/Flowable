package cn.edu.nju.ics.perish.schema;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchemaRepository extends MongoRepository<SchemaEntity, String> {
}
