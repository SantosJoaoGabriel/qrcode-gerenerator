package com.santosjoaogabriel.qrcode.generator.ports;

public interface StoregePort {
    String uploadFile(byte[] fileData, String fileName, String contentType);
}
