package com.example.dncompany.dto.openAiChat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


    @Getter @Setter @Builder
    public class GeminiRequest {
        private List<Content> contents;

        @Getter
        @Setter
        @Builder
        public static class Content {
            private List<Part> parts;
        }

        @Getter
        @Setter
        @Builder
        public static class Part {
            private String text;
            private InlineData inlineData;
        }

        @Getter
        @Setter
        @Builder
        public static class InlineData {
            private String mimeType;
            private String data;
        }
    }
