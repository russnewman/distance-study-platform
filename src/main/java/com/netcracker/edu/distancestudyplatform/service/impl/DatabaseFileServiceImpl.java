package com.netcracker.edu.distancestudyplatform.service.impl;

import com.netcracker.edu.distancestudyplatform.exception.FileNotFoundException;
import com.netcracker.edu.distancestudyplatform.exception.FileStorageException;
import com.netcracker.edu.distancestudyplatform.model.DatabaseFile;
import com.netcracker.edu.distancestudyplatform.repository.DatabaseFileRepository;
import com.netcracker.edu.distancestudyplatform.service.DatabaseFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DatabaseFileServiceImpl implements DatabaseFileService {
    private final DatabaseFileRepository dbFileRepository;

    @Autowired
    public DatabaseFileServiceImpl(DatabaseFileRepository dbFileRepository) {
        this.dbFileRepository = dbFileRepository;
    }


    @Override
    public DatabaseFile storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Filename contains invalid path sequence " + fileName);
            }
            DatabaseFile dbFile = new DatabaseFile(fileName, file.getContentType(), file.getBytes());
            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file: " + fileName, ex);
        }
    }

    @Override
    public DatabaseFile getFile(String fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }

}
