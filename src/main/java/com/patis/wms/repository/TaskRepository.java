package com.patis.wms.repository;

import com.patis.wms.entity.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {


    List<Task> findByWorker_IdOrderByTimeBeginDesc(long id_worker);

}
