package com.visions.controller;

import com.visions.service.VaultTransitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/vault")
public class VaultController {

    private final VaultTransitService vaultTransitService;

    public VaultController(VaultTransitService vaultTransitService){
        this.vaultTransitService = vaultTransitService;
    }

    @PostMapping(value = "/encrypt")
    public ResponseEntity<String> encryptText(@RequestParam("text") String text){
        return ResponseEntity.ok(vaultTransitService.encryptData(text));
    }

    @PostMapping(value = "/decrypt")
    public ResponseEntity<String> decryptText(@RequestParam("text") String text){
        return ResponseEntity.ok(vaultTransitService.decryptData(text));
    }
}
