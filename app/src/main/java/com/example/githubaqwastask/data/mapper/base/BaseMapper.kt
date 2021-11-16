package com.example.githubaqwastask.data.mapper.base

interface BaseMapper<ResponseType, ResultType> {

    fun transform(type:ResponseType?): ResultType?
}