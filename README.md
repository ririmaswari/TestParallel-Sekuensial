# 📦 "Parallel vs sequential test"

## 📌 Overview
Ini adalah framework otomasi Selenium WebDriver yang komprehensif untuk menguji fungsi e-commerce, khususnya untuk aplikasi SauceDemo.
Proyek ini menggunakan pola desain Page Object Model (POM) dengan framework TestNG untuk menjalankan pengujian yang kuat dan mudah dipelihara.

## 📂 Project Structure
```
src/
├── main/java/com/juaracoding/ecom/
│   ├── pages/           # Page Object classes
│   ├── utils/           # Utility classes (DriverUtil, DriverManager)
│   └── providers/       # Data providers for test data
└── test/java/com/juaracoding/ecom/
    ├── AuthenticationTest.java    # Login/Authentication tests
    ├── InventoryTest.java         # Product inventory tests
    ├── DragDropTest.java          # UI interaction tests
    ├── BaseTest.java              # Base test setup
    └── ListenerTest.java          # TestNG listeners
```

## ✅ Test Categories

### 🔒 1. Authentication Tests (`AuthenticationTest.java`)
- Login with valid credentials
- Login with invalid username
- Login with invalid password
- Login without username
- Login without password
- Login with empty credentials

### 📦 2. Inventory Tests (`InventoryTest.java`)
- Add single item to cart
- Add multiple items to cart
- Verify product sorting functionality

### 🖱️ 3. UI Interaction Tests (`DragDropTest.java`)
- Test drag and drop functionality
- Test element resizing

## ⚙️ Test Execution Modes

### ▶️ Sequential Test Execution
By default, all tests run **sequentially**, one after another.

**Example Configuration (`testng.xml`):**
```xml
<suite name="SequentialSuite" parallel="false">
    <test name="AuthenticationTests">
        <classes>
            <class name="com.juaracoding.ecom.AuthenticationTest"/>
        </classes>
    </test>
    <test name="InventoryTests">
        <classes>
            <class name="com.juaracoding.ecom.InventoryTest"/>
        </classes>
    </test>
</suite>
```

**✅ Pros:**
- Stable and predictable execution flow
- Easier to debug failures
- Lower resource usage
- No data conflicts between tests
- Good for CI/CD pipelines
- Ideal for integration tests with dependencies

**❌ Cons:**
- Slower total execution time
- Underutilized CPU resources
- Slower feedback loop
- One slow test can block the rest

### ⚡ Parallel Test Execution
Tests can run **simultaneously** across multiple threads or browsers to reduce total test time.

**Example Configuration (`testng.xml`):**
```xml
<suite name="Swaglabs eCommerce Suite" parallel="methods" thread-count="2" verbose="1">
    <test name="ParallelTests">
        <classes>
            <class name="com.juaracoding.ecom.AuthenticationTest"/>
            <class name="com.juaracoding.ecom.InventoryTest"/>
        </classes>
    </test>
</suite>
```

**✅ Pros:**
- Faster overall execution
- Maximizes hardware utilization
- Provides quicker feedback
- Higher test throughput
- Easily scalable for larger test suites

**❌ Cons:**
- More complex to configure and maintain
- Higher CPU and memory usage
- Harder to debug flaky tests
- Risk of data conflicts if tests share state
- Limited by available browser instances
- May require more powerful test infrastructure

## 🚀 Key Features

**✔️ Page Object Model (POM)**  
- Clean separation between test logic and page interactions  
- Reusable page components for maintainability

**✔️ WebDriver Management**  
- Centralized driver setup via `DriverUtil`  
- Multi-browser support  
- Reliable driver lifecycle handling

**✔️ Test Data Management**  
- Data-driven testing with `DataTestProvider`  
- Externalized test data for flexibility  
- Parameterized tests supported

**✔️ Robust Element Handling**  
- Explicit waits to avoid flaky failures  
- Flexible locator strategies  
- Error handling for unstable elements

**✔️ Detailed Test Reporting**  
- Custom TestNG listeners  
- Rich execution logs  
- Clear test result reports

## 📊 Execution Results

Based on the results:

- **Sequential Mode:** *All test cases executed in **16 seconds***  
  ![Sequential Execution](image.png)

- **Parallel Mode:** *All test cases executed in **12 seconds***  
  ![Parallel Execution](image-2.png)


Based on simple research:

| Execution Mode | Number of Tests | Total Time (seconds) |
|----------------|------------------|----------------------|
| Sequential     | 10+ tests         | 16 seconds           |
| Parallel       | 10+ tests         | 12 seconds           |

**Insight:**  
Menjalankan beberapa tes secara berurutan akan mengantri tes satu per satu, sehingga waktu eksekusi total menjadi lebih lama (16 detik).
Menjalankan tes secara paralel memungkinkan tes berjalan secara bersamaan di thread atau browser yang berbeda, sehingga mengurangi waktu keseluruhan menjadi 12 detik dalam percobaan ini.
Eksekusi paralel lebih efisien untuk kumpulan tes yang besar, tetapi memerlukan isolasi tes yang tepat dan sumber daya komputasi yang lebih banyak.
Tim harus menentukan tingkat paralelisme berdasarkan kemandirian tes, infrastruktur, dan kecepatan umpan balik yang diinginkan.

## ✅ Best Use Recommendations

| Project Size | Recommended Mode | Notes |
|--------------|------------------|-------|
| Small (< 50 tests) | Sequential | Simple and stable |
| Medium (50–200 tests) | Parallel | Requires test isolation |
| Large (200+ tests) | Parallel (mandatory) | Needs robust infrastructure |

## ✔️ Best Practices Implemented

1. **Test Independence:** Each test is self-contained  
2. **Data Isolation:** No shared mutable data  
3. **Resource Cleanup:** Drivers closed after each test  
4. **Explicit Waits:** Replaces unreliable static sleeps  
5. **Graceful Error Handling:** Manages dynamic element states

## ⚙️ Maven Parallel Execution Example
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <configuration>
        <parallel>methods</parallel>
        <threadCount>3</threadCount>
        <suiteXmlFiles>
            <suiteXmlFile>testng-parallel.xml</suiteXmlFile>
        </suiteXmlFiles>
    </configuration>
</plugin>
```

### TestNG Parallel Options
```xml
parallel="methods"     <!-- Run test methods in parallel -->
parallel="classes"     <!-- Run test classes in parallel -->
parallel="tests"       <!-- Run test tags in parallel -->
parallel="instances"   <!-- Run test instances in parallel -->
```

## 🎯 Conclusion

This automation framework is designed to scale flexibly for both **sequential** and **parallel** execution modes, depending on project size and available infrastructure.

- ✅ **Use Sequential Mode** for maximum stability and easier debugging.
- ⚡ **Use Parallel Mode** for faster feedback and high-volume execution.

**Happy Testing! 🚀**
