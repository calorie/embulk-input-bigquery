package org.embulk.input.bigquery;

import org.embulk.util.config.Config;
import org.embulk.util.config.ConfigDefault;
import org.embulk.util.config.Task;
import org.embulk.util.config.units.SchemaConfig;

public interface PluginTask extends Task {
    @Config("sql")
    String getSql();

    @Config("columns")
    SchemaConfig getSchemaConfig();

    @Config("json_column_name")
    @ConfigDefault("\"record\"")
    String getJsonColumnName();
}
