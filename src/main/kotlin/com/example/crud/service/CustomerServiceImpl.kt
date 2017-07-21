package com.example.crud.service

import com.example.crud.domain.Customer
import com.example.crud.domain.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component("customerService")
@Transactional
class CustomerServiceImpl : CustomerService {


    @Autowired
    lateinit var customerRepository: CustomerRepository

    override fun findAll(customerSearchCriteria: CustomerSearchCriteria, pageable: Pageable): Page<Customer> {
        val name = customerSearchCriteria.name
        val alias = customerSearchCriteria.alias
        val industry = customerSearchCriteria.industry
        val product = customerSearchCriteria.product
        var region = customerSearchCriteria.region
        if (customerSearchCriteria.isEmpty()) {
            return customerRepository.findAll(pageable)
        } else {
            //return customerRepository.findByNameContainingOrAliasContainingAllIgnoringCase(name, alias, pageable)
            return customerRepository.findByNameContainingOrAliasContainingOrIndustryContainingOrProductContainingOrRegionContainingAllIgnoringCase(
                    name, alias, industry, product, region, pageable
            )
        }
    }

    override fun findAll(pageable: Pageable): Page<Customer> = customerRepository.findAll(pageable)

    override fun findCustomer(id: Long): Customer = customerRepository.findOne(id)

    override fun saveCustomer(customer: Customer) {
        customerRepository.save(customer)
    }

    override fun deleteCustomer(id: Long) {
        customerRepository.delete(id)
    }

    override fun importCustomers(content: String): Int {
        if (!content.isNullOrEmpty()) {
            var trimedContent = content.trimMargin()
            val customerArray = ArrayList<Customer>()
            for (line in trimedContent.lines()) {
                val fields = line.split(if (line.contains('\t')) '\t' else ',')
                println(line)
                if (fields.size != 5) return -1;
                val customer = Customer(
                        name = fields[0].trimMargin(),
                        alias = fields[1].trimMargin(),
                        industry = fields[2].trimMargin(),
                        product = fields[3].trimMargin(),
                        region = fields[4].trimMargin()
                )
                customerArray.add(customer)
            }
            return customerRepository.save(customerArray).count()
        } else {
            return 0;
        }
    }

    override fun count(): Int = customerRepository.count().toInt()
}
