package org.example.services;

import org.example.World;
import org.example.resources.AnalysisResult;

import java.util.logging.Logger;

public class OutlineService {
  private static Logger logger = Logger.getGlobal();

  public static void calcOutline(String uri) {
    logger.info("Start outline calc for '"+uri+"'");
    AnalysisResult result = World.codeResult.get(uri);
    logger.info("outline done from result '" + result.getResult() + "'");
  }
}
