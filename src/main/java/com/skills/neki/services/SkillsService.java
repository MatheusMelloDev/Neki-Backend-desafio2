package com.skills.neki.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skills.neki.dto.SkillsDTO;
import com.skills.neki.model.Skills;
import com.skills.neki.repositores.SkillsRepository;

@Service
public class SkillsService {

    @Autowired
    private SkillsRepository skillsRepository;

  
    public Skills saveSkills(SkillsDTO skillsDTO) {
        Skills skill = new Skills();
        skill.setNome(skillsDTO.getNome());
        skill.setDescricao(skillsDTO.getDescricao());
        skill.setTecnologia(skillsDTO.getTecnologia());
        skill.setNivel(skillsDTO.getNivel()); 
        skill.setPhoto(skillsDTO.getPhoto());

        return skillsRepository.save(skill);
    }

   
    public Skills save(Skills skill) {
        return skillsRepository.save(skill);
    }

    public Optional<Skills> updateSkills(Long id, SkillsDTO skillsDTO) {
        return skillsRepository.findById(id).map(skill -> {
            skill.setNome(skillsDTO.getNome());
            skill.setDescricao(skillsDTO.getDescricao());
            skill.setTecnologia(skillsDTO.getTecnologia());
            skill.setNivel(skillsDTO.getNivel()); 
            return skillsRepository.save(skill);
        });
    }

    public void deleteSkills(Long id) {
        skillsRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return skillsRepository.existsById(id);
    }

    public List<SkillsDTO> getAllSkills() {
        List<Skills> skillsList = skillsRepository.findAll();
        return skillsList.stream().map(SkillsDTO::new).toList();
    }

}
