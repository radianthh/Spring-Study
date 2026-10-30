package springprac.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import springprac.springbootdeveloper.dto.UploadResponse;

import java.util.UUID;

@Profile("!local")
@RequiredArgsConstructor
@Service
public class AwsS3Service implements FileStorageService {

    private final S3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public UploadResponse store(byte[] bytes, String filename) {
        String ext = filename.contains(".")
                ? filename.substring(filename.lastIndexOf(".") + 1).toLowerCase() : "png";

        String key = "uploads/" + UUID.randomUUID() + "." + ext;

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .contentType("image/" + ext)
                .build();
        s3Client.putObject(request, RequestBody.fromBytes(bytes));

        String url = s3Client.utilities()
                .getUrl(builder -> builder.bucket(bucket).key(key))
                .toExternalForm();

        return new UploadResponse(url);
    }
}
