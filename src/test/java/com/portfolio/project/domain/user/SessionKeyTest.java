package com.portfolio.project.domain.user;

import com.portfolio.project.domain.user.SessionKey;
import com.portfolio.project.repository.user.SessionKeyRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SessionKeyTest {

    @Autowired
    private SessionKeyRepository sessionKeyRepository;

    @Test
    public void testSaveSessionKeyWithoutUser() {
        //Given
        SessionKey sessionKey = new SessionKey();
        sessionKey.generateSessionKey();

        //When
        sessionKeyRepository.save(sessionKey);
        Long id = sessionKey.getId();
        Optional<SessionKey> returnSessionKey = sessionKeyRepository.findById(id);

        //Then
        Assert.assertTrue(returnSessionKey.isPresent());
        Assert.assertNotEquals(Optional.of(0L), returnSessionKey.get().getId());
        Assert.assertNotEquals(0, returnSessionKey.get().getSessionKey());
        Assert.assertNotEquals(0, returnSessionKey.get().getTermOfValidity());

        //Clean up
        try{
            sessionKeyRepository.deleteById(id);
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}