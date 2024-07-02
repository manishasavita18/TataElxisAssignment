Feature: Subscription Validation
  @executeThisScenario
  Scenario Outline: Validate subscription packages for <Country>
    Given I navigate to "https://subscribe.stctv.com/sa-en"
    When I select country "<Country>"
    Then I validate subscription packages with the following details
      | PackageType | Price | Currency |
      | <PackageType1> | <Price1> | <Currency1> |
      | <PackageType2> | <Price2> | <Currency2> |
      | <PackageType3> | <Price3> | <Currency3> |
    Examples:
      | Country | PackageType1 | Price1 | Currency1 | PackageType2 | Price2 | Currency2 |PackageType3 | Price3 | Currency3 |
      | KSA     | LITE         | 15     | SAR       | CLASSIC      | 25     | SAR       |Premium      |  60    | SAR       |
      | Kuwait  | Lite         | 1.2    | KWD       | Classic      | 2.5    | KWD       |Premium      | 4.8    | KWD       |
      | Bahrain | Lite         | 2      | BHD       | Classic      | 3      | BHD       |Premium      |   6    | BHD       |