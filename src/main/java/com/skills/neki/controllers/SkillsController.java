package com.skills.neki.controllers;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.skills.neki.dto.SkillsDTO;
import com.skills.neki.exceptions.ResourceNotFoundException;
import com.skills.neki.model.Skills;
import com.skills.neki.repositores.SkillsRepository;
import com.skills.neki.services.SkillsService;

import io.swagger.v3.oas.annotations.Operation;



@RestController
@RequestMapping("/skills")
public class SkillsController {

    @Autowired
    private SkillsService skillsService;
    
    @Autowired 
    private SkillsRepository skillsRepository;

    @Operation(summary = "Upload de foto para a skill", description = "Este endpoint permite fazer upload de uma foto para a skill.")
    @PostMapping(path = "/{id}/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Skills> uploadPhoto(@PathVariable Long id, @RequestParam("photo") MultipartFile photo) {
        Skills skills = skillsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id " + id));
        try {
           
            skills.setPhoto(photo.getBytes());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
  
        skillsRepository.save(skills);
        return ResponseEntity.ok(skills);
    }

    @GetMapping("/{id}/photo")
    public ResponseEntity<String> getPhoto(@PathVariable Long id) {
        Skills skills = skillsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id " + id));
        
      
        String base64Photo = Base64.getEncoder().encodeToString(skills.getPhoto());
        
        return ResponseEntity.ok(base64Photo);
    }

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

    @PutMapping("/alterar_infos/{id}")
    public ResponseEntity<Skills> updateSkillInfo(
        @PathVariable Long id,
        @RequestBody Skills updatedSkill) {

        // Busca a skill pelo ID
        Skills existingSkill = skillsRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Skill não encontrada com id: " + id));

        // Atualiza os campos
        existingSkill.setNome(updatedSkill.getNome());
        existingSkill.setDescricao(updatedSkill.getDescricao());
        existingSkill.setTecnologia(updatedSkill.getTecnologia());
        existingSkill.setNivel(updatedSkill.getNivel());

        // Salva as alterações no banco de dados
        skillsRepository.save(existingSkill);

        return ResponseEntity.ok(existingSkill);
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