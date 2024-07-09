package com.skills.neki.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skills.neki.model.Skills;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, Long> {

}
