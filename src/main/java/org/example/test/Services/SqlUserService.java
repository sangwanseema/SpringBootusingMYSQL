package org.example.test.Services;

import org.example.test.Respository.SqlUserRepository;

import org.example.test.entity.SqlUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SqlUserService
{
     @Autowired
     SqlUserRepository sqlUserRepository;
     public void saveUser(SqlUser user)
     {
      sqlUserRepository.save(user);
     }

}
