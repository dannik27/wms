package com.patis.wms.repository;

import com.patis.wms.entity.Company;
import com.patis.wms.entity.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {


    List<Task> findByWorker_Id(long id_worker);

}
