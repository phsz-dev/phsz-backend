package com.phsz.userservice.userserviceprovider.repository;

import com.phsz.userservice.userserviceprovider.pojo.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {

}
