package lifereliever.support.mailer.data

import lifereliever.EmailAddress
import lifereliever.support.mailer.data.types.Password

case class Credentials(user: EmailAddress, password: Password)
