package com.skills.neki.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skills.neki.dto.SkillsDTO;
import com.skills.neki.exceptions.ResourceNotFoundException;
import com.skills.neki.model.Skills;
import com.skills.neki.services.SkillsService;

@RestController
@RequestMapping("/skills")
public class SkillsController {

    @Autowired
    private SkillsService skillsService;

    @PostMapping("/register_skills")
    public ResponseEntity<Skills> createSkills(@RequestBody SkillsDTO skillsDTO) {
        Skills savedSkills = skillsService.saveSkills(skillsDTO);
        return ResponseEntity.ok(savedSkills);
    }

    @GetMapping("/listar_skills")
    public ResponseEntity<List<SkillsDTO>> getAllSkills() {
        List<SkillsDTO> skills = skillsService.getAllSkills();
        return ResponseEntity.ok(skills);
    }

    @PatchMapping("/alterar_skills/{id}")
    public ResponseEntity<Skills> updateSkills(@PathVariable Long id, @RequestBody SkillsDTO skillsDTO) {
        Skills updatedSkills = skillsService.updateSkills(id, skillsDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Skill não encontrada com id: " + id));
        return ResponseEntity.ok(updatedSkills);
    }

    @DeleteMapping("/deletar_skills/{id}")
    public ResponseEntity<String> deleteSkills(@PathVariable Long id) {
        if (!skillsService.existsById(id)) {
            throw new ResourceNotFoundException("Skill não encontrada com id: " + id);
        }
        skillsService.deleteSkills(id);
        return ResponseEntity.ok("Skill deletada com sucesso");
    }
}
