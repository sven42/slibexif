/*
 * slibexif - Scala library to parse JPEG EXIF data.
 * Copyright (C) Niklas Grossmann
 *
 * This file is part of libexif.
 *
 * slibexif is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * slibexif is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser Public License for more details.
 *
 * You should have received a copy of the GNU Lesser Public License
 * along with libexif.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.n12n.exif

case class Rational(val numerator: Long, val denominator: Long) {
  override def toString = numerator.toString + "/" + denominator
  def toDouble()        = numerator.toDouble / denominator.toDouble
}

case class SignedRational(val numerator: Int, val denominator: Int) {
  override def toString = numerator.toString + "/" + denominator
  def toDouble()        = numerator.toDouble / denominator.toDouble
}
