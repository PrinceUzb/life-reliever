package lifereliever

package object syntax {
  object all
      extends RefinedSyntax
         with GenericSyntax
         with CirceSyntax
         with JavaTimeSyntax
         with OptionSyntax
         with ConfigDecoderSyntax
         with ListSyntax

  object generic extends GenericSyntax
  object circe extends CirceSyntax
  object javaTime extends JavaTimeSyntax
  object refined extends RefinedSyntax
  object option extends OptionSyntax
  object config extends ConfigDecoderSyntax
  object list extends ListSyntax
}
