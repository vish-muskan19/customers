package com.example.Service;

import java.util.List;

import com.example.Entity.Customer;
import com.example.Entity.User;

public interface CustomerService {
	
	public Customer createCustomer(Customer customer, User user);
	
	public Customer findCustomerById(Long id) throws Exception;
	
	public void deleteCustomer(Long id)throws Exception;
	
	public Customer updateCustomer(Customer customer, Long Id)throws Exception;

	public List<Customer>findAllCustomer();

}
