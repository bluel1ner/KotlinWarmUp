package org.blueliner.springbootwithkotlin

import com.fasterxml.jackson.databind.ObjectMapper
import org.blueliner.springbootwithkotlin.dto.response.CvScoringResponse
import org.blueliner.springbootwithkotlin.dto.response.OpenAiResponse
import org.blueliner.springbootwithkotlin.service.AiClientService
import org.blueliner.springbootwithkotlin.utils.PdfUtils
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.net.URL
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@SpringBootTest
class CvScoringServiceTest {

    @Autowired
    lateinit var aiClientService: AiClientService
    val objectMapper = ObjectMapper()

    companion object {
        private val log: Logger = LoggerFactory.getLogger(CvScoringServiceTest::class.java)
    }

    @Test
    fun testAiServiceReturnNonNullResponse() {
        val resource: URL = javaClass.classLoader.getResource("cv-template/non-cv-template.pdf")
        val pdfPayload = PdfUtils.parsePdfToString(resource)

        val message: String =
            "Hey! I have a cv and you should score this resume by range from 0 to 100 and respond in json" +
                " with 'name', 'title' and 'score' and give 'explanation'. Cv attached below " + pdfPayload;
        val cvScoringString = aiClientService.getCvScoring(message)
        val cvScoringResponse: CvScoringResponse =
            objectMapper.readValue(cvScoringString, CvScoringResponse::class.java)
        log.info("response ${cvScoringResponse}")
        assertNotNull(cvScoringResponse)
    }

    @Test
    fun testConvertPdfToStringWithPositiveResult() {
        val resource: URL? = javaClass.classLoader.getResource("cv-template/second-cv-template.pdf")
        resource?.let {
            assertNotNull(PdfUtils.parsePdfToString(resource))
        }
    }

    @Test
    fun testForCorrectHandlePotentialNullInChain() {
        val body: OpenAiResponse? = null
        val response = body?.choices?.firstOrNull()?.message?.content
        assertNull(response)
    }

}
