package org.example.resources;

import java.util.List;

public class CodeTestResources {
  private final String uri;
  private final int timeWait;
  private final List<String> copybooks;

  public CodeTestResources(String uri, int timeWait, String... copybooks) {
    this.uri = uri;
    this.timeWait = timeWait;
    this.copybooks = List.of(copybooks);
  }

  public String getUri() {
    return uri;
  }

  public int getTimeWait() {
    return timeWait;
  }

  public List<String> getCopybooks() {
    return copybooks;
  }
}
