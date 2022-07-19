package com.carrental.CarrentalAPI.repository;

import com.carrental.CarrentalAPI.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
