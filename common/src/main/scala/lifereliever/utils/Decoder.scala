package lifereliever.utils

import ciris.ConfigDecoder
object Decoder {
  type Id[A] = ConfigDecoder[String, A]
}
