package com.portfolio.project.repository.user;

import com.portfolio.project.domain.user.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Long> {
}
