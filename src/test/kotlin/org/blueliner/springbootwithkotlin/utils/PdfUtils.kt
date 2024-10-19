package org.blueliner.springbootwithkotlin.utils

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import org.blueliner.springbootwithkotlin.CvScoringServiceTest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.net.URL

object PdfUtils {

    fun parsePdfToString(url: URL): String? {
        return try {
            PDDocument.load(File(url.file)).use { document ->
                PDFTextStripper().getText(document)
            }
        } catch (e: Exception) {
            println(e.message)
            null
        }
    }
}