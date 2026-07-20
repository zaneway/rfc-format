package com.github.zaneway.format.rfc.chunk;

import java.util.ArrayList;
import java.util.List;

/**
 * 对异常大的逻辑块做确定性二次切分，防止 embedding 模型静默截断正文。
 */
public class RfcChunkSegmenter {

  private static final int MIN_SEGMENT_CHARS = 256;

  /**
   * 按段落、行、最后按字符依次降级切分；片段无重叠且拼接后与原文完全一致。
   *
   * @param chunk        原始逻辑块
   * @param maxBodyChars 每个片段允许的最大正文字符数
   * @return 至少一个物理片段
   */
  public List<RfcChunkSegment> split(RfcChunk chunk, int maxBodyChars) {
    if (maxBodyChars < MIN_SEGMENT_CHARS) {
      throw new IllegalArgumentException("maxBodyChars must be at least " + MIN_SEGMENT_CHARS);
    }
    String text = chunk.text();
    List<SegmentDraft> drafts = new ArrayList<>();
    int offset = 0;
    int currentLine = chunk.startLine();
    while (offset < text.length()) {
      int end = chooseEnd(text, offset, maxBodyChars);
      String body = text.substring(offset, end);
      int newlineCount = countNewlines(body);
      int endLine = currentLine + newlineCount;
      if (body.endsWith("\n") && endLine > currentLine) {
        endLine--;
      }
      drafts.add(new SegmentDraft(body, currentLine, endLine));
      currentLine += newlineCount;
      offset = end;
    }

    List<RfcChunkSegment> result = new ArrayList<>(drafts.size());
    for (int index = 0; index < drafts.size(); index++) {
      SegmentDraft draft = drafts.get(index);
      result.add(new RfcChunkSegment(draft.text(), draft.startLine(), draft.endLine(), index,
          drafts.size()));
    }
    return List.copyOf(result);
  }

  private static int chooseEnd(String text, int offset, int maxChars) {
    int hardEnd = Math.min(text.length(), offset + maxChars);
    if (hardEnd == text.length()) {
      return hardEnd;
    }
    int minimumPreferredEnd = offset + (maxChars / 2);
    // 子串起点最多为 hardEnd - 2，否则第二个换行会越过字符上限。
    int paragraphEnd = text.lastIndexOf("\n\n", hardEnd - 2);
    if (paragraphEnd >= minimumPreferredEnd) {
      return paragraphEnd + 2;
    }
    int lineEnd = text.lastIndexOf('\n', hardEnd - 1);
    return lineEnd >= minimumPreferredEnd ? lineEnd + 1 : hardEnd;
  }

  private static int countNewlines(String value) {
    int count = 0;
    for (int index = 0; index < value.length(); index++) {
      if (value.charAt(index) == '\n') {
        count++;
      }
    }
    return count;
  }

  private record SegmentDraft(String text, int startLine, int endLine) {
  }
}
