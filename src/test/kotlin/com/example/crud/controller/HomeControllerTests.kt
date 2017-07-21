package com.example.crud.controller

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

/**
 * Created by kevin on 2017/7/19.
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class HomeControllerTests {

    lateinit var mvc: MockMvc

    @Before
    fun setup() {
        mvc = MockMvcBuilders.standaloneSetup(HomeController()).build()
    }

    @Test
    fun getHome() {
        mvc.perform(
                MockMvcRequestBuilders.get("/")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
    }
}