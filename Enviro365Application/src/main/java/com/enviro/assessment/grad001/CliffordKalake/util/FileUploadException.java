package com.enviro.assessment.grad001.CliffordKalake.util;

import java.io.IOException;

public class FileUploadException extends IOException {

    public FileUploadException(String message) {
        super(message);
    }

    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }
}
