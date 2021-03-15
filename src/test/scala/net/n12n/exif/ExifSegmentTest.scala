package net.n12n.exif
import org.scalatest.funsuite.AnyFunSuite

class ExifSegmentTest extends AnyFunSuite {
  val exif: ExifSegment = new JpegMetaData(classOf[ExifSegmentTest].getResourceAsStream("image-gps.jpg")).exif.get

  test("findAttr finds TiffAttribute") {
    assert(exif.findAttr(TiffIfd.XResolution).isDefined, "XResoultion not found in IFD0")
  }

  test("findAttr finds ExifAttribute") {
    assert(exif.findAttr(ExifIfd.ExifVersion).isDefined, "ExifVersion not found")
  }

  test("findAttr finds GpsAttribute") {
    assert(exif.findAttr(GpsIfd.GPSLongitude).isDefined, "GPSLongitude not found")
  }

  assertResult("A user comment", "Unicode UserComment found") {
    exif.exifIfd.get.value(ExifIfd.UserComment).value
  }
}
