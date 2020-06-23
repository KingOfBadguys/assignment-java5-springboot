package com.king.repository;

import com.king.model.Member;
import org.springframework.data.repository.CrudRepository;

public interface IMemberRepository extends CrudRepository<Member,Integer> {
}
