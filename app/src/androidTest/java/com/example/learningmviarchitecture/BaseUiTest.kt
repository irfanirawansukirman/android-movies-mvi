package com.example.learningmviarchitecture

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

abstract class BaseUiTest {

    private val mockWebServer: MockWebServer? = null

    @Before
    fun setup() {
        mockWebServer?.start(8080)
    }

    @After
    fun teardown() {
        mockWebServer?.shutdown()
    }

    fun mockSuccess(code: Int, responseName: String) {
        mockWebServer?.enqueue(
            MockResponse()
                .setResponseCode(code)
                .setBody(getStringFromFile(getTargetContext(), responseName))
        )
    }

    fun mockFailed(code: Int, responseName: String) {
        mockWebServer?.enqueue(
            MockResponse()
                .setResponseCode(code)
                .setBody(responseName)
        )
    }

    protected open fun getTargetContext(): Context {
        return InstrumentationRegistry.getInstrumentation().targetContext
    }
}