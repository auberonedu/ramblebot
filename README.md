# ramblebot

Ramblebot is a Java-based program designed to generate new text in the style of a given writer. By training on an input text file, it builds a unigram model to predict words based on single-word probabilities, allowing the bot to create text that mimics the style of the input. The project is divided into five waves, each building towards the final functionality.

# Project Goals
Tokenize Text: Process input text to create a list of lowercase words.
Unigram Model: Train a model on the tokenized text, using word frequencies to generate probable next words.
Text Generation: Generate a user-defined number of words in the style of the input text.
