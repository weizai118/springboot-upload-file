package com.gf.controller;

import com.gf.storage.StorageFileNotFountException;
import com.gf.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Path;
import java.util.stream.Collectors;

/**
 * <p>Title: FileUploadController</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author guofu
 * @version 1.0
 * @date 2018-04-10 12:09
 */
@Controller
public class FileUploadController {

    @Autowired
    private StorageService storageService;

    @GetMapping("/")
    public String listUploadFiles(Model model){
        model.addAttribute( "files" , storageService
                .loadAll()
                .map( path -> MvcUriComponentsBuilder
                        .fromMethodName( FileUploadController.class , "serveFile" , path.getFileName().toString() )
                        .build().toString())
                .collect( Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename){
        Resource file = storageService.loadAsResource( filename );
        return ResponseEntity
                .ok()
                .header( HttpHeaders.CONTENT_DISPOSITION , "attachment; filename=\""+file.getFilename()+"\"" )
                .body( file );
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file")MultipartFile file , RedirectAttributes redirectAttributes){
        storageService.store( file );
        redirectAttributes.addAttribute( "message" , "You successfully upload " + file.getOriginalFilename() + "!" );

        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFountException.class)
    public ResponseEntity handleStorageFileNotFount(StorageFileNotFountException e){
        return ResponseEntity.notFound().build();
    }




}
