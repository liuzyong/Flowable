package cn.edu.nju.ics.perish.document;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends MongoRepository<DocumentEntity, String> {

  DocumentEntity findByTaskIdAndInstanceId(String taskId, String instanceId);

  List<DocumentEntity> findAllByInstanceId(String instanceId);
}
