package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class QuestionReader {

    public static ArrayList<Question> readQuestions(String path) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File(path));
        ArrayList<Question> questions = new ArrayList<>();

        // Loop through all questions in the array
        if (root.isArray()) {
            for (JsonNode questionNode : root) {

                String question = questionNode.get("question").asText();
                JsonNode responsesNode = questionNode.get("responses");
                ArrayList<String> choices = new ArrayList<>();
                Iterator<JsonNode> values = responsesNode.elements();
                while (values.hasNext()) {
                    JsonNode value = values.next();
                    if (!value.isNull()) {
                        choices.add(value.asText());
                    }
                }

                int answer = questionNode.get("answer").asInt();
                Question fullQuestion = new Question(question, choices, choices.size(), answer);
                questions.add(fullQuestion);

            }
        }

        return questions;

    }

    public static void main(String[] args) {
        try {
            readQuestions("/Users/alisonliu/Documents/schoology_app_test/Untitled/schoology-mcq/data/u3.json");
        } catch (IOException e) {

        }
    }
}
