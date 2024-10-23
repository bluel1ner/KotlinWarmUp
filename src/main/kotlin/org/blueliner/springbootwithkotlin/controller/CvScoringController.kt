package org.blueliner.springbootwithkotlin.controller

import org.blueliner.springbootwithkotlin.config.AiModelProperties
import org.blueliner.springbootwithkotlin.dto.response.CvScoringBySectionResponse
import org.blueliner.springbootwithkotlin.dto.response.CvScoringResponse
import org.blueliner.springbootwithkotlin.service.CvScoringService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/ai")
class CvScoringController {

    @Autowired
    lateinit var cvScoringService: CvScoringService

    @PostMapping("/cv-scoring")
    fun getCommonCvScoring(@RequestPart("file") file: MultipartFile): CvScoringResponse {
        return cvScoringService.getCommonCvScoring(file)
    }

    @PostMapping("/section-cv-scoring")
    fun getCvScoringBySection(@RequestPart("file") file: MultipartFile): CvScoringBySectionResponse {
        return cvScoringService.getCvScoringBySection(file)
    }

}