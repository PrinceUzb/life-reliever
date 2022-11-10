package lifereliever.exception

abstract class GenericError extends Throwable {
  def cause: String
  override def getMessage: String = cause
}
