package com.patis.wms.repository;

import com.patis.wms.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

  Worker findTop1ByLoginAndPassword(String login, String password);

}
