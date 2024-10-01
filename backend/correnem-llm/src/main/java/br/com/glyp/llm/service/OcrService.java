package br.com.glyp.llm.service;

import br.com.glyp.msorm.web.exceptions.GlypBackendException;
import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OcrService {

  public String extrairTexto(MultipartFile file) {
    try {
      ByteString imgBytes = ByteString.copyFrom(file.getBytes());
      Image img = Image.newBuilder().setContent(imgBytes).build();
      Feature feat = Feature.newBuilder().setType(Feature.Type.DOCUMENT_TEXT_DETECTION).build();

      ImageContext imageContext = ImageContext.newBuilder().addLanguageHints("pt-handwrit").build();

      AnnotateImageRequest request =
        AnnotateImageRequest.newBuilder().addFeatures(feat).setImageContext(imageContext).setImage(img).build();

      List<AnnotateImageRequest> requests = new ArrayList<>();
      requests.add(request);

      try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
        List<AnnotateImageResponse> responses = client.batchAnnotateImages(requests).getResponsesList();

        for (AnnotateImageResponse res : responses) {
          if (res.hasError()) {
            throw new GlypBackendException("Erro na leitura do texto da imagem.",
              HttpStatus.INTERNAL_SERVER_ERROR.value());
          }

          TextAnnotation annotation = res.getFullTextAnnotation();
          return annotation.getText();
        }
      }
    } catch (IOException e) {
      throw new GlypBackendException("Erro na leitura do arquivo.", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
    return null;
  }

}
