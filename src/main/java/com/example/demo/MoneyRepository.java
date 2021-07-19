package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyRepository extends JpaRepository<Money, Integer> {
	List<Money> findByUid(Integer uid);
	List<Money> findByUidOrderByDateAsc(Integer uid);
}
