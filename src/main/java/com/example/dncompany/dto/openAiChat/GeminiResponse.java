package com.example.dncompany.dto.openAiChat;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

    @Getter @Setter
    public class GeminiResponse {
        private List<Candidate> candidates;
        private PromptFeedback promptFeedback;

        @Getter
        @Setter
        public static class Candidate {
            private Content content;
            private String finishReason;
            private int index;
        }

        @Getter
        @Setter
        public static class Content {
            private List<Part> parts;
            private String role;
        }

        @Getter
        @Setter
        public static class Part {
            private String text;
        }

        @Getter
        @Setter
        public static class PromptFeedback {
            private SafetyRating safetyRatings;
        }

        @Getter
        @Setter
        public static class SafetyRating {
            private String category;
            private String probability;
        }
    }