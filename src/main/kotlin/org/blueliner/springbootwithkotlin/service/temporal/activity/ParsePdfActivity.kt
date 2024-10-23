package org.blueliner.springbootwithkotlin.service.temporal.activity

import io.temporal.activity.ActivityInterface

@ActivityInterface
interface ParsePdfActivity {

    fun parsePdfActivity(file: ByteArray) : String
}