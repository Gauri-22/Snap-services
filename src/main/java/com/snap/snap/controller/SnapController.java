package com.snap.snap.controller;

import java.io.IOException;
import java.io.OutputStream;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.codec.ByteArrayDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
// import org.springframework.mock.web.MockMultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snap.snap.model.Snap;
import com.snap.snap.repository.SnapRepository;
import com.snap.snap.services.FileService;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.awt.Dimension;
import javax.imageio.ImageIO;
import javax.swing.text.html.ImageView;

@RestController
@CrossOrigin(origins = "*")
public class SnapController {

    @Autowired
    private SnapRepository repository;

    @Autowired
    private FileService fileService;


@RequestMapping(path = "/snap", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
public ResponseEntity<Object> insertsnap(@RequestPart String snap,
        @RequestPart(value = "image", required = false) MultipartFile image
        ) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    Snap snaps = mapper.readValue(snap, Snap.class);

    if (image != null) {
        String imgID = fileService.addFile(image);
        snaps.setWebSnap(imgID);
    }

    String path= "C://ToolScreenshot//ScreenShot.jpg";
    try {
        Thread.sleep(0);
        Robot robotObj = new Robot();
        Rectangle rectObj = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage img = robotObj.createScreenCapture(rectObj);
        ImageIO.write(img, "jpg", new File(path));
        System.out.println("Screenshot Saved.");
        System.out.println(img); 
        // snaps.setScreenshot(img);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", baos);
        byte[] screenshot = baos.toByteArray();
        snaps.setScreenshot(screenshot);

        //creates a string from the byte array without specifying character encoding  
        // String s = new String(screenshot); 
        
        // MemoryStream ms = new MemoryStream(buffer);
        // Bitmap bmp=new Bitmap(ms);
        // String screenshotID = fileService.addFile(screenshot);
        // snaps.setScreenshot(outputfile);

        // ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // ImageIO.write(img, "jpg", new File("ScreenShot.jpg"));
        // MultipartFile multipartFile = new MockMultipartFile(s, baos.toByteArray());


        // String screenshotID = fileService.addFile(multipartFile);
        // snaps.setWebSnap(screenshotID);
              
    } catch (AWTException | IOException  | InterruptedException ioe) {
        System.out.println(ioe);
    }
    
    repository.insert(snaps);
    return new ResponseEntity<>(HttpStatus.CREATED);
}


}
