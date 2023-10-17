package db.testIt.db;

import com.mongodb.client.MongoDatabase;
import db.testIt.db.Test.Test;
import db.testIt.db.Test.TestToUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TestRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    private List<String> pages;
    private PageFilter filter;

    public boolean saveTest(TestToUpload test) {
        if(containsTest(test.getUserName(), test.getTest().getTestName())) {
            return false;
        }
        mongoTemplate.save(test);
        return true;
    }
    public boolean containsTest(String userName, String testName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userName + testName));
        TestToUpload test = mongoTemplate.findOne(query, TestToUpload.class);
        return test != null;
    }

    public List<String> getAllPages(PageFilter filter) {
        this.filter = filter;
        Query query = new Query();
        if(!filter.getUserName().isEmpty()) {
            query.addCriteria(Criteria.where("userName").is(filter.getUserName()));
        }
        if(!filter.getTestName().isEmpty()) {
            query.addCriteria(Criteria.where("test.testName").is(filter.getTestName()));
        }
        if(!filter.getKeywords().isEmpty()) {
            query.addCriteria(Criteria.where("keywords.").in(filter.getKeywords()));
        }

        List<String> page = new ArrayList<>();
        List<TestToUpload> test = mongoTemplate.find(query, TestToUpload.class);
        for(TestToUpload testU : test) {
            page.add(testU.getUserName());
            page.add(testU.getTest().getTestName());
        }

        return page;
    }

    public TestToUpload getTestById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        TestToUpload test = mongoTemplate.findOne(query, TestToUpload.class);
        assert test != null;
        return test;
    }
}
