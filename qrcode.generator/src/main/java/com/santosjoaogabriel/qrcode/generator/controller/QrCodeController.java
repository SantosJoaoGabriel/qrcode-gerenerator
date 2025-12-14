package com.santosjoaogabriel.qrcode.generator.controller;

import com.santosjoaogabriel.qrcode.generator.dto.QrCodeGenareteResponse;
import com.santosjoaogabriel.qrcode.generator.dto.QrCodeGenerateRequest;
import com.santosjoaogabriel.qrcode.generator.service.QrCodeGeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {

    private final QrCodeGeneratorService qrCodeGeneratorService;

    public QrCodeController(QrCodeGeneratorService qrCodeService) {
        this.qrCodeGeneratorService = qrCodeService;
    }

    @PostMapping
    public ResponseEntity<QrCodeGenareteResponse> generate(@RequestBody QrCodeGenerateRequest request){
        try {
            QrCodeGenareteResponse response = this.qrCodeGeneratorService.generateAndUploadQrCode(request.text());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
