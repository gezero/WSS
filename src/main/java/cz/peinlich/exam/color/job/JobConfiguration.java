package cz.peinlich.exam.color.job;

import cz.peinlich.exam.color.grid.Grid;
import cz.peinlich.exam.color.grid.implementation.ArrayListMatrixGridFactory;
import cz.peinlich.exam.color.rules.RuleEngine;
import cz.peinlich.exam.color.rules.RuleExecutionResult;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * User: George
 * Date: 20.5.2014
 * Time: 17:05
 */
public class JobConfiguration {

    @Autowired
    ArrayListMatrixGridFactory factory;

    @Autowired
    RuleEngine ruleEngine;

    @Bean
    public ItemReader<Grid> itemReader() {
        FlatFileItemReader<Grid> reader = new FlatFileItemReader<Grid>();
        reader.setResource(new ClassPathResource("sample-data.csv"));
        reader.setLineMapper(new LineMapper<Grid>() {
            @Override
            public Grid mapLine(String line, int i) throws Exception {
                FileInputStream inputStream = new FileInputStream(line);
                return factory.buildGridFromInputStream(inputStream, null);
            }
        });
        return reader;
    }

    @Bean
    public ItemProcessor<Grid, RuleExecutionResult> itemProcessor() {
        return new ItemProcessor<Grid, RuleExecutionResult>() {
            @Override
            public RuleExecutionResult process(Grid grid) throws Exception {
                return ruleEngine.executeRules(grid);
            }
        };
    }

    @Bean
    public ItemWriter<RuleExecutionResult> writer() {
        return new ItemWriter<RuleExecutionResult>() {

            @Override
            public void write(List<? extends RuleExecutionResult> ruleExecutionResults) throws Exception {
                for (RuleExecutionResult ruleExecutionResult : ruleExecutionResults) {
                    write(ruleExecutionResult);
                }
            }

            private void write(RuleExecutionResult ruleExecutionResult) throws IOException {
                File outputFileName = new File("output/" +ruleExecutionResult.getName());
                if (!outputFileName.exists()){
                    outputFileName.getParentFile().mkdirs();
                    outputFileName.createNewFile();
                }
                FileOutputStream outputStream = new FileOutputStream(outputFileName,false);
                ruleExecutionResult.writeResult(outputStream);
            }
        };
    }
    @Bean
    public Job calculateGrids(JobBuilderFactory jobs, Step s1) {
        return jobs.get("calculateGrids")
                .incrementer(new RunIdIncrementer())
                .flow(s1)
                .end()
                .build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Grid> reader,
                      ItemWriter<RuleExecutionResult> writer, ItemProcessor<Grid, RuleExecutionResult> processor) {
        return stepBuilderFactory.get("step1")
                .<Grid, RuleExecutionResult> chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}
