package cn.edu.nju.ics.perish.document;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController {

  private final DocumentService documentService;

  public DocumentController(DocumentService documentService) {
    this.documentService = documentService;
  }

  @GetMapping
  public DocumentEntity getDocument(
      @RequestParam String taskId,
      @RequestParam String instanceId
  ) {
    return documentService.getDocument(taskId, instanceId);
  }

  @GetMapping("/{instanceId}")
  public List<DocumentEntity> getDocuments(
      @PathVariable String instanceId) {
    return documentService.getDocuments(instanceId);
  }

  @PutMapping
  public DocumentEntity updateDocument(
      @RequestParam String taskId,
      @RequestParam String instanceId,
      @RequestBody JSONObject update
  ) {
    return documentService.updateDocument(taskId, instanceId, update);
  }
}
