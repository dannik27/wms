package com.patis.wms.workflow;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.activiti.engine.task.Task;

@Data @NoArgsConstructor @AllArgsConstructor
public class TaskDTO {

  private String id;
  private String name;
  private Date date;

  public TaskDTO(Task task){
    this.id = task.getId();
    this.name = task.getName();
    this.date = task.getCreateTime();
  }

}
