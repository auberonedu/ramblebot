import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class LowercaseSentenceTokenizerTest {

    // Wave 1
    @Test
    void testTokenizeWithNoCapitalizationOrPeriod() {
        LowercaseSentenceTokenizer tokenizer = new LowercaseSentenceTokenizer();
        Scanner scanner = new Scanner("this is a lowercase sentence without a period");
        Scanner scanner2 = new Scanner("this is a test for my method");
        List<String> tokens = tokenizer.tokenize(scanner);
        List<String> tokens2 = tokenizer.tokenize(scanner2);

        assertEquals(List.of("this", "is", "a", "lowercase", "sentence", "without", "a", "period"), tokens);
        //Additional Test to make sure method is working properly
        assertEquals(List.of("this", "is", "a", "test", "for", "my", "method"), tokens2);
    }

    // Wave 2
    @Test
    void  testToeknizeWithSpaces(){
        LowercaseSentenceTokenizer tokenizer = new LowercaseSentenceTokenizer();
        Scanner scanner = new Scanner("this is a lowercase sentence with         spaces");
        Scanner scanner2 = new Scanner("this is   a    test    for    my    method");   
        
        List<String> tokens = tokenizer.tokenize(scanner);
        List<String> tokens2 = tokenizer.tokenize(scanner2);

        assertEquals(List.of("this", "is", "a", "lowercase", "sentence", "with", "spaces"), tokens);
        assertEquals(List.of("this", "is", "a", "test", "for", "my", "method"), tokens2);
    }
    

    // Wave 3
    @Test
    void testTokenizeWithCapitalization() {
        LowercaseSentenceTokenizer tokenizer = new LowercaseSentenceTokenizer();
        Scanner scanner = new Scanner("This is a SENTENCE with sTrAnGe capitalization");
        List<String> tokens = tokenizer.tokenize(scanner);

        assertEquals(List.of("this", "is", "a", "sentence", "with", "strange", "capitalization"), tokens);
    }

    // Wave 3
    @Test
    void testTokenizeSentenceWithPeriod() {
        LowercaseSentenceTokenizer tokenizer = new LowercaseSentenceTokenizer();
        Scanner scanner = new Scanner("Hello world. This is an example.");
        List<String> tokens = tokenizer.tokenize(scanner);
        Scanner scanner2 = new Scanner("This. is. another. test.");
        List<String> tokens2 = tokenizer.tokenize(scanner2);

        assertEquals(List.of("hello", "world", ".", "this", "is", "an", "example", "."), tokens);
        assertEquals(List.of("this", ".", "is",".", "another", ".", "test", "."), tokens2);
    }

    // Wave 3
    @Test
    void testTokenizeWithInternalPeriod() {
        LowercaseSentenceTokenizer tokenizer = new LowercaseSentenceTokenizer();
        Scanner scanner = new Scanner("Hello world. This is Dr.Smith's example.");
        Scanner scanner2 = new Scanner("Th.is is another. te..st");
        Scanner scanner3 = new Scanner("Mr.John is a c.o.ol and ni.ce person");

        List<String> tokens = tokenizer.tokenize(scanner);
        List<String> tokens2 = tokenizer.tokenize(scanner2);
        List<String> tokens3 = tokenizer.tokenize(scanner3);
 
        assertEquals(List.of("hello", "world", ".", "this", "is", "dr.smith's", "example", "."), tokens);
        assertEquals(List.of("th.is", "is", "another", ".", "te..st"), tokens2);
        //This test failed as the first char did not convert to lowercase
        //
        assertEquals(List.of("mr.john", "is", "a", "c.o.ol", "and" , "ni.ce", "person"), tokens3);
    }
    
}
