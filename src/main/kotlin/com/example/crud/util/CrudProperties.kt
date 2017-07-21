package com.example.crud.util

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
/**
 * Created by kevin on 2017/7/19.
 */
class CrudProperties {
    @Value("\${com.example.title}")
    var title: String = ""
    @Value("\${com.example.description}")
    var description: String = ""
}
