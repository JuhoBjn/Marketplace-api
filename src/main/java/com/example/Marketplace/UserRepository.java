package com.example.Marketplace;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    @Override
    ArrayList<User> findAll();
    User findByUserID(long UserID);
    User findByName(String Name);
    Long deleteByUserID(long UserID);
}
