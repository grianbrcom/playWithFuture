package org.example.services;

import org.example.World;
import org.example.resources.AnalysisResult;
import org.example.resources.CodeTestResources;
import org.example.resources.CopybookData;

import java.util.logging.Logger;

public class CodeParserService {
  private static Logger logger = Logger.getGlobal();

  public static void parseCode(CodeTestResources resources) {
    logger.info("Start code parsing for '"+ resources.getUri() +"'");
    try {
      Thread.sleep(resources.getTimeWait() * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    logger.info("Code processed for '" + resources.getUri() + "'. Work on copybooks");
    for (String copybookUri: resources.getCopybooks()) {
      CopybookData copybookData = World.copybooks.get(copybookUri);
      logger.info("Copybook for uri '"+copybookUri+"' processed with content '"+copybookData.getContent()+"'");
    }
    logger.info("Parsing complete for '"+resources.getUri()+"'");
    World.codeResult.put(resources.getUri(), new AnalysisResult("result!"));
  }
}
