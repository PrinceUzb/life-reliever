package lifereliever.utils

import scala.annotation.implicitNotFound

import derevo.Derivation
import derevo.NewTypeDerivation

object configDecoder extends Derivation[Decoder.Id] with NewTypeDerivation[Decoder.Id] {
  def instance(implicit ev: OnlyNewtypes): Nothing = ev.absurd

  @implicitNotFound("Only newtypes instances can be derived")
  final abstract class OnlyNewtypes {
    def absurd: Nothing = ???
  }
}
