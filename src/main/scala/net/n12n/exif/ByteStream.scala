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

import java.io.InputStream

private[exif] class ByteStream(in: InputStream) extends Iterator[Int] {
  private var peek = in.read()
  override def next(): Int = if (peek != -1) {
    val b = peek
    peek = in.read()
    return b
  } else throw new IllegalStateException("Iterator reached end")

  override def hasNext = peek != -1
}
