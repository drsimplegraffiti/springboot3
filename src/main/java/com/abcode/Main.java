package com.abcode;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/api/v1/customers")
public class Main {
    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/")
    public GreetResponse health() {



        GreetResponse response =  new GreetResponse(
                "Hello World!",
                List.of("Spring Boot", "Java"),
                new Author("Joseph", 1.0)
                );
        return response;
    }

    record Author(String name, double version) {

    }
    record GreetResponse(
            String message,
            List<String> techStacks,
            Author author
            ) {} // record class is a new feature in Java 14 for immutable classes

    @GetMapping("")
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    record NewCustomerRequest(String name, String email, Integer age) {}

    @PostMapping("")
    public void addCustomer(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);

    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id) {
        customerRepository.deleteById(id);
    }

    @PutMapping("/{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id, @RequestBody NewCustomerRequest request) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }

    @GetMapping("/{customerId}")
    public Customer getCustomer(@PathVariable("customerId") Integer id) {
        return customerRepository.findById(id).orElseThrow();
    }
}
