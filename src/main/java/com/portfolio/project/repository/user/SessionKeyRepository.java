package com.portfolio.project.repository.user;

import com.portfolio.project.domain.user.SessionKey;
import org.springframework.data.repository.CrudRepository;

public interface SessionKeyRepository extends CrudRepository<SessionKey, Long> {
}
