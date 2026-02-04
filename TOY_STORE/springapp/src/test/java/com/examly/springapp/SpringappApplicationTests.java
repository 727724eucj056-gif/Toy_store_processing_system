package com.examly.springapp;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringappApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    // ============================================================================
    // DAY 3: DIRECTORY STRUCTURE TESTS (4 tests)
    // ============================================================================

    @Test
    @Order(1)
    void Day3_test_Controller_Directory_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/controller").isDirectory());
    }

    @Test
    @Order(2)
    void Day3_test_Model_Directory_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/model").isDirectory());
    }

    @Test
    @Order(3)
    void Day3_test_Service_Directory_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service").isDirectory());
    }

    @Test
    @Order(4)
    void Day3_test_Repository_Directory_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/repository").isDirectory());
    }

    // ============================================================================
    // DAY 4: MODEL FILE EXISTS TESTS (5 tests)
    // ============================================================================

    @Test
    @Order(5)
    void Day4_test_ProductModel_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/model/Product.java").isFile());
    }

    @Test
    @Order(6)
    void Day4_test_SupplierModel_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/model/Supplier.java").isFile());
    }

    @Test
    @Order(7)
    void Day4_test_PurchaseOrderModel_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/model/PurchaseOrder.java").isFile());
    }

    @Test
    @Order(8)
    void Day4_test_PurchaseOrderItemModel_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/model/PurchaseOrderItem.java").isFile());
    }

    @Test
    @Order(9)
    void Day4_test_CustomerReturnModel_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/model/CustomerReturn.java").isFile());
    }

    // ============================================================================
    // DAY 4: ENTITY & ID ANNOTATION TESTS (7 tests)
    // ============================================================================

    @Test
    @Order(10)
    void Day4_test_Product_Has_Entity_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.model.Product");
            Class<?> annotation = Class.forName("jakarta.persistence.Entity");
            assertTrue(clazz.isAnnotationPresent((Class) annotation), 
                "❌ @Entity annotation is missing on Product class");
        } catch (ClassNotFoundException e) {
            fail("❌ Product class not found.");
        }
    }

    @Test
    @Order(11)
    void Day4_test_Product_Has_Id_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.model.Product");
            Class<?> idAnnotation = Class.forName("jakarta.persistence.Id");
            boolean found = false;
            for (var field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent((Class) idAnnotation)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "❌ No field in Product class is annotated with @Id");
        } catch (ClassNotFoundException e) {
            fail("❌ Product class not found.");
        }
    }

    @Test
    @Order(12)
    void Day4_test_Supplier_Has_Entity_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.model.Supplier");
            Class<?> annotation = Class.forName("jakarta.persistence.Entity");
            assertTrue(clazz.isAnnotationPresent((Class) annotation), 
                "❌ @Entity annotation is missing on Supplier class");
        } catch (ClassNotFoundException e) {
            fail("❌ Supplier class not found.");
        }
    }

    @Test
    @Order(13)
    void Day4_test_Supplier_Has_Id_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.model.Supplier");
            Class<?> idAnnotation = Class.forName("jakarta.persistence.Id");
            boolean found = false;
            for (var field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent((Class) idAnnotation)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "❌ No field in Supplier class is annotated with @Id");
        } catch (ClassNotFoundException e) {
            fail("❌ Supplier class not found.");
        }
    }

    @Test
    @Order(14)
    void Day4_test_CustomerReturn_Has_Entity_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.model.CustomerReturn");
            Class<?> annotation = Class.forName("jakarta.persistence.Entity");
            assertTrue(clazz.isAnnotationPresent((Class) annotation), 
                "❌ @Entity annotation is missing on CustomerReturn class");
        } catch (ClassNotFoundException e) {
            fail("❌ CustomerReturn class not found.");
        }
    }

    @Test
    @Order(15)
    void Day4_test_PurchaseOrder_Has_Entity_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.model.PurchaseOrder");
            Class<?> annotation = Class.forName("jakarta.persistence.Entity");
            assertTrue(clazz.isAnnotationPresent((Class) annotation), 
                "❌ @Entity annotation is missing on PurchaseOrder class");
        } catch (ClassNotFoundException e) {
            fail("❌ PurchaseOrder class not found.");
        }
    }

    @Test
    @Order(16)
    void Day4_test_PurchaseOrderItem_Has_Entity_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.model.PurchaseOrderItem");
            Class<?> annotation = Class.forName("jakarta.persistence.Entity");
            assertTrue(clazz.isAnnotationPresent((Class) annotation), 
                "❌ @Entity annotation is missing on PurchaseOrderItem class");
        } catch (ClassNotFoundException e) {
            fail("❌ PurchaseOrderItem class not found.");
        }
    }

    // ============================================================================
    // DAY 5: REPOSITORY FILE EXISTS TESTS (5 tests)
    // ============================================================================

    @Test
    @Order(17)
    void Day5_test_ProductRepo_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/repository/ProductRepo.java").isFile());
    }

    @Test
    @Order(18)
    void Day5_test_SupplierRepo_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/repository/SupplierRepo.java").isFile());
    }

    @Test
    @Order(19)
    void Day5_test_PurchaseOrderRepo_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/repository/PurchaseOrderRepo.java").isFile());
    }

    @Test
    @Order(20)
    void Day5_test_PurchaseOrderItemRepo_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/repository/PurchaseOrderItemRepo.java").isFile());
    }

    @Test
    @Order(21)
    void Day5_test_CustomerReturnRepo_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/repository/CustomerReturnRepo.java").isFile());
    }

    // ============================================================================
    // DAY 5: REPOSITORY ANNOTATION TESTS (5 tests)
    // ============================================================================

    @Test
    @Order(22)
    void Day5_test_ProductRepo_Has_Repository_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.repository.ProductRepo");
            Class<?> annotation = Class.forName("org.springframework.stereotype.Repository");
            assertTrue(clazz.isAnnotationPresent((Class) annotation), 
                "❌ @Repository annotation is missing on ProductRepo");
        } catch (ClassNotFoundException e) {
            fail("❌ ProductRepo class not found.");
        }
    }

    @Test
    @Order(23)
    void Day5_test_SupplierRepo_Has_Repository_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.repository.SupplierRepo");
            Class<?> annotation = Class.forName("org.springframework.stereotype.Repository");
            assertTrue(clazz.isAnnotationPresent((Class) annotation), 
                "❌ @Repository annotation is missing on SupplierRepo");
        } catch (ClassNotFoundException e) {
            fail("❌ SupplierRepo class not found.");
        }
    }

    @Test
    @Order(24)
    void Day5_test_CustomerReturnRepo_Has_Repository_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.repository.CustomerReturnRepo");
            Class<?> annotation = Class.forName("org.springframework.stereotype.Repository");
            assertTrue(clazz.isAnnotationPresent((Class) annotation), 
                "❌ @Repository annotation is missing on CustomerReturnRepo");
        } catch (ClassNotFoundException e) {
            fail("❌ CustomerReturnRepo class not found.");
        }
    }

    @Test
    @Order(25)
    void Day5_test_PurchaseOrderRepo_Has_Repository_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.repository.PurchaseOrderRepo");
            Class<?> annotation = Class.forName("org.springframework.stereotype.Repository");
            assertTrue(clazz.isAnnotationPresent((Class) annotation), 
                "❌ @Repository annotation is missing on PurchaseOrderRepo");
        } catch (ClassNotFoundException e) {
            fail("❌ PurchaseOrderRepo class not found.");
        }
    }

    @Test
    @Order(26)
    void Day5_test_PurchaseOrderItemRepo_Has_Repository_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.repository.PurchaseOrderItemRepo");
            Class<?> annotation = Class.forName("org.springframework.stereotype.Repository");
            assertTrue(clazz.isAnnotationPresent((Class) annotation), 
                "❌ @Repository annotation is missing on PurchaseOrderItemRepo");
        } catch (ClassNotFoundException e) {
            fail("❌ PurchaseOrderItemRepo class not found.");
        }
    }

    // ============================================================================
    // DAY 6: CONTROLLER FILE EXISTS TESTS (5 tests)
    // ============================================================================

    @Test
    @Order(27)
    void Day6_test_ProductController_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/controller/ProductController.java").isFile());
    }

    @Test
    @Order(28)
    void Day6_test_SupplierController_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/controller/SupplierController.java").isFile());
    }

    @Test
    @Order(29)
    void Day6_test_PurchaseOrderController_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/controller/PurchaseOrderController.java").isFile());
    }

    @Test
    @Order(30)
    void Day6_test_CustomerReturnController_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/controller/CustomerReturnController.java").isFile());
    }

    @Test
    @Order(31)
    void Day6_test_PurchaseOrderItemController_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/controller/PurchaseOrderItemController.java").isFile());
    }

    // ============================================================================
    // DAY 6: CONTROLLER REST ANNOTATION TESTS (5 tests)
    // ============================================================================

    @Test
    @Order(32)
    void Day6_test_ProductController_Has_RestController_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.ProductController");
            Class<?> annotation = Class.forName("org.springframework.web.bind.annotation.RestController");
            assertTrue(clazz.isAnnotationPresent((Class) annotation), 
                "❌ @RestController annotation is missing on ProductController");
        } catch (ClassNotFoundException e) {
            fail("❌ ProductController class not found.");
        }
    }

    @Test
    @Order(33)
    void Day6_test_SupplierController_Has_RestController_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.SupplierController");
            Class<?> annotation = Class.forName("org.springframework.web.bind.annotation.RestController");
            assertTrue(clazz.isAnnotationPresent((Class) annotation), 
                "❌ @RestController annotation is missing on SupplierController");
        } catch (ClassNotFoundException e) {
            fail("❌ SupplierController class not found.");
        }
    }

    @Test
    @Order(34)
    void Day6_test_PurchaseOrderController_Has_RestController_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.PurchaseOrderController");
            Class<?> annotation = Class.forName("org.springframework.web.bind.annotation.RestController");
            assertTrue(clazz.isAnnotationPresent((Class) annotation), 
                "❌ @RestController annotation is missing on PurchaseOrderController");
        } catch (ClassNotFoundException e) {
            fail("❌ PurchaseOrderController class not found.");
        }
    }

    @Test
    @Order(35)
    void Day6_test_CustomerReturnController_Has_RestController_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.CustomerReturnController");
            Class<?> annotation = Class.forName("org.springframework.web.bind.annotation.RestController");
            assertTrue(clazz.isAnnotationPresent((Class) annotation), 
                "❌ @RestController annotation is missing on CustomerReturnController");
        } catch (ClassNotFoundException e) {
            fail("❌ CustomerReturnController class not found.");
        }
    }

    @Test
    @Order(36)
    void Day6_test_PurchaseOrderItemController_Has_RestController_Annotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.PurchaseOrderItemController");
            Class<?> annotation = Class.forName("org.springframework.web.bind.annotation.RestController");
            assertTrue(clazz.isAnnotationPresent((Class) annotation), 
                "❌ @RestController annotation is missing on PurchaseOrderItemController");
        } catch (ClassNotFoundException e) {
            fail("❌ PurchaseOrderItemController class not found.");
        }
    }

    // ============================================================================
    // DAY 6: CONTROLLER MAPPING ANNOTATION TESTS (5 tests)
    // ============================================================================

    @Test
    @Order(37)
    void Day6_test_ProductController_Has_PostMapping() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.ProductController");
            Class<?> postMapping = Class.forName("org.springframework.web.bind.annotation.PostMapping");
            boolean found = false;
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent((Class) postMapping)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "❌ No @PostMapping method found in ProductController");
        } catch (ClassNotFoundException e) {
            fail("❌ ProductController class not found.");
        }
    }

    @Test
    @Order(38)
    void Day6_test_ProductController_Has_GetMapping() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.ProductController");
            Class<?> getMapping = Class.forName("org.springframework.web.bind.annotation.GetMapping");
            boolean found = false;
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent((Class) getMapping)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "❌ No @GetMapping method found in ProductController");
        } catch (ClassNotFoundException e) {
            fail("❌ ProductController class not found.");
        }
    }

    @Test
    @Order(39)
    void Day6_test_ProductController_Has_PutMapping() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.ProductController");
            Class<?> putMapping = Class.forName("org.springframework.web.bind.annotation.PutMapping");
            boolean found = false;
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent((Class) putMapping)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "❌ No @PutMapping method found in ProductController");
        } catch (ClassNotFoundException e) {
            fail("❌ ProductController class not found.");
        }
    }

    @Test
    @Order(40)
    void Day6_test_ProductController_Has_DeleteMapping() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.ProductController");
            Class<?> deleteMapping = Class.forName("org.springframework.web.bind.annotation.DeleteMapping");
            boolean found = false;
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent((Class) deleteMapping)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "❌ No @DeleteMapping method found in ProductController");
        } catch (ClassNotFoundException e) {
            fail("❌ ProductController class not found.");
        }
    }

    @Test
    @Order(41)
    void Day6_test_SupplierController_Has_PostMapping() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.SupplierController");
            Class<?> postMapping = Class.forName("org.springframework.web.bind.annotation.PostMapping");
            boolean found = false;
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent((Class) postMapping)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "❌ No @PostMapping method found in SupplierController");
        } catch (ClassNotFoundException e) {
            fail("❌ SupplierController class not found.");
        }
    }

    // ============================================================================
    // DAY 6: API ENDPOINT TESTS - NEGATIVE CASES (3 tests)
    // ============================================================================

    @Test
    @Order(42)
    public void Day6_testCreateProduct_NoBody_StatusBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Order(43)
    public void Day6_testGetAllProducts_StatusNoContent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }


    // ============================================================================
    // DAY 7: REQUEST MAPPING & PATH VARIABLE TESTS (6 tests)
    // ============================================================================

    @Test
    @Order(44)
    void Day7_test_ProductController_Has_RequestMapping() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.ProductController");
            Class<?> requestMapping = Class.forName("org.springframework.web.bind.annotation.RequestMapping");
            boolean found = clazz.isAnnotationPresent((Class) requestMapping);
            if (!found) {
                for (Method m : clazz.getDeclaredMethods()) {
                    if (m.isAnnotationPresent((Class) requestMapping)) {
                        found = true;
                        break;
                    }
                }
            }
            assertTrue(found, "❌ No @RequestMapping found on ProductController");
        } catch (ClassNotFoundException e) {
            fail("❌ ProductController class not found.");
        }
    }

    @Test
    @Order(45)
    void Day7_test_ProductController_Has_PathVariable() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.ProductController");
            Class<?> pathVariable = Class.forName("org.springframework.web.bind.annotation.PathVariable");
            boolean found = false;
            for (Method method : clazz.getDeclaredMethods()) {
                for (Parameter param : method.getParameters()) {
                    if (param.isAnnotationPresent((Class) pathVariable)) {
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }
            assertTrue(found, "❌ No @PathVariable found in ProductController");
        } catch (ClassNotFoundException e) {
            fail("❌ ProductController class not found.");
        }
    }

    @Test
    @Order(46)
    void Day7_test_SupplierController_Has_RequestMapping() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.SupplierController");
            Class<?> requestMapping = Class.forName("org.springframework.web.bind.annotation.RequestMapping");
            boolean found = clazz.isAnnotationPresent((Class) requestMapping);
            assertTrue(found, "❌ No @RequestMapping found on SupplierController");
        } catch (ClassNotFoundException e) {
            fail("❌ SupplierController class not found.");
        }
    }

    @Test
    @Order(47)
    void Day7_test_SupplierController_Has_PathVariable() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.SupplierController");
            Class<?> pathVariable = Class.forName("org.springframework.web.bind.annotation.PathVariable");
            boolean found = false;
            for (Method method : clazz.getDeclaredMethods()) {
                for (Parameter param : method.getParameters()) {
                    if (param.isAnnotationPresent((Class) pathVariable)) {
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }
            assertTrue(found, "❌ No @PathVariable found in SupplierController");
        } catch (ClassNotFoundException e) {
            fail("❌ SupplierController class not found.");
        }
    }

    @Test
    @Order(48)
    void Day7_test_PurchaseOrderController_Has_RequestMapping() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.PurchaseOrderController");
            Class<?> requestMapping = Class.forName("org.springframework.web.bind.annotation.RequestMapping");
            boolean found = clazz.isAnnotationPresent((Class) requestMapping);
            assertTrue(found, "❌ No @RequestMapping found on PurchaseOrderController");
        } catch (ClassNotFoundException e) {
            fail("❌ PurchaseOrderController class not found.");
        }
    }

    @Test
    @Order(49)
    void Day7_test_CustomerReturnController_Has_RequestMapping() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.CustomerReturnController");
            Class<?> requestMapping = Class.forName("org.springframework.web.bind.annotation.RequestMapping");
            boolean found = clazz.isAnnotationPresent((Class) requestMapping);
            assertTrue(found, "❌ No @RequestMapping found on CustomerReturnController");
        } catch (ClassNotFoundException e) {
            fail("❌ CustomerReturnController class not found.");
        }
    }

    // ============================================================================
    // DAY 8: SERVICE FILE EXISTS TESTS (10 tests)
    // ============================================================================

    @Test
    @Order(50)
    void Day8_test_ProductService_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/ProductService.java").isFile());
    }

    @Test
    @Order(51)
    void Day8_test_SupplierService_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/SupplierService.java").isFile());
    }

    @Test
    @Order(52)
    void Day8_test_CustomerReturnService_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/CustomerReturnService.java").isFile());
    }

    @Test
    @Order(53)
    void Day8_test_PurchaseOrderService_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/PurchaseOrderService.java").isFile());
    }

    @Test
    @Order(54)
    void Day8_test_PurchaseOrderItemService_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/PurchaseOrderItemService.java").isFile());
    }

    @Test
    @Order(55)
    void Day8_test_ProductServiceImpl_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/ProductServiceImpl.java").isFile());
    }

    @Test
    @Order(56)
    void Day8_test_SupplierServiceImpl_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/SupplierServiceImpl.java").isFile());
    }

    @Test
    @Order(57)
    void Day8_test_CustomerReturnServiceImpl_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/CustomerReturnServiceImpl.java").isFile());
    }

    @Test
    @Order(58)
    void Day8_test_PurchaseOrderServiceImpl_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/PurchaseOrderServiceImpl.java").isFile());
    }

    @Test
    @Order(59)
    void Day8_test_PurchaseOrderItemServiceImpl_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/PurchaseOrderItemServiceImpl.java").isFile());
    }

    // ============================================================================
    // DAY 8-9: CRUD OPERATIONS - PRODUCT (4 tests)
    // ============================================================================

    @Test
    @Order(60)
    public void Day8_testAddProduct() throws Exception {
        String requestBody = "{\"productName\": \"Laptop\", \"description\": \"High-end gaming laptop\", \"price\": 1500.00, \"stockQuantity\": 10}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productName").value("Laptop"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("High-end gaming laptop"));
    }

    @Test
    @Order(61)
    public void Day9_testGetAllProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].productName").value("Laptop"));
    }

    @Test
    @Order(62)
    public void Day9_testGetProductById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productName").value("Laptop"));
    }

    @Test
    @Order(63)
    public void Day9_testUpdateProduct() throws Exception {
        String requestBody = "{\"productName\": \"Updated Laptop\", \"description\": \"Updated description\", \"price\": 1600.00, \"stockQuantity\": 15}";
        mockMvc.perform(MockMvcRequestBuilders.put("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productName").value("Updated Laptop"));
    }

    // ============================================================================
    // DAY 10: CRUD OPERATIONS - SUPPLIER (4 tests)
    // ============================================================================

    @Test
    @Order(64)
    public void Day10_testAddSupplier() throws Exception {
        String requestBody = "{\"supplierName\": \"Tech Supplies Inc\", \"contactNumber\": \"1234567890\", \"email\": \"tech@example.com\", \"address\": \"123 Tech Street\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/suppliers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.supplierName").value("Tech Supplies Inc"))
                .andExpect(jsonPath("$.email").value("tech@example.com"));
    }

    @Test
    @Order(65)
    public void Day10_testGetAllSuppliers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/suppliers")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].supplierName").value("Tech Supplies Inc"));
    }

    @Test
    @Order(66)
    public void Day10_testGetSupplierById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/suppliers/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.supplierId").value(1));
    }

    @Test
    @Order(67)
    public void Day10_testUpdateSupplier() throws Exception {
        String requestBody = "{\"supplierName\": \"Updated Supplier\", \"contactNumber\": \"9876543210\", \"email\": \"updated@example.com\", \"address\": \"456 New Street\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/api/suppliers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.supplierName").value("Updated Supplier"));
    }

    // ============================================================================
    // DAY 11: PURCHASE ORDER OPERATIONS (4 tests)
    // ============================================================================

    @Test
    @Order(68)
    public void Day11_testAddPurchaseOrder() throws Exception {
        String requestBody = "{\"orderNumber\": \"PO001\", \"orderDate\": \"2024-01-15T10:00:00\", \"status\": \"CREATED\", \"supplier\": {\"supplierId\": 1}}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/purchase-orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.orderNumber").value("PO001"))
                .andExpect(jsonPath("$.status").value("CREATED"));
    }

    @Test
    @Order(69)
    public void Day11_testGetAllPurchaseOrders() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/purchase-orders")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderNumber").value("PO001"));
    }

    @Test
    @Order(70)
    public void Day11_testGetPurchaseOrderById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/purchase-orders/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.purchaseOrderId").value(1));
    }

    @Test
    @Order(71)
    public void Day11_testUpdatePurchaseOrder() throws Exception {
        String requestBody = "{\"orderNumber\": \"PO001-UPDATED\", \"orderDate\": \"2024-01-16T10:00:00\", \"status\": \"APPROVED\", \"supplier\": {\"supplierId\": 1}}";
        mockMvc.perform(MockMvcRequestBuilders.put("/api/purchase-orders/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderNumber").value("PO001-UPDATED"))
                .andExpect(jsonPath("$.status").value("APPROVED"));
    }

    // ============================================================================
    // DAY 12: CUSTOMER RETURN OPERATIONS (3 tests)
    // ============================================================================

    @Test
    @Order(72)
    public void Day12_testAddCustomerReturn() throws Exception {
        String requestBody = "{\"returnDate\": \"2024-01-15T10:00:00\", \"quantity\": 2, \"reason\": \"Defective\", \"product\": {\"productId\": 1}}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer-returns")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.quantity").value(2))
                .andExpect(jsonPath("$.reason").value("Defective"));
    }

    @Test
    @Order(73)
    public void Day12_testGetAllCustomerReturns() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customer-returns")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].reason").value("Defective"));
    }

    @Test
    @Order(74)
    public void Day12_testGetCustomerReturnById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customer-returns/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerReturnId").value(1));
    }

    // ============================================================================
    // DAY 13: PURCHASE ORDER ITEM OPERATIONS (2 tests)
    // ============================================================================

    @Test
    @Order(75)
    public void Day13_testAddPurchaseOrderItem() throws Exception {
        String requestBody = "{\"quantity\": 5, \"unitPrice\": 1500.00, \"purchaseOrder\": {\"purchaseOrderId\": 1}, \"product\": {\"productId\": 1}}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/purchase-order-items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.quantity").value(5));
    }

    @Test
    @Order(76)
    public void Day13_testGetPurchaseOrderItemsByOrderId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/purchase-order-items/order/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].quantity").value(5));
    }

    // ============================================================================
    // DAY 14: EXCEPTION HANDLING TESTS (2 tests)
    // ============================================================================

    @Test
    @Order(77)
    void Day14_test_Exception_Directory_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/exception").isDirectory());
    }

    @Test
    @Order(78)
    void Day14_test_ResourceNotFoundException_File_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/exception/ResourceNotFoundException.java").isFile());
    }

    // ============================================================================
    // DAY 15: DELETE & ADDITIONAL OPERATIONS (1 test)
    // ============================================================================

    @Test
@Order(79)
    void Day14test_configuartion_Directory_Exists() {
        assertTrue(new File("src/main/java/com/examly/springapp/configuration").isDirectory());
    }

@Test
@Order(80)
public void Day15_testAOPLogFileExists() {

    assertTrue(new File("src/main/java/com/examly/springapp/aop").isDirectory());
   
}
}