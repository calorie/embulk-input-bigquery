package org.embulk.input.bigquery;

import org.embulk.util.config.Config;
import org.embulk.util.config.ConfigDefault;
import org.embulk.util.config.Task;

public interface PluginTask extends Task {
    @Config("json_column_name")
    @ConfigDefault("\"record\"")
    String getJsonColumnName();
}
