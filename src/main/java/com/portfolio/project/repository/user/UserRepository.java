package com.portfolio.project.repository.user;

import com.portfolio.project.domain.user.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<Users, Long> {

    Optional<Users> findByLogin(String login);

    Optional<Users> findByUsersMail_PinConfirmMail(String pin);

    List<Users> findAll();

    Optional<Users> findByUsersMail_Mail(String mail);
}
