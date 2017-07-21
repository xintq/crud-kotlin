package com.example.crud.domain

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * Created by kevin on 2017/7/19.
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class CustomerRepositoryTests {

    @Autowired
    lateinit var customerRepository : CustomerRepository

    @Test
    fun test(){

        customerRepository.deleteAll()
        Assert.assertEquals(0, customerRepository.count())

        customerRepository.save(Customer(name= "test-aaa", alias = "This is test aaa", industry = "Test", product = "Test", region = "CN"))
        customerRepository.save(Customer(name="test-bbb", alias = "This is test bbb", industry = "Test", product = "Test", region = "CN"))
        customerRepository.save(Customer(name= "test-ccc", alias = "This is test ccc", industry = "Test", product = "Test", region = "CN"))

        Assert.assertEquals(3, customerRepository.count())

        Assert.assertEquals("test-aaa", customerRepository.findByName("test-aaa")[0].name)

        customerRepository.deleteAll()
        Assert.assertEquals(0, customerRepository.count())
    }
}
