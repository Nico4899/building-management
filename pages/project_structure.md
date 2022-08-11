# BuildingManagement Repository Structure

This project is structured after the standard template, with api, infrastructure and logic.

```
BuildingManagement
    └── src
        ├── main
        | 	├── java
        |	|   └── edu.kit.tm.cm.smartcampus	
        |	|       └── buildingmanagement
        |	|           ├── api	
        |	|           |   ├── configuration
        |	|           |   |   └── converter
        |	|           |   ├── error
        |	|           |	└── controller	
        |	|           ├── logic
        |	|           |	├── model
        |	|           |	└── operations
        |   |           |       ├── filter
        |   |           |       ├── settings
        |   |           |       └── utils
        |	|			└── infrastructure
        |	|				├── connector
        |	|				|   ├── building
        |	|				|   |   └── dto
        |	|				|   └── error
        |	|				├── exceptions	
        |	|				└── database
        |	|	                ├── generator              
        |	|	                └── repository
        |	|	                    └── favorite
        | 	├── resources
        |	└── proto
        └── test
            ├── java
            |	└── edu.kit.tm.cm.smartcampus
            └── resources
```
