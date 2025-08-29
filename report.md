# Report of Refactoring - (Phase 2)

## 1. Apply Facade Pattern
- ایجاد کلاس `CompilerFacade` برای ساده‌سازی دسترسی به مراحل مختلف کامپایل. 
- تمام فراخوانی‌ها به کلاس‌های مختلف CodeGenerator، Parser و LexicalAnalyzer از طریق این facade انجام شد.

## 2. Apply Polymorphism / Strategy in Parser
- جایگزینی switch-case برای Action با استفاده از interface `ActionHandler` و کلاس‌های `ShiftActionHandler`, `ReduceActionHandler`, `AcceptActionHandler`. 
- این کار باعث شد عملیات shift/reduce/accept به صورت پلی‌مورفیک مدیریت شود.

## 3. Separate Query From Modifier
- در `CodeGenerator`, متد `resolvePid` از متد `pid` جدا شد تا Query و Modifier از هم تفکیک شوند. 
- باعث شد منطق بررسی و ایجاد آدرس متغیرها و ثبت آن‌ها در stack مستقل شود.

## 4. Extract Method and Rename Variable
- در چند متد طولانی، بخش‌هایی از کد جدا و متدهای کوچکتر ساخته شد (مثل `fromString` در `Action.java`).  
- برخی متغیرها با اسم‌های گویا‌تر جایگزین شدند تا خوانایی کد افزایش یابد.

## 5. Replace magic numbers/strings with constants in Action
- مقادیر ثابت (مانند `"acc"`, `'r'`, `'s'`) در کلاس `Action.java` با متغیرهای ثابت جایگزین شدند.  
- هدف: کاهش احتمال خطا و افزایش خوانایی و نگهداری کد.

## 6. Add resolvePid method in CodeGenerator and Action.fromString; minimal safe refactor
- متد `resolvePid` اضافه شد تا منطق پیدا کردن آدرس متغیرها در CodeGenerator تفکیک شود.  
- همچنین `Action.fromString` به عنوان متد کمکی برای تبدیل رشته به Action اضافه شد.


##  Questions:

### 1. Three Categories of GoF Design Patterns
- **Creational Patterns:** الگوهایی که ایجاد اشیاء را ساده یا منعطف می‌کنند. مثال: `Factory Method`, `Singleton`.  
- **Structural Patterns:** الگوهایی که کلاس‌ها یا اشیاء را به هم متصل می‌کنند تا ساختار کلی منعطف شود. مثال: `Facade`, `Decorator`.  
- **Behavioral Patterns:** الگوهایی که الگوریتم‌ها و رفتارها را بین اشیاء مدیریت می‌کنند. مثال: `Strategy`, `Observer`.


### 2. Category of Patterns Used in Phase 1


### 3. Suitable Design Pattern for Smart Energy Management System


### 4. SOLID Principles in Factory Pattern
- **S** (Single Responsibility): Factory مسئول ایجاد شیء است و از منطق اضافی جداست → تحقق یافته.  
- **O** (Open/Closed): می‌توان Factory را بدون تغییر کلاس موجود برای تولید انواع جدید گسترش داد → تحقق یافته.  
- **L** (Liskov Substitution): زیرکلاس‌های محصول می‌توانند جایگزین محصول پایه شوند → تحقق یافته.  
- **I** (Interface Segregation): Factory اغلب از یک interface ساده استفاده می‌کند و مشتریان وابسته به متدهای اضافی نیستند → تحقق یافته.  
- **D** (Dependency Inversion): Factory وابسته به abstraction است نه پیاده‌سازی‌های خاص → تحقق یافته.


### 5. Short Concepts
- **کد تمیز:** کدی که خوانا، قابل نگهداری و قابل توسعه باشد.  
- **بدهی فنی:** تصمیمات طراحی یا پیاده‌سازی کوتاه‌مدت که در آینده نیاز به اصلاح دارند.  
- **بوی بد:** نشانه‌های کد مشکل‌دار یا طراحی ضعیف که نیاز به ریفکتور دارد.



### 6. Five Categories of Code Smells (refactoring.guru)
1. **Bloaters (بلند و حجیم):** کلاس‌ها یا متدهای بزرگ که پیچیدگی را افزایش می‌دهند.  
2. **Object-Orientation Abusers (سوءاستفاده از شیءگرایی):** الگوهای ضعیف شیءگرایی، مثل استفاده از static زیاد یا inheritance نادرست.  
3. **Change Preventers (مانع تغییر):** طراحی‌هایی که اعمال تغییر را سخت یا پرهزینه می‌کنند.  
4. **Dispensables (قابل حذف):** کدهای اضافی، تکراری یا بلااستفاده.  
5. **Couplers (وابستگی بیش از حد):** کلاس‌ها یا ماژول‌هایی که بیش از حد به هم وابسته‌اند.

### 7. Feature Envy
- دسته: **Object-Orientation Abusers**  
- بازآرایی پیشنهادی: **Move Method**, **Extract Method**, **Inline Method**  
- مواقع نادیده گرفتن: زمانی که دسترسی مستقیم به داده‌های کلاس دیگر ساده‌تر یا منطقی‌تر از انتقال متد باشد و refactor پیچیدگی غیرضروری ایجاد کند.

### 8. Example Code Smells in the Project 


#### 1. Long Method
**Where:** `Main.java` – `loopOnGUI()` (~50+ lines). 

**Why:** Multiple responsibilities: logging, status checking, file writing, code generation, XML output.  

**Fix:** Extract methods:
- `logDiagramStatus()`
- `handleCodeGeneration()`
- `writeDiagramXmlIfOk()`
  
  
---

#### 2. Large Class
**Where:** `Main.java` (~200+ lines). 

**Why:** Mixed responsibilities — violates SRP.  

**Fix:** Extract CLI handler, GUI runner, and XML code into their own classes:  
- `CommandLineProcessor`
- `GuiLoopController`

---

#### 3. Duplicated Code
**Where:** Printing diagram status in `generateInfoForXML()` and `loopOnGUI()`.  

**Why:** Nearly identical status/result printouts.  

**Fix:** Create helper method `printDiagramStatus(DiagramLike diagram)`.  


---

#### 4. Primitive Obsession
**Where:** Passing raw `String` paths in `Main.main`.  

**Why:** Magic strings for directories/flags; no type safety.  

**Fix:** Create `ConfigPaths` object with typed fields.  


---

#### 5. Magic Numbers
**Where:** `loopOnGUI()` uses `counter % 15` and `counter % 4`.  

**Why:** No symbolic meaning.  

**Fix:** Use constants like `STATUS_LOG_INTERVAL` and `GENERATION_INTERVAL`.  


---

#### 6. Shotgun Surgery
**Where:** `setDataByNode()` in `ClassStructure` and `ClassDiagram`.  

**Why:** XML structure change requires edits in multiple locations.  

**Fix:** Extract XML parsing into utility class `XmlNodeMapper`.  


---

#### 7. Feature Envy
**Where:** `ClassDiagram.getAllProblems()` manipulates `TClass` internals.  

**Why:** Responsibility mismatch.  

**Fix:** Push checks for attribute/method/class name conflicts into `TClass`.  


---

#### 8. Temporary Field
**Where:** `Main` static fields `documentFactory`, `documentBuilder`, `transformer`.  

**Why:** Only needed during GUI initialization.  

**Fix:** Encapsulate into `GuiContext` object used only in GUI mode.  


---

#### 9. Multithreaded Risk Without Synchronization
**Where:** `CommandExecutor.logOutput()`.  

**Why:** Threads logging to `System.out` risk interleaved output.  

**Fix:** Keep `log()` synchronized (already is) and ensure ordered output via thread joining.  


---

#### 10. Data Clumps
**Where:** `diagramInfoDirectory`, `phase1Directory`, `otherCFiles`, `headers` always passed together.  

**Why:** Indicates need for grouping.  

**Fix:** Wrap into `Phase2Paths` DTO.  


---


### 9. Formatter Plugin
- **کارکرد:** کد منبع را بر اساس قوانین از پیش تعریف شده مرتب و فرمت می‌کند (Indentation, Spacing, Line Wrapping).  
- **کمک‌کننده:** خوانایی کد را افزایش می‌دهد و بوی بد ناشی از ساختار ناهماهنگ یا نامرتب کد را کاهش می‌دهد.  
- **رابطه با Refactoring:** اجرای بازآرایی‌ها مؤثرتر و ایمن‌تر می‌شود زیرا کد مرتب و یکدست است و بررسی تغییرات راحت‌تر انجام می‌شود.

