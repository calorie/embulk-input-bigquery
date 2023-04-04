package org.embulk.input.bigquery;

import java.util.List;
import org.embulk.config.ConfigDiff;
import org.embulk.config.ConfigSource;
import org.embulk.config.TaskReport;
import org.embulk.config.TaskSource;
import org.embulk.spi.InputPlugin;
import org.embulk.spi.PageOutput;
import org.embulk.spi.Schema;
import org.embulk.spi.type.Types;
import org.embulk.util.config.ConfigMapper;
import org.embulk.util.config.ConfigMapperFactory;

public class BigQueryInputPlugin implements InputPlugin {
    private static final ConfigMapperFactory CONFIG_MAPPER_FACTORY = ConfigMapperFactory.builder().addDefaultModules().build();

    @Override
    public ConfigDiff resume(TaskSource taskSource, Schema schema, int taskCount, InputPlugin.Control control) {
        final ConfigDiff configDiff = CONFIG_MAPPER_FACTORY.newConfigDiff();
        return configDiff;
    }

    @Override
    public ConfigDiff guess(ConfigSource config) {
        return CONFIG_MAPPER_FACTORY.newConfigDiff();
    }

    @Override
    public void cleanup(TaskSource taskSource, Schema schema, int taskCount, List<TaskReport> successTaskReports) {
    }

    @Override
    public TaskReport run(TaskSource taskSource, Schema schema, int taskIndex, PageOutput output) {
        final TaskReport taskReport = CONFIG_MAPPER_FACTORY.newTaskReport();
        return taskReport;
    }

    @Override
    public ConfigDiff transaction(ConfigSource config, InputPlugin.Control control) {
        final ConfigMapper configMapper = CONFIG_MAPPER_FACTORY.createConfigMapper();
        final PluginTask task = configMapper.map(config, PluginTask.class);
        final Schema schema = Schema.builder().add(task.getJsonColumnName(), Types.JSON).build();
        return resume(task.toTaskSource(), schema, 1, control);
    }
}
