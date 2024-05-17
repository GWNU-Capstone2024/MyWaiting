package com.mascari4615.mwaiting.restaurant.repository;

import com.mascari4615.mwaiting.restaurant.repository.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findById(long id);
}
