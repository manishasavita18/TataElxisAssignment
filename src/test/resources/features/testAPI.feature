Feature: Adding a device using the API endpoint
  Scenario Outline: Successfully add a new device
    Given the base URI is "https://api.restful-api.dev/objects"
    When I send a POST request to "https://api.restful-api.dev/objects" with the following payload:
      """
      {
          "name": "Apple Max Pro 1TB",
          "data": {
              "year": 2023,
              "price": 7999.99,
              "CPU model": "Apple ARM A7",
              "Hard disk size": "1 TB"
                    }
      }
      """
    Then the response status code should be <expectedStatusCode>
    And the response body should contain:
      | name            | <expectedName>                     |
      | data.year       | <expectedYear>                     |
      | data.price      | <expectedPrice>                    |
      | data.CPU model  | <expectedCpuModel>                 |
      | data.Hard disk size | <expectedHardDiskSize>          |
    And the "id" and "createdAt" fields should not be null
    Examples:
      | expectedStatusCode | expectedName      | expectedYear | expectedPrice | expectedCpuModel | expectedHardDiskSize |
      | 200                | Apple Max Pro 1TB | 2023         | 7999.99       | Apple ARM A7     | 1 TB                 |