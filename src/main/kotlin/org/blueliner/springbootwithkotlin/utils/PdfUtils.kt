package org.blueliner.springbootwithkotlin.utils

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

object PdfUtils {

    fun parsePdfToString(file: ByteArray): String {
        return PDDocument.load(file).use { document ->
            PDFTextStripper().getText(document)
        }
    }
}