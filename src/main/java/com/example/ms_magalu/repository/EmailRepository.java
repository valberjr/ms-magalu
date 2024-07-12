package com.example.ms_magalu.repository;

import com.example.ms_magalu.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
