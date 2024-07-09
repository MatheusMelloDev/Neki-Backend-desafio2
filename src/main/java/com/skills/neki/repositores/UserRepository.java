package com.skills.neki.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skills.neki.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
