package com.netcracker.edu.distancestudyplatform.service;

import com.netcracker.edu.distancestudyplatform.model.DatabaseFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DatabaseFileService {
    DatabaseFile storeFile(MultipartFile file);
    DatabaseFile getFile(String fileId);
    List<DatabaseFile> getFiles();
}
