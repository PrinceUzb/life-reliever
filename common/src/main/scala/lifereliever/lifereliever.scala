import eu.timepit.refined.W
import eu.timepit.refined.api.Refined
import eu.timepit.refined.string._

package object lifereliever {
  type Phone = String Refined MatchesRegex[W.`"""[+][\\d]+"""`.T]
  type EmailAddress =
    String Refined MatchesRegex[W.`"[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+[.][a-zA-Z]{2,}"`.T]
  type OnlyDigits = String Refined MatchesRegex[W.`"""^\\d{1,}$"""`.T]
  type UriAddress = String Refined Uri
}
