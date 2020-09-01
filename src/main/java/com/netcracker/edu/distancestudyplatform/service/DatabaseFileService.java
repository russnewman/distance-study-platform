package com.netcracker.edu.distancestudyplatform.service;

import com.netcracker.edu.distancestudyplatform.model.DatabaseFile;
import org.springframework.web.multipart.MultipartFile;

public interface DatabaseFileService {
    DatabaseFile storeFile(MultipartFile file);
    DatabaseFile getFile(String fileId);
}
