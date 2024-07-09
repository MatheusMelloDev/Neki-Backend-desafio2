package com.skills.neki.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skills.neki.dto.SkillsDTO;
import com.skills.neki.model.Skills;
import com.skills.neki.services.SkillsService;

@RestController
@RequestMapping("/skills")
public class SkillsController {

    @Autowired
    private SkillsService skillsService;

    @PostMapping("/cadastrar_skills")
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
        Optional<Skills> updatedSkills = skillsService.updateSkills(id, skillsDTO);
        return updatedSkills.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deletar_skills/{id}")
    public ResponseEntity<Void> deleteSkills(@PathVariable Long id) {
        skillsService.deleteSkills(id);
        return ResponseEntity.noContent().build();
    }
}
