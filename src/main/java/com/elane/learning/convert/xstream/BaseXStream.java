package com.elane.learning.convert.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("service")
public class BaseXStream {

  private Company head;

  private ShipOwner djxx;
}
