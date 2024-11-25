//package com.vinhuni.booking.service.storage;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//@Service
//public class FileSystem implements StorageService{
//    private final Path root;
//    public FileSystem() {
//        this.root =  Paths.get("src/main/resources/static/uploads");
//    }
//    @Override
//    public void store(MultipartFile file) {
//        try{
//            Path destination = this.root.resolve(
//                    Paths.get(file.getOriginalFilename())
//            ).normalize().toAbsolutePath();
//            try(InputStream is = file.getInputStream()) {
//                Files.copy(is, destination,
//                StandardCopyOption.REPLACE_EXISTING);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//    @Override
//    public void init(){
//        try{
//            Files.createDirectories(root);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
