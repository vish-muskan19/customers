package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.Customer;
import com.example.Entity.User;
import com.example.Service.CustomerService;
import com.example.Service.UserService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping()
	public Customer createCustomer(@RequestBody Customer customer, @RequestHeader("Authorization") String jwt) throws Exception {
		
		User user = userService.findUserByJwt(jwt);
		
		Customer createdCustomer = customerService.createCustomer(customer, user);
		return createdCustomer;
	}
	
	@PutMapping("/{id}")
	public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Long id) throws Exception {
		
		Customer updatecCustomer = customerService.updateCustomer(customer, id);
		return updatecCustomer;
	}
	
	@GetMapping()
	public List<Customer> getAllCustomers() throws Exception{
		List<Customer> customers = customerService.findAllCustomer();
		return customers;
	}
	
	@DeleteMapping("/{customerId}")
	public String deleteCustomer(@PathVariable Long customerId)throws Exception{
		customerService.deleteCustomer(customerId);
		return "Customer deleted successfully";
	}

}
