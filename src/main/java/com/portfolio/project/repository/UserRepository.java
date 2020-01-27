package com.portfolio.project.repository;

import com.portfolio.project.domain.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Long> {
}
