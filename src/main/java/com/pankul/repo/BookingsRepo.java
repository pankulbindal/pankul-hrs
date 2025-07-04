package com.pankul.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pankul.models.BookingModel;

@Repository
public interface BookingsRepo extends JpaRepository<BookingModel, Long>{

}
