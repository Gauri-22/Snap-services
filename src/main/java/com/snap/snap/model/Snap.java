package com.snap.snap.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "snap")
public class Snap {
   @Id
   private String id;
   private String webSnap;
   private byte[] screenshot;
   private String date;

   public Snap() {
        super();
    }

public String getId() {
    return id;
}

public void setId(String id) {
    this.id = id;
}

public String getWebSnap() {
    return webSnap;
}

public void setWebSnap(String webSnap) {
    this.webSnap = webSnap;
}

public byte[] getScreenshot() {
    return screenshot;
}

public void setScreenshot(byte[] screenshot) {
    this.screenshot = screenshot;
}

public String getDate() {
    return date;
}

public void setDate(String date) {
    this.date = date;
}
   
}
