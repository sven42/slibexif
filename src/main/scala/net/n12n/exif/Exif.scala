package net.n12n.exif

import java.io.IOException
import scala.util.{Failure, Success, Try}

/** Simple command-line tool to view Exif segment content.
  */
object Exif extends App {
  try {
    if (args.length != 1)
      usage()

    JpegMetaData(args(0)).exif.foreach(exif => {
      exif.ifds
        .flatMap(_.attributes)
        .foreach(attr => {
          val valueOrExceptionMsg = Try(attr.value) match {
            case Failure(exception) => "Exception: " + exception.getMessage
            case Success(value)     => value
          }
          println(s"${attr.tag}: $valueOrExceptionMsg")
        })
      exif.ifds.flatMap(_.attributeExceptions).foreach(t => println(t.getMessage()))
    })
  } catch {
    case e: IOException =>
      System.err.println(s"Failed to open ${args(0)} for reading")
      System.exit(2)
  }

  private def usage(): Unit = {
    System.err.println("Usage: Exif <path>")
    System.exit(1)
  }
}
