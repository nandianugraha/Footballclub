package com.example.nandi.footballclub.data

import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ApiRepositoryTest {

    @Test
    fun doRequest() {
        val apiRepository = mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/eventsnext.php?id=133602"
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }
}