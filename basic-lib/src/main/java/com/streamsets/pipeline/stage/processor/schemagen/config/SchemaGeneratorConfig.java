/**
 * Copyright 2017 StreamSets Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.streamsets.pipeline.stage.processor.schemagen.config;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.Dependency;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.stage.common.HeaderAttributeConstants;

public class SchemaGeneratorConfig {

  @ConfigDef(
    required = true,
    type = ConfigDef.Type.MODEL,
    defaultValue="AVRO",
    label = "Schema Type",
    description = "Type of schema that should be generated.",
    group = "SCHEMA",
    displayPosition = 10
  )
  @ValueChooserModel(SchemaTypeValueChooser.class)
  public SchemaType schemaType = SchemaType.AVRO;

  @ConfigDef(
    required = true,
    type = ConfigDef.Type.STRING,
    defaultValue = "",
    label = "Schema name",
    description = "Name of the schema that will be generated.",
    group = "SCHEMA",
    displayPosition = 10
  )
  public String schemaName;

  @ConfigDef(
    required = true,
    type = ConfigDef.Type.STRING,
    defaultValue = "avroSchema",
    label = "Header Attribute",
    description = "Name of the header attribute where the generated schema will be stored.",
    group = "SCHEMA",
    displayPosition = 10
  )
  public String attributeName = "avroSchema";

  // Avro specific configs

  @ConfigDef(
    required = false,
    type = ConfigDef.Type.STRING,
    label = "Namespace",
    description = "Namespace for generated schema.",
    group = "AVRO",
    dependencies = {
      @Dependency(configName = "schemaType", triggeredByValues = "AVRO")
    },
    displayPosition = 10
  )
  public String avroNamespace;

  @ConfigDef(
    required = false,
    type = ConfigDef.Type.STRING,
    label = "Doc",
    description = "Documentation string that will be stored in the generated schema.",
    group = "AVRO",
    dependencies = {
      @Dependency(configName = "schemaType", triggeredByValues = "AVRO")
    },
    displayPosition = 20
  )
  public String avroDoc;

  @ConfigDef(
    required = true,
    type = ConfigDef.Type.BOOLEAN,
    defaultValue = "true",
    label = "Nullable fields",
    description = "If set to true all schema fields will be created as unions allowing null values.",
    group = "AVRO",
    dependencies = {
      @Dependency(configName = "schemaType", triggeredByValues = "AVRO")
    },
    displayPosition = 30
  )
  public boolean avroNullableFields;

  @ConfigDef(
    required = true,
    type = ConfigDef.Type.BOOLEAN,
    defaultValue = "false",
    label = "Expand Types",
    description = "For SDC types that Avro does not have direct equivalent, expand them to the closest 'bigger' equivalent.",
    group = "AVRO",
    dependencies = {
      @Dependency(configName = "schemaType", triggeredByValues = "AVRO")
    },
    displayPosition = 40
  )
  public boolean avroExpandTypes;

  // Specific type configs

  @ConfigDef(
    required = true,
    type = ConfigDef.Type.STRING,
    defaultValue = HeaderAttributeConstants.ATTR_PRECISION,
    label = "Precision Field Attribute",
    description = "Name of the field attribute that stores precision for decimal fields.",
    group = "TYPES",
    displayPosition = 10
  )
  public String precisionAttribute = HeaderAttributeConstants.ATTR_PRECISION;

  @ConfigDef(
    required = true,
    type = ConfigDef.Type.STRING,
    defaultValue = HeaderAttributeConstants.ATTR_SCALE,
    label = "Scale Field Attribute",
    description = "Name of the field attribute that stores scale for decimal fields.",
    group = "TYPES",
    displayPosition = 20
  )
  public String scaleAttribute = HeaderAttributeConstants.ATTR_SCALE;

  @ConfigDef(
    required = false,
    type = ConfigDef.Type.NUMBER,
    defaultValue = "-1",
    label = "Default Precision",
    description = "Default precision when given field attribute does not exists or is invalid. Use -1 to disable default value.",
    group = "TYPES",
    displayPosition = 30
  )
  public int defaultPrecision;

  @ConfigDef(
    required = false,
    type = ConfigDef.Type.NUMBER,
    label = "Default Scale",
    defaultValue = "-1",
    description = "Default scale when given field attribute does not exists or is invalid. Use -1 to disable default value.",
    group = "TYPES",
    displayPosition = 30
  )
  public int defaultScale;

}
