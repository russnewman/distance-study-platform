package com.netcracker.edu.distancestudyplatform.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}
