برای اجرای پروژه با Maven از دستور زیر استفاده کنید:

mvn compile exec:java -Dexec.mainClass="com.example.energymanager.Main"

سیستم مدیریت پویای مصرف انرژی هوشمند
این پروژه یک شبیه‌ساز برای مدیریت مصرف انرژی در یک ساختمان هوشمند است که با استفاده از زبان جاوا و الگوهای طراحی Strategy و State پیاده‌سازی شده است. توسعه این پروژه بر اساس رویکرد توسعه آزمون‌محور (TDD) انجام گرفته است.

ویژگی‌ها
مدیریت وضعیت سیستم: سیستم می‌تواند در سه حالت Active, Eco Mode و Shutdown قرار گیرد و رفتار آن بر اساس هر وضعیت تغییر می‌کند.

سیاست‌های قیمت‌گذاری پویا: هزینه مصرف انرژی بر اساس سیاست‌های مختلف (Standard, Peak Hours, Green Mode) محاسبه می‌شود و این سیاست‌ها در زمان اجرا قابل تغییر هستند.

رابط کاربری مبتنی بر منو: کاربران و مدیران می‌توانند از طریق یک منوی ساده در ترمینال با سیستم تعامل داشته باشند.

الگوهای طراحی استفاده شده
در این پروژه از دو الگوی طراحی اصلی برای مدیریت پیچیدگی و افزایش انعطاف‌پذیری کد استفاده شده است:

۱. الگوی استراتژی (Strategy Pattern)
هدف: این الگو برای مدیریت روش‌های مختلف محاسبه هزینه انرژی به کار رفته است. با استفاده از این الگو، می‌توان الگوریتم محاسبه هزینه را در زمان اجرا و بدون تغییر در کلاس اصلی (EnergyManager) تغییر داد. این کار باعث جداسازی الگوریتم‌ها از کلاسی می‌شود که از آن‌ها استفاده می‌کند.

نحوه پیاده‌سازی:

PricingStrategy: یک اینترفیس است که متد calculateCost(double units) را تعریف می‌کند. این اینترفیس نقش قرارداد اصلی برای تمام الگوریتم‌های قیمت‌گذاری را ایفا می‌کند.

StandardPricingStrategy, PeakHoursPricingStrategy, GreenModePricingStrategy: این سه کلاس، اینترفیس PricingStrategy را پیاده‌سازی کرده و هر کدام الگوریتم محاسبه هزینه خاص خود را دارند.

EnergyManager: این کلاس (Context) یک ارجاع به یک شیء از نوع PricingStrategy دارد. هنگامی که نیاز به محاسبه هزینه است، EnergyManager این وظیفه را به استراتژی فعلی خود محول می‌کند. متد setPricingStrategy به مدیر اجازه می‌دهد تا این استراتژی را در هر لحظه تغییر دهد.

// نمونه استفاده در کلاس EnergyManager
public class EnergyManager {
    private PricingStrategy currentPricingStrategy;

    // این متد به مدیر اجازه می‌دهد استراتژی را در زمان اجرا تغییر دهد
    public void setPricingStrategy(PricingStrategy strategy) {
        this.currentPricingStrategy = strategy;
    }

    // مسئولیت محاسبه به شیء استراتژی واگذار می‌شود
    public double simulateCost(double units) {
        return currentPricingStrategy.calculateCost(units);
    }
}

۲. الگوی وضعیت (State Pattern)
هدف: این الگو برای مدیریت رفتارهای مختلف سیستم بر اساس وضعیت داخلی آن (Active, Eco, Shutdown) استفاده شده است. این الگو به ما کمک می‌کند تا از دستورات شرطی (if/else یا switch) پیچیده در کلاس EnergyManager جلوگیری کنیم و هر وضعیت، منطق و قوانین مربوط به خود را کپسوله کند.

نحوه پیاده‌سازی:

SystemState: یک اینترفیس است که متدهای مربوط به تمام اقدامات ممکن (مانند activate, shutdown, setToEcoMode) را تعریف می‌کند.

ActiveState, EcoModeState, ShutdownState: این کلاس‌ها، اینترفیس SystemState را پیاده‌سازی می‌کنند. هر کلاس مسئولیت مدیریت رفتارها و انتقال‌ها از آن وضعیت خاص را بر عهده دارد. برای مثال، کلاس ActiveState می‌داند که از حالت فعال می‌توان به حالت Eco یا Shutdown رفت، اما نمی‌توان دوباره به حالت Active رفت.

EnergyManager: این کلاس (Context) یک ارجاع به وضعیت فعلی خود (currentState) دارد. هر درخواستی برای تغییر وضعیت (مثلاً فراخوانی متد manager.shutdown()) به شیء وضعیت فعلی ارسال می‌شود. سپس آن شیء تصمیم می‌گیرد که چه کاری انجام دهد و در صورت لزوم، وضعیت EnergyManager را به یک شیء وضعیت جدید تغییر می‌دهد.

// نمونه استفاده در کلاس EnergyManager
public class EnergyManager {
    private SystemState currentState;

    // این متد توسط اشیاء وضعیت برای تغییر وضعیت فعلی فراخوانی می‌شود
    public void setCurrentState(SystemState state) {
        this.currentState = state;
    }

    // درخواست به شیء وضعیت فعلی واگذار می‌شود
    public void shutdown() {
        currentState.shutdown(this);
    }

    public void activate() {
        currentState.activate(this);
    }
}

نحوه اجرا
۱. اطمینان حاصل کنید که JDK (نسخه ۱۱ یا بالاتر) و Maven روی سیستم شما نصب شده باشد.
۲. پروژه را در یک IDE مانند VS Code یا IntelliJ IDEA باز کنید.
۳. IDE به صورت خودکار وابستگی‌ها (JUnit) را از طریق فایل pom.xml دانلود می‌کند.
۴. برای اجرای برنامه اصلی، فایل Main.java را اجرا کنید.
۵. برای اجرای تست‌ها، می‌توانید از طریق IDE تمام تست‌های موجود در پوشه src/test/java را اجرا کنید یا از دستور mvn test در ترمینال استفاده نمایید.