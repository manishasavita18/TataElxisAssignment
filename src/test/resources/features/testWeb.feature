Feature: Subscription Validation
  @executeThisScenario
  Scenario Outline: Validate subscription packages for <Country>
    Given I navigate to "https://subscribe.stctv.com/sa-en"
    When I select country "<Country>"
    Then the subscription package details for LITE should be:
      | Packagetype         | Price                | Currency             |
      | <Packagetype_Lite>  | <Price_Lite>         | <Currency_Lite>      |
    And the subscription package details for CLASSIC should be:
      | Packagetype           | Price                 | Currency          |
      | <Packagetype_Classic> | <Price_Classic>       | <Currency_Classic>|
    And the subscription package details for PREMIUM should be:
      | Packagetype           | Price                 | Currency          |
      | <Packagetype_Premium> | <Price_Premium>       | <Currency_Premium>|
    Examples:
      | Country | Packagetype_Lite | Price_Lite | Currency_Lite | Packagetype_Classic | Price_Classic | Currency_Classic |Packagetype_Premium | Price_Premium | Currency_Premium |
      | KSA     | LITE             | 15         | SAR           | CLASSIC             | 25            | SAR              |PREMIUM             |  60    | SAR       |
      | Kuwait  | LITE             | 1.2        | KWD           | CLASSIC             | 2.5           | KWD              |PREMIUM             | 4.8    | KWD       |
      | Bahrain | LITE             | 2          | BHD           | CLASSIC             | 3             | BHD              |PREMIUM             |   6    | BHD       |