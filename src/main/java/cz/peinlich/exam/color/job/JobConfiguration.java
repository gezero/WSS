package cz.peinlich.exam.color.job;

import cz.peinlich.exam.color.grid.Color;
import cz.peinlich.exam.color.grid.Grid;
import cz.peinlich.exam.color.grid.implementation.ArrayListMatrixGridFactory;
import cz.peinlich.exam.color.rules.RuleEngine;
import cz.peinlich.exam.color.rules.RuleExecutionResult;
import cz.peinlich.exam.color.rules.implementation.SimpleRuleEngine;
import cz.peinlich.exam.color.rules.implementation.rules.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * This configuration creates the job that will go throw all input files and handle them.
 * <p/>
 * User: George
 * Date: 20.5.2014
 * Time: 17:05
 */
@Configuration
@EnableBatchProcessing
public class JobConfiguration {

    @Autowired
    ArrayListMatrixGridFactory factory;

    @Value("${output-directory}")
    String outputDirectory;

    @Value("${input-file-url}")
    String inputFileName;

    @Autowired
    ApplicationContext context;

    /**
     * Item Reader checks the input-list.txt and reads each line. Lines contain path to file that should be
     * handled. Each file will be parsed and Grid will be created out of it. This grid will be later passed to
     * the processor.
     */
    @Bean
    public ItemReader<Grid> itemReader() {
        FlatFileItemReader<Grid> reader = new FlatFileItemReader<>();
        reader.setResource(context.getResource(inputFileName));
        reader.setLineMapper(new LineMapper<Grid>() {
            @Override
            public Grid mapLine(String line, int i) throws Exception {
                FileInputStream inputStream = new FileInputStream(line);
                return factory.buildGridFromInputStream(inputStream, line);
            }
        });
        return reader;
    }

    /**
     * RuleEngine executes each rule against the grid and returns result that compiles all the individual
     * results of particular rules. The Result is then passed to ItemWriter.
     */

    @Bean
    public ItemProcessor<Grid, RuleExecutionResult> itemProcessor() {
        return new ItemProcessor<Grid, RuleExecutionResult>() {
            @Override
            public RuleExecutionResult process(Grid grid) throws Exception {
                return ruleEngine().executeRules(grid);
            }
        };
    }

    /**
     * Writer creates new output file for result and writes the result into the file
     */
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
                File outputFileName = new File(outputDirectory + ruleExecutionResult.getName());
                if (!outputFileName.exists()) {
                    outputFileName.getParentFile().mkdirs();
                    outputFileName.createNewFile();
                }
                FileOutputStream outputStream = new FileOutputStream(outputFileName, false);
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
                .<Grid, RuleExecutionResult>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    /**
     * Here is the actual specification of the rules
     */
    @Bean
    public RuleEngine ruleEngine() {
        RuleEngine ruleEngine = new SimpleRuleEngine();

//        1.	Green structure always has an adjacent blue structure.
        ruleEngine.registerRule(new HasAdjacentStructureOfColor(Color.GREEN, Color.BLUE));
//        2.	Red structure has no more than one another adjacent structure.
        ruleEngine.registerRule(new HasNoMoreThanNAdjacentStructuresRule(Color.RED, 1));
//        3.	Yellow structure cannot have an adjacent green structure.
        ruleEngine.registerRule(new DoesNotHaveAdjacentStructureOfColor(Color.YELLOW, Color.GREEN));
//        4.	Red structure cannot consist from more than 5 cells.
        ruleEngine.registerRule(new HasSizeAtMostNRule(Color.RED, 5));
//        5.	Yellow structure is always linear, i.e. all cells that form the structure are on a single horizontal or vertical line.
        ruleEngine.registerRule(new IsVerticalAndHorizontal(Color.YELLOW));
//        6.	There are no more than two blue structures in the grid.
        ruleEngine.registerRule(new NoMoreThanNStructuresOfColor(Color.BLUE, 2));
//        7.	For each color, average number of cells per structure is less than 5.
        for (Color color : Color.values()) {
            if (color != Color.EMPTY) {
                ruleEngine.registerRule(new AverageAmountOfCellsInStructureLowerThanN(color, 5));
            }
        }
        return ruleEngine;
    }

}
