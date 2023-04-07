package com.dh.catalogservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Catalog")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Movie {
    @Id
    String id;
    String name;
    String genre;
    String urlStream;


}
