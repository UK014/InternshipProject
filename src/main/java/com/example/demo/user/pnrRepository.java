package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface pnrRepository extends JpaRepository<pnr,Long> {

    @Query("SELECT p FROM pnr p WHERE p.pnr = :pnr")
    List<pnr> findAllByPnr(@Param("pnr") String pnr);
}
