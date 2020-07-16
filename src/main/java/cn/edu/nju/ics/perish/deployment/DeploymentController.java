package cn.edu.nju.ics.perish.deployment;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/deployment")
public class DeploymentController {

  final DeploymentService deploymentService;

  public DeploymentController(DeploymentService deploymentService) {
    this.deploymentService = deploymentService;
  }

  @GetMapping
  public List<DeploymentEntity> getDeployments(
      @RequestParam(required = false) String startUser,
      @RequestParam(required = false) String startUserGroups
  ) {
    return deploymentService.getDeployments(startUser, startUserGroups);
  }

  @GetMapping("/{deploymentId}")
  public String getDeployment(@PathVariable String deploymentId) throws IOException {
    return deploymentService.getDeploymentXML(deploymentId);
  }

  @PutMapping("/{deploymentId}")
  public DeploymentEntity updateDeployment(@PathVariable String deploymentId, @RequestBody String xml) throws IOException {
    return deploymentService.updateByDeploymentIdAndXML(deploymentId, xml);
  }
}
