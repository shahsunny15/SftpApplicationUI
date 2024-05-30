package com.hshah.sftpapplication.controller;

import com.hshah.sftp.service.SftpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserController {

    private final SftpService sftpService;

    public UserController(SftpService sftpService) {
        this.sftpService = sftpService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/upload")
    public String uploadForm(Model model) {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadFile(MultipartFile file, Model model) {
        boolean isSuccess = sftpService.uploadFile(file);
        model.addAttribute("message", isSuccess ? "File uploaded successfully!" : "File upload failed!");
        return "upload";
    }
}