package com.pickle.pickledemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "file_name")
    @NotNull(message = "is required")
    private String fileName;


    @Column(name = "file_type")
    @NotNull(message = "is required")
    private String fileType;


    @Column(name = "file_path")
    @NotNull(message = "is required")
    private String filePath;


}
