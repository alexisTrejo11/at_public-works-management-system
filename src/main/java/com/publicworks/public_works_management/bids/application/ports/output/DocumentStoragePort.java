package com.publicworks.public_works_management.bids.application.ports.output;

public interface DocumentStoragePort {
    String storeDocument(byte[] content, String fileName);
    void deleteDocument(String storagePath);
}
