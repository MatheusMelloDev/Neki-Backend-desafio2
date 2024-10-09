package com.skills.neki.controllers;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import java.io.IOException;


@RestController
@RequestMapping("/skills")
public class SkillsController {

    @Autowired
    private SkillsService skillsService;
    
    @Autowired SkillsRepository skillsRepository;
    
  

    @PostMapping("/{id}/photo")
    public ResponseEntity<Skills> uploadPhoto(@PathVariable Long id, @RequestParam("photo") MultipartFile photo) {
        Skills skills = skillsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id " + id));
        try {
            // Converte o arquivo para bytes e salva no atributo photo
            skills.setPhoto(photo.getBytes());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        // Salva a skill com a foto atualizada
        skillsRepository.save(skills);
        return ResponseEntity.ok(skills);
    }
    
    @GetMapping("/{id}/photo")
    public ResponseEntity<String> getPhoto(@PathVariable Long id) {
        Skills skills = skillsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id " + id));
        
        // Converte os bytes da foto para uma string Base64
        String base64Photo = Base64.getEncoder().encodeToString(skills.getPhoto());
        
        // Retorna a imagem codificada em Base64
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
