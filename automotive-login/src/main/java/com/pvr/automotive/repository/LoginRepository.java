package com.pvr.automotive.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pvr.automotive.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer>{

	Optional <Login> findByEmail(String email);
	

}
