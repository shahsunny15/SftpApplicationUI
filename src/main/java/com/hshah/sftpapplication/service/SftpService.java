package com.hshah.sftpapplication.service;

import org.apache.commons.net.sftp.SFTPClient;
import org.apache.commons.net.sftp.SFTPFileTransfer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class SftpService {

    public boolean uploadFile(MultipartFile file) {
        String remoteHost = "your-remote-host";
        String username = "your-username";
        String privateKeyPath = "path-to-your-private-key";

        try (SFTPClient client = new SFTPClient()) {
            client.connect(remoteHost, username, privateKeyPath);

            SFTPFileTransfer fileTransfer = client.getFileTransfer();
            fileTransfer.upload(new ByteArrayInputStream(file.getBytes()), "/remote/path/" + file.getOriginalFilename());

            client.disconnect();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
