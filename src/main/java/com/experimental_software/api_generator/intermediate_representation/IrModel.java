package com.experimental_software.api_generator.intermediate_representation;

import java.io.File;

import lombok.Builder;

@Builder
public record IrModel(String packageName, String json, File path) {
}
