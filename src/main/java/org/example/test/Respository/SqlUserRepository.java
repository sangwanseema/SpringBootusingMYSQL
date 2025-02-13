package org.example.test.Respository;

import org.example.test.entity.SqlUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SqlUserRepository extends JpaRepository<SqlUser, Integer>
{
    SqlUser findByUsername(String username);
}
