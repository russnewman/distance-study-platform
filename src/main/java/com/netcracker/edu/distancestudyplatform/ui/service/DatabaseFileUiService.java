package com.netcracker.edu.distancestudyplatform.ui.service;

import com.netcracker.edu.distancestudyplatform.model.DatabaseFile;
import org.springframework.web.multipart.MultipartFile;


public interface DatabaseFileUiService {
    void saveDatabaseFile(MultipartFile multipartFile);
    DatabaseFile getFile(String id);
}
