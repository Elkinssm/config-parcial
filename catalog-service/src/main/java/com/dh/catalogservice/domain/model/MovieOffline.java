package com.dh.catalogservice.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "Movies")
public class MovieOffline {
    @Id
    String id;
    String name;
    String genre;
    String urlStream;

}
