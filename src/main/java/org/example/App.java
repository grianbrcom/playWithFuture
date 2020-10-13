package org.example;

import org.example.resources.CodeTestResources;
import org.example.services.CodeParserService;
import org.example.services.CopybookService;
import org.example.services.OutlineService;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class App {
    private static Logger logger = Logger.getGlobal();
    public static void main( String[] args ) throws InterruptedException, IOException {
        LogManager.getLogManager().readConfiguration(App.class.getClassLoader().getResourceAsStream("logging.properties"));

        Thread codeParsing = new Thread(() -> CodeParserService.parseCode(new CodeTestResources("theCode", 3,
            CopybookService.CB_2_SEC_DELAY, CopybookService.CB_7_SEC_DELAY)));
        codeParsing.start();

        Thread outline = new Thread(() -> OutlineService.calcOutline("theCode"));
        outline.start();

        codeParsing.join();
        outline.join();
    }
}
