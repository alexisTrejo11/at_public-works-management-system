package com.publicworks.public_works_management.bids.infrastructure.ports.input.rest.api.controllers;

import com.publicworks.public_works_management.bids.application.dto.DocumentResponse;
import com.publicworks.public_works_management.shared.response.http.ResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bids")
@RequiredArgsConstructor
public class BidDocumentController {

    @GetMapping("/documents/{documentsId}")
    public ResponseWrapper<DocumentResponse> getDocumentDetailsById(@PathVariable String documentsId) {
        return null;
    }

    @GetMapping("{bidId}/documents")
    public ResponseWrapper<List<DocumentResponse>> getBidDocuments(@PathVariable String bidId) {
        return null;
    }

    @PostMapping("/documents")
    public ResponseWrapper<DocumentResponse> addDocuments(@PathVariable String bidId) {
        return null;
    }

    @PatchMapping("/documents/{documentsId}")
    public ResponseWrapper<DocumentResponse> updateDocument(@PathVariable String bidId) {
        return null;
    }

    @DeleteMapping("/documents/{documentsId}")
    public ResponseWrapper<DocumentResponse> deleteDocument(@PathVariable String bidId) {
        return null;
    }
}
