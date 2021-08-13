package service;


import models.Configuration;
import models.Group;
import models.Stock;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.lang.annotation.Annotation;

import static service.Storage.addItem;
import static service.Storage.getRoots;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StorageTest {
    StorageTest storageTest;

    @BeforeAll
    public void init() {
        storageTest = new StorageTest();
    }

    @Test
    @DisplayName("create")
    public void create() {
        Group g = new Group("23", "my");
        int size = Storage.groups.size();
        Storage.addGroup(g, "3", Storage.getRoots(), true);
        Assertions.assertNotEquals(size, Storage.groups.size());
    }

    @Test
    public void read() {
        Group g = new Group("23", "myo");
        Storage.addGroup(g, "3", Storage.getRoots(), true);
        Assertions.assertNotNull(Storage.findGroupByName(g.getName()).get());

    }


    @ParameterizedTest
    @CsvFileSource(resources = "/item.csv")
    public void csv(String id, double price, String name, String image, String pId) {
        Assertions.assertNotNull(id);
        Assertions.assertTrue(price>0);
        Assertions.assertNotNull(name);
        Assertions.assertNotNull(image);
        Assertions.assertNotNull(pId);
        Stock stock = new Stock(id, name, image, price, Configuration.Resolution.HD);


    }

}
