package org.blueliner.springbootwithkotlin.service.temporal.activity.impl

import io.temporal.spring.boot.ActivityImpl
import org.blueliner.springbootwithkotlin.service.temporal.activity.ParsePdfActivity
import org.blueliner.springbootwithkotlin.utils.PdfUtils
import org.springframework.stereotype.Component

@Component
@ActivityImpl(taskQueues = ["GenenerateAiCvResponseQueue", "GenenerateAiCvResponseBySectionQueue"])
class ParsePdfActivityImpl : ParsePdfActivity {
    override fun parsePdfActivity(file: ByteArray) = PdfUtils.parsePdfToString(file)
}