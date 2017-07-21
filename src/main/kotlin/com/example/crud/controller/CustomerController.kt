package com.example.crud.controller

import com.example.crud.domain.Customer
import com.example.crud.service.CustomerSearchCriteria
import com.example.crud.service.CustomerService
import com.example.crud.util.PageWrapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Controller
@RequestMapping("/customer/")
class CustomerController {

    @Autowired
    lateinit var customerService: CustomerService

    @RequestMapping("all")
    fun all(model: ModelMap,
            @RequestParam(value = "page", defaultValue = "0")
            page: Int,
            @RequestParam(value = "size", defaultValue = "10")
            size: Int,
            @RequestParam(value = "q", defaultValue = "")
            q: String) : String {

        val pageWrapper = PageWrapper(
                customerService.findAll(CustomerSearchCriteria(q), PageRequest(page, size)),
                "/customer/all?q=$q")
        model.put("page", pageWrapper)
        model.put("list", pageWrapper.content)
        model.put("customer", Customer())
        model.put("q", q)
        return "/customer/index"
    }

    @RequestMapping("delete/{id}")
    fun delete(@PathVariable id: Long): String {
        customerService.deleteCustomer(id)
        return "redirect:/customer/all"
    }

    @RequestMapping("toAdd")
    fun toAdd(model: ModelMap) :String {
        model.addAttribute("customer", Customer())
        return  "/customer/add"
    }

    @GetMapping("edit/{id}")
    fun edit(model: ModelMap, @PathVariable id: Long):String {
        model.addAttribute("customer", customerService.findCustomer(id))
        return "/customer/edit"
    }

    @PostMapping("update")
    fun update(@Valid customer: Customer, bindingResult: BindingResult):String {
        if (bindingResult.hasErrors()){
            return "/customer/edit"
        }
        customerService.saveCustomer(customer)
        return "redirect:/customer/all"
    }

    @PostMapping("add")
    fun add(@Valid customer: Customer, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()){
            return "/customer/add"
        }
        customerService.saveCustomer(customer)
        return "redirect:/customer/all"
    }

    @GetMapping("toImport")
    fun toImport() = "/customer/import"

    @PostMapping("imp")
    fun imp(@RequestParam(value = "content", defaultValue = "") content: String, model: ModelMap): String {
        val importedCustomers = customerService.importCustomers(content)
        model.addAttribute("content", content)
        model.addAttribute("importedCustomers", importedCustomers)
        return "/customer/import"
    }
}
