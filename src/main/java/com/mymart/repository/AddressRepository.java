package com.mymart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymart.model.Address;
import com.mymart.model.User;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	Address findDefaultAddressByUser(User user);

}
