package org.example.services;

import org.example.resources.CopybookData;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class CopybookService {
  private static Logger logger = Logger.getGlobal();
  public static final String CB_2_SEC_DELAY = "CB_2_SEC_DELAY";
  public static final String CB_7_SEC_DELAY = "CB_7_SEC_DELAY";

  public static CompletableFuture<CopybookData> readCopybook(String uri) {
    switch (uri) {
      case CB_2_SEC_DELAY:
        return CompletableFuture.supplyAsync(getSupplier(2, CB_2_SEC_DELAY));
      case CB_7_SEC_DELAY:
        return CompletableFuture.supplyAsync(getSupplier(7, CB_7_SEC_DELAY));
      default:
        return CompletableFuture.completedFuture(new CopybookData("default"));
    }
  }

  private static Supplier<CopybookData> getSupplier(int timeout, String content) {
    return () -> {
      try {
        Thread.sleep(timeout * 1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      logger.info("Copybook resolved with content: " + content);
      return new CopybookData(content);
    };
  }
}
