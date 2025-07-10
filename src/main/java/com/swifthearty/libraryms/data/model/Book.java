package com.swifthearty.libraryms.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Book {
   private String id;
   private String title;
   private String author;
   @Indexed(unique = true)
   private String isbn;
   private String type;
   private String language;
   private boolean available;
   private int quantity;
   private boolean borrowed;
   private boolean returned;
}
