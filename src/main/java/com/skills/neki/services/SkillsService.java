package com.skills.neki.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skills.neki.dto.SkillsDTO;
import com.skills.neki.model.Skills;
import com.skills.neki.repositores.SkillsRepository;

@Service
public class SkillsService {

    @Autowired
    private SkillsRepository repository;

    public Skills saveSkills(SkillsDTO dto) {
        Skills skills = new Skills();
        skills.setNome(dto.getNome());
        skills.setDescricao(dto.getDescricao());
        skills.setImagemUrl(dto.getImagemUrl());
        skills.setTecnologia(dto.getTecnologia());

        return repository.save(skills);
    }

    public List<SkillsDTO> getAllSkills() {
        return repository.findAll()
                         .stream()
                         .map(this::convertToDto)
                         .collect(Collectors.toList());
    }

    public Optional<Skills> updateSkills(Long id, SkillsDTO dto) {
        Optional<Skills> optionalSkills = repository.findById(id);
        if (optionalSkills.isPresent()) {
            Skills skills = optionalSkills.get();
            skills.setNome(dto.getNome());
            skills.setDescricao(dto.getDescricao());
            skills.setImagemUrl(dto.getImagemUrl());
            skills.setTecnologia(dto.getTecnologia());
           

            return Optional.of(repository.save(skills));
        } else {
            return Optional.empty();
        }
    }

    public void deleteSkills(Long id) {
        repository.deleteById(id);
    }

    private SkillsDTO convertToDto(Skills skills) {
        SkillsDTO dto = new SkillsDTO();
        dto.setIdSkill(skills.getIdSkill());
        dto.setNome(skills.getNome());
        dto.setDescricao(skills.getDescricao());
        dto.setImagemUrl(skills.getImagemUrl());
        dto.setTecnologia(skills.getTecnologia());
      

        return dto;
    }
}
