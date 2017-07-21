package com.example.crud.domain

import org.hibernate.validator.constraints.NotBlank
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * @NotEmpty 不能为null，而且长度必须大于0
 * @NotBlank 只能作用在String上，不能为null，而且调用trim()后，长度必须大于0,即：必须有实际字符
 * @NotNull 不能为null，但可以为empty
 *
 * 在 java 中，将 validation 注解到实体类属性或者 get/set 方法上，
 * 但是在 kotlin 中，属性直接写在构造函数中，validation 注解并没有作用到属性上。
 * 解决方法：
 *   使用 @field: 标识符，field标识符只允许在属性的访问器函数内使用。
 *   它能够 validation 校验注解作用与属性。
 */

@Entity
data class Customer (
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,

        @field:NotNull(message = "客户名称不能为空")
        @field:Size(min=2, max=30, message = "客户名称长度需要在2到30个字符之内")
        var name: String = "",
        var alias: String = "",

        @field:NotBlank(message = "行业不能为空")
        var industry: String = "",

        @field:NotBlank(message = "产品不能为空")
        var product: String = "",

        @field:NotBlank(message = "区域不能为空")
        @field:Size(min = 2, message = "区域长度不小于2个字符")
        var region: String = ""
)
