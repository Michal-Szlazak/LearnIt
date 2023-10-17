package db.testIt.db;

import db.testIt.db.Test.Test;
import db.testIt.db.Test.TestToUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMongoRepositories
public class TestMapping {

    @Autowired
    TestRepository testRepository;

    @GetMapping("/test")
    public String test() {
        System.out.println("Test mapping requested.");
        return "Testing endpoint mapping";
    }

    @GetMapping("/test/{testName}")
    public String showTestName(@PathVariable("testName") String testName) {
        return testName;
    }

    @PostMapping(value = "/postTest", consumes = "application/json", produces = "application/json")
    public boolean postTest(@RequestBody TestToUpload test) {
        System.out.println(test.getTest().toString());
        return testRepository.saveTest(test);
    }

    @PostMapping(value = "/page", consumes = "application/json", produces = "application/json")
    public List<String> loadPages(@RequestBody PageFilter filter) {
        return testRepository.getAllPages(filter);
    }
    @GetMapping("downloadTest/{testId}")
    public TestToUpload getTest(@PathVariable("testId") String testId) {
        System.out.println(testRepository.getTestById(testId));
        return testRepository.getTestById(testId);
    }

}
