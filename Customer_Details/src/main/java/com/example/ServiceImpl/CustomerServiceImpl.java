package com.example.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Customer;
import com.example.Entity.User;
import com.example.Repository.CustomerRepository;
import com.example.Service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer createCustomer(Customer customer, User user) {
		
		Customer cretadCustomer = new Customer();
		cretadCustomer.setFirstName(customer.getFirstName());
		cretadCustomer.setLastName(customer.getLastName());
		cretadCustomer.setAddress(customer.getAddress());
		cretadCustomer.setStreet(customer.getStreet());
		cretadCustomer.setState(customer.getState());
		cretadCustomer.setCity(customer.getCity());
		cretadCustomer.setEmail(customer.getEmail());
		cretadCustomer.setPhone(customer.getPhone());
		cretadCustomer.setImage(customer.getImage());
		cretadCustomer.setUser(user);
		cretadCustomer.setCreateAt(LocalDateTime.now());
		
		return customerRepository.save(cretadCustomer);
	}

	@Override
	public Customer findCustomerById(Long id) throws Exception {
		Optional<Customer> opt = customerRepository.findById(id);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new Exception("Customer not found with id "+ id);
	}

	@Override
	public void deleteCustomer(Long id) throws Exception {
		findCustomerById(id);
		customerRepository.deleteById(id);
		
	}

	@Override
	public Customer updateCustomer(Customer customer, Long id) throws Exception {
		Customer oldCustomer = findCustomerById(id);
		
		if(customer.getFirstName()!=null) {
			oldCustomer.setFirstName(customer.getFirstName());
		}
		if(customer.getLastName()!=null) {
			oldCustomer.setLastName(customer.getLastName());
		}
		if(customer.getAddress()!=null) {
			oldCustomer.setAddress(customer.getAddress());
		}
		if(customer.getStreet()!=null) {
			oldCustomer.setStreet(customer.getStreet());
		}
		if(customer.getState()!=null) {
			oldCustomer.setState(customer.getState());
		}
		if(customer.getCity()!=null) {
			oldCustomer.setCity(customer.getCity());
		}
		if(customer.getEmail()!=null) {
			oldCustomer.setEmail(customer.getEmail());
		}
		if(customer.getPhone()!=null) {
			oldCustomer.setPhone(customer.getPhone());
		}
		if(customer.getImage()!=null) {
			oldCustomer.setImage(customer.getImage());
		}
		

		return customerRepository.save(oldCustomer);
	}

	@Override
	public List<Customer> findAllCustomer() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

}
