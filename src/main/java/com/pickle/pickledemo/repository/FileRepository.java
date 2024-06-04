package com.pickle.pickledemo.repository;

import com.pickle.pickledemo.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FileRepository extends JpaRepository<File, Integer> {

}
