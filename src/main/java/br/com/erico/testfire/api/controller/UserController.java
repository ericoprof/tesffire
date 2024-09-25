package br.com.erico.testfire.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.erico.testfire.domain.service.FirestoreService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private FirestoreService firestoreService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestParam String userId, @RequestParam String email) {
        try {
            String updateTime = firestoreService.addUser(userId, email);
            return ResponseEntity.ok("Usuário adicionado com sucesso. Hora da última atualização: " + updateTime);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erro ao adicionar usuário: " + e.getMessage());
        }
    }
}