package com.example.crud.service

import com.example.crud.domain.Customer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CustomerService {
    fun findAll(customerSearchCriteria: CustomerSearchCriteria, pageable: Pageable) : Page<Customer>
    fun findAll(pageable: Pageable) : Page<Customer>
    fun findCustomer(id: Long): Customer
    fun saveCustomer(customer: Customer)
    fun deleteCustomer(id: Long)
    fun importCustomers(content: String) : Int
    fun count(): Int
}
