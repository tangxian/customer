package com.boot.core.kernel_generator.executor;

import com.boot.core.kernel_generator.executor.config.GunsGeneratorConfig;


public class GunsCodeGenerator {
    public static void main(String[] args) {
        GunsGeneratorConfig gunsGeneratorConfig = new GunsGeneratorConfig();
        gunsGeneratorConfig.doMpGeneration();


        gunsGeneratorConfig.doGunsGeneration();
    }
}
