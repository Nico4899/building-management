# BuildingManagement Class Diagram

This class diagram is split into three parts: building, controller and filter. The building, which is being described more precisely in [Building](https://git.scc.kit.edu/cm-tm/cm-team/3.projectwork/pse/domain/building/-/blob/dev/pages/bounded_context_entity_relation_view.md), contains the construction of a building with its rooms, components and notifications. The controllers are there to handle API requests and control change of the building. Filters are units which filter a collection of objects of the building construction. Everything is being controlled by the BuildingManager unit.

![BuildingManagement Class Diagram](../figures/class_diagram/building_management_class_diagram.png)

A description of the identification number called "in" in this diagram can be found in the [Ubiquitous Language](https://git.scc.kit.edu/-/ide/project/cm-tm/cm-team/3.projectwork/pse/docsc/tree/english-translation/-/pages/ubiquitous_language.md/).
