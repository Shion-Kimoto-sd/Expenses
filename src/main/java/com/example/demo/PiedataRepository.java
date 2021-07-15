package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PiedataRepository extends JpaRepository<PieData, Integer> {
	//List<PieData> findByUid(Integer id);
}
