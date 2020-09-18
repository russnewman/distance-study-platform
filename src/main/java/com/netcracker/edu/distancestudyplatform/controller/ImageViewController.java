package com.netcracker.edu.distancestudyplatform.controller;

import com.netcracker.edu.distancestudyplatform.model.DatabaseFile;
import com.netcracker.edu.distancestudyplatform.service.DatabaseFileService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class ImageViewController {
    private final DatabaseFileService dbFileService;

    public ImageViewController(DatabaseFileService dbFileService) {
        this.dbFileService = dbFileService;
    }

    @GetMapping("/image/{id}")
    public void showImage(
            @PathVariable String id,
            HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        DatabaseFile dbFile = dbFileService.getFile(id);
        InputStream is = new ByteArrayInputStream(dbFile.getFile());
        IOUtils.copy(is, response.getOutputStream());
    }
}
