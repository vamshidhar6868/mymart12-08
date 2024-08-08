package com.mymart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymart.model.Address;
import com.mymart.model.User;
import com.mymart.repository.AddressRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address getAddressById(int id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + id));
    }

	public void saveAddress(Address address) {
        addressRepository.save(address);
    }

	public Address getDefaultAddressForUser(User user) {
        // Implement your logic to fetch the default address for the user
        // For example, you might fetch the address marked as default in the database
        // This is just an example, adjust the logic based on your actual requirements
        return addressRepository.findDefaultAddressByUser(user);
    }

	public Address findById(int id) {
        return addressRepository.findById(id).orElse(null);
    }

	public void deleteAddressById(int id) {
        addressRepository.deleteById(id);
		
	}

	

//	public List<Address> findById(int id) {
//		// TODO Auto-generated method stub
//		return (List<Address>) addressRepository.findById(id).orElse(null);
//	}
}
