package com.santosjoaogabriel.qrcode.generator.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.santosjoaogabriel.qrcode.generator.dto.QrCodeGenareteResponse;
import com.santosjoaogabriel.qrcode.generator.ports.StoregePort;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;


@Service
public class QrCodeGeneratorService {
    private final StoregePort storege;

    public QrCodeGeneratorService(StoregePort storege) {
        this.storege = storege;
    }

    public QrCodeGenareteResponse generateAndUploadQrCode(String text) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = QRCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PGN", pngOutputStream);
        byte[] pngQrCodeData = pngOutputStream.toByteArray();

        String url = storege.uploadFile(pngQrCodeData, UUID.randomUUID().toString(), "image/png");

        return new QrCodeGenareteResponse(url);
    }
}
