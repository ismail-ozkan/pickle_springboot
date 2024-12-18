package com.pickle.pickledemo.repository;

import com.pickle.pickledemo.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.List;


public interface FileRepository extends JpaRepository<File, Integer> {

    List<File> getFilesByFileName(@NotNull(message = "is required") String fileName);
}
