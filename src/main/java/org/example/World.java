package org.example;

import org.example.resources.AnalysisResult;
import org.example.resources.CopybookData;
import org.example.resources.ResourceManager;
import org.example.services.CopybookService;

import java.util.concurrent.TimeUnit;

public class World {
  public static final ResourceManager<String, AnalysisResult> codeResult =
      new ResourceManager<>(new AnalysisResult("empty"), 30, TimeUnit.SECONDS);

  public static final ResourceManager<String, CopybookData> copybooks =
      new ResourceManager<>(new CopybookData("nothing"), 5, TimeUnit.SECONDS, CopybookService::readCopybook);
}
