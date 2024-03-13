package com.enviro.assessment.grad001.CliffordKalake.controller;

import com.enviro.assessment.grad001.CliffordKalake.model.EnvironmentalData;
import com.enviro.assessment.grad001.CliffordKalake.service.FileProcessorService;
import com.enviro.assessment.grad001.CliffordKalake.util.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController// Specifies this class handles REST API requests and response bodies are directly serialized
public class FileProcessorController {
    @Autowired// Spring handles dependency injection for the fileProcessorService
    private FileProcessorService fileProcessorService;

    @PostMapping("/upload")// Maps HTTP POST requests with '/upload' path to this method
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            List<EnvironmentalData> processedData = fileProcessorService.processFile(file);
            // If processing is successful, return processed data with HTTP 200 (OK)
            return ResponseEntity.ok(processedData);

        } catch (FileUploadException e) {
            // Specific exception for file upload issues; create error response with HTTP 400 (Bad Request)
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);

        } catch (Exception e) {
            // Generic catch-all; create error response with HTTP 500 (Internal Server Error) and a generic message
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


    private static class ErrorResponse {
        private int status;
        private String message;

        public ErrorResponse(int status, String message) {
            this.status = status;
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
