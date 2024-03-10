package ru.avedernikov.asyncarchitecture.eventmodel.task;

import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;

public class TaskEventModelValidator {

    private static final String TASK_V1_SCHEMA_PATH = "/account/task_v1.json";
    private static final String TASK_V2_SCHEMA_PATH = "/account/task_v2.json";

    public void validateTaskV1Schema(TaskEventV1 task) {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema jsonSchema = factory.getSchema(
                TaskEventModelValidator.class.getResourceAsStream(TASK_V1_SCHEMA_PATH));
    }

    public static void validateTaskV12Schema(TaskEventV2 task) {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema jsonSchema = factory.getSchema(
                TaskEventModelValidator.class.getResourceAsStream(TASK_V2_SCHEMA_PATH));
        
    }
}
