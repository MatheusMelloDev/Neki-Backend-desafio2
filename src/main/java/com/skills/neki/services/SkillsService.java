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

    private static final int MAX_URL_LENGTH = 2000; // Um limite arbitrário para URLs

    @Autowired
    private SkillsRepository skillsRepository;

    public Skills saveSkills(SkillsDTO skillsDTO) {
        validateSkillsDTO(skillsDTO);
        Skills skills = new Skills();
        skills.setNome(skillsDTO.getNome());
        skills.setDescricao(skillsDTO.getDescricao());
        skills.setImagemUrl(skillsDTO.getImagemUrl());
        skills.setTecnologia(skillsDTO.getTecnologia());
        return skillsRepository.save(skills);
    }

    public List<SkillsDTO> getAllSkills() {
        List<Skills> skills = skillsRepository.findAll();
        // Converte Skills para SkillsDTO
        return skills.stream().map(skill -> new SkillsDTO(skill)).toList();
    }

    public Optional<Skills> updateSkills(Long id, SkillsDTO skillsDTO) {
        validateSkillsDTO(skillsDTO);
        return skillsRepository.findById(id).map(skills -> {
            skills.setNome(skillsDTO.getNome());
            skills.setDescricao(skillsDTO.getDescricao());
            skills.setImagemUrl(skillsDTO.getImagemUrl());
            skills.setTecnologia(skillsDTO.getTecnologia());
            return skillsRepository.save(skills);
        });
    }

    public void deleteSkills(Long id) {
        skillsRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return skillsRepository.existsById(id);
    }

    private void validateSkillsDTO(SkillsDTO skillsDTO) {
        if (skillsDTO.getImagemUrl() != null && ((String) skillsDTO.getImagemUrl()).length() > MAX_URL_LENGTH) {
            throw new IllegalArgumentException("URL da imagem é muito longa");
        }
        // Adicione outras validações conforme necessário
    }
}
