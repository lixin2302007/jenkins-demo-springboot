package com.elane.learning.aynsc;

import java.io.File;

public interface UploadService {
  String Upload(File file);

  void transformToPictures();
}
