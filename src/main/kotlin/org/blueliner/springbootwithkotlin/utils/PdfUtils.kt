package org.blueliner.springbootwithkotlin.utils

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import org.springframework.web.multipart.MultipartFile

object PdfUtils {

    fun parsePdfToString(file: MultipartFile): String {
        return PDDocument.load(file.bytes).use { document ->
            PDFTextStripper().getText(document)
        }
    }
}