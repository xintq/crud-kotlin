package com.example.crud.controller

import com.example.crud.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import java.text.DateFormat
import java.util.*

/**
 * Created by kevin on 2017/7/18.
 */
@Controller
class HomeController {

    @Autowired
    lateinit var customerService: CustomerService

    @RequestMapping("/")
    fun toIndex(locale: Locale, model: Model) : String {
        var dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale)
        model.addAttribute("currentTime", dateFormat.format(Date()))
        model.addAttribute("customersCount", customerService.count())
        return "index"
    }

    @RequestMapping("/about")
    fun about() = "about"

}
