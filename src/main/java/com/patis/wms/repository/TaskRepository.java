package com.patis.wms.repository;

import com.patis.wms.entity.Task;
import com.patis.wms.entity.Transportation;
import org.springframework.data.repository.CrudRepository;


public interface TaskRepository extends CrudRepository<Task, Long> {


}
