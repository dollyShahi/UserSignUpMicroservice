package com.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.entity.Model;



public interface Repository  extends JpaRepository <Model, String> {

}
