package br.com.glyp.llm.service;

import br.com.glyp.msorm.model.Redacao;
import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.*;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeminiService {

  private static final String gcloudProjectId = System.getenv("PROJECT_ID");

  private static final String gcloudLocationId = System.getenv("LOCATION_ID");

  private static final String gcloudModelId = System.getenv("MODEL_ID");

  private final String systemInstruction;

  // @formatter:off
  private static final GenerationConfig generationConfig = GenerationConfig
      .newBuilder()
      .setMaxOutputTokens(2048)
      .setTemperature(1F)
      .setTopP(0.95F)
      .build();

  private static final List<SafetySetting> safetySettings = Arrays.asList(
      SafetySetting.newBuilder()
          .setCategory(HarmCategory.HARM_CATEGORY_HATE_SPEECH)
          .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE).build(),
      SafetySetting.newBuilder()
          .setCategory(HarmCategory.HARM_CATEGORY_DANGEROUS_CONTENT)
          .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE).build(),
      SafetySetting.newBuilder()
          .setCategory(HarmCategory.HARM_CATEGORY_SEXUALLY_EXPLICIT)
          .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE).build(),
      SafetySetting.newBuilder()
          .setCategory(HarmCategory.HARM_CATEGORY_HARASSMENT)
          .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE).build()
  );

  // @formatter:on

  public GeminiService() {
    this.systemInstruction = getSystemInstructionFromClasspath();
  }

  private static String getSystemInstructionFromClasspath() {
    Resource resource = new ClassPathResource("gemini-si.txt");
    String systemInstruction = "";
    try {
      try (InputStream inputStream = resource.getInputStream()) {
        systemInstruction =
          new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
      }
      if (systemInstruction.isBlank()) {
        throw new RuntimeException("Instrução de sistema está em branco.");
      }
    } catch (Exception e) {
      throw new RuntimeException(
        "Erro no carregamento do arquivo '\"gemini-si.txt\"' de instrução de sistema do modelo Gemini: " +
          e.getMessage()
      );
    }
    return systemInstruction;
  }

  public String corrigirRedacao(Redacao redacao) {
    return this.corrigirRedacao(
      redacao.getPrompt(),
      redacao.getTitle(),
      redacao.getText()
    );
  }

  public String corrigirRedacao(String tema, String titulo, String texto) {
    String templateInput =
      """
            TEMA: %s
            TITULO: %s
            TEXTO: %s
        """;

    String input = String.format(templateInput, tema, titulo, texto).trim();

    try (VertexAI vertexAi = new VertexAI(gcloudProjectId, gcloudLocationId);) {
      Content vertexAiSystemInstruction = ContentMaker.fromMultiModalData(
        systemInstruction
      );
      GenerativeModel model = new GenerativeModel.Builder()
        .setModelName(gcloudModelId)
        .setVertexAi(vertexAi)
        .setGenerationConfig(generationConfig)
        .setSafetySettings(safetySettings)
        .setSystemInstruction(vertexAiSystemInstruction)
        .build();
      return model
        .generateContent(input)
        .getCandidates(0)
        .getContent()
        .getPartsList()
        .stream()
        .map(Part::getText)
        .collect(Collectors.joining());
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(
        "Erro na comunicação com o modelo na correção da redação: " +
          e.getMessage()
      );
    }
  }
}
