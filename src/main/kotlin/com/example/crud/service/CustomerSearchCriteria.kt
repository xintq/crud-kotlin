package com.example.crud.service

data class CustomerSearchCriteria (
        var name: String = "",
        var alias: String = "",
        var industry: String = "",
        var product: String = "",
        var region: String = ""
) {

    constructor(q: String) : this() {
        name = q
        alias = q
        industry = q
        product = q
        region = q
    }

    fun isEmpty() : Boolean = name.isNullOrEmpty()
                && alias.isNullOrEmpty()
                && industry.isNullOrEmpty()
                && product.isNullOrEmpty()
                && region.isNullOrEmpty()

}
