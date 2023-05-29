package com.experimental_software.intermediate_representation;

import java.util.List;

record Function(
    String meaning,
    String name,
    List<Parameter> parameters,
    String return_type
) {}

record Parameter(
    String name,
    String type
) {}
