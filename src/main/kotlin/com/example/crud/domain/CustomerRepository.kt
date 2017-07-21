package com.example.crud.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by kevin on 2017/7/18.
 */
interface CustomerRepository : PagingAndSortingRepository<Customer, Long> {
    fun findByName(name: String): List<Customer>
    fun findByAlias(alias: String): List<Customer>
    fun findByNameContainingOrAliasContainingAllIgnoringCase(name: String,
                                                             alias: String,
                                                             pageable: Pageable) : Page<Customer>

    fun findByNameContainingOrAliasContainingOrIndustryContainingOrProductContainingOrRegionContainingAllIgnoringCase(
            name: String,
            alias: String,
            industry: String,
            product: String,
            region: String,
            pageable: Pageable
    ) : Page<Customer>
}
