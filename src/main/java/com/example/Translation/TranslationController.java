package com.example.Translation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TranslationController {
    private final TranslationService translationService;

    public TranslationController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @GetMapping("/translate/{word}/{sourceLanguage}/{targetLanguage}")
    public String translate(@PathVariable String word, @PathVariable String sourceLanguage, @PathVariable String targetLanguage) {
        return translationService.translate(word, sourceLanguage, targetLanguage);
    }
}