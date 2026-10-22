package springprac.springbootdeveloper.service;

import springprac.springbootdeveloper.dto.UploadResponse;

public interface FileStorageService {
    UploadResponse store(byte[] bytes, String fileName);
}
