package sycho.spring.basic.utils;

import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Base64;

public class Base64FileUtils {
    static public MultipartFile base64ToMultipartFile(String filename, String base64Str) {

        Assert.notNull(filename, "'filename' must not be null!");
        Assert.notNull(base64Str, "'base64Str' must not be null!");

        var delimiter = "[,]";

        var imageString = "";

        if (base64Str.contains("data:image")) {
            var parts = base64Str.split(delimiter);
            imageString = parts[1];
        } else {
            imageString = base64Str;
        }

        var decodedBytes = Base64.getDecoder().decode(imageString);

        var fullName = filename + "." + extractExtension(decodedBytes);

        return new MultipartFile() {
            @Override
            public String getName() {
                return fullName;
            }

            @Override
            public String getOriginalFilename() {
                return fullName;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return decodedBytes == null || decodedBytes.length == 0;
            }

            @Override
            public long getSize() {
                return decodedBytes.length;
            }

            @Override
            public byte[] getBytes() {
                return decodedBytes;
            }

            @Override
            public InputStream getInputStream() {
                return new ByteArrayInputStream(decodedBytes);
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                FileCopyUtils.copy(this.getInputStream(), Files.newOutputStream(dest.toPath()));
            }
        };
    }

    static private String extractExtension(byte[] imageByteArray) {
        InputStream is = new ByteArrayInputStream(imageByteArray);

        String mimeType;
        String fileExtension;

        try {
            mimeType = URLConnection.guessContentTypeFromStream(is);
            var delimiter = "[/]";
            var tokens = mimeType.split(delimiter);
            fileExtension = tokens[1];

            return fileExtension;
        } catch (IOException e) {
            e.printStackTrace();
            return "jpg";
        }
    }
}
