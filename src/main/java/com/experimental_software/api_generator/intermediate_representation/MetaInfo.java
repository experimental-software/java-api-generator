package com.experimental_software.api_generator.intermediate_representation;

import java.util.List;

record MetaInfo(
    List<String> base_types,
    String classifier,
    String description,
    String name
) {}
