package harsh.SftpApplication.service;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.Properties;

@Service
public class SftpService {

    public boolean uploadFile(MultipartFile file) {
        String remoteHost = "your-remote-host";
        String username = "your-username";
        String privateKeyPath = "path-to-your-private-key";

        Session session = null;
        ChannelSftp channelSftp = null;

        try {
            JSch jsch = new JSch();
            jsch.addIdentity(privateKeyPath);
            session = jsch.getSession(username, remoteHost, 22);

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect();
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            channelSftp.put(new ByteArrayInputStream(file.getBytes()), "/remote/path/" + file.getOriginalFilename());

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (channelSftp != null) {
                channelSftp.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        }
    }
}