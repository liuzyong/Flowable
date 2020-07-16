package cn.edu.nju.ics.perish.document;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {
  private final DocumentRepository documentRepository;

  public DocumentService(DocumentRepository documentRepository) {
    this.documentRepository = documentRepository;
  }


  public DocumentEntity getDocument(String taskId, String instanceId) {

    DocumentEntity documentEntity = documentRepository
        .findByTaskIdAndInstanceId(taskId, instanceId);

    if (documentEntity == null)
      documentEntity = documentRepository.save(new DocumentEntity(taskId, instanceId));

    return documentEntity;
  }

  public DocumentEntity updateDocument(String taskId, String instanceId, JSONObject update) {
    DocumentEntity documentEntity = documentRepository
        .findByTaskIdAndInstanceId(taskId, instanceId);
    documentEntity.setDocument(update);
    return documentRepository.save(documentEntity);
  }

  public List<DocumentEntity> getDocuments(String instanceId) {
    return documentRepository.findAllByInstanceId(instanceId);
  }
}
