# Tools
做2/8原则中的2,将2做到极致

## 项目背景
    "工欲善其事,必先利其器",在日常开发中,为了能够提高开发的效率,需要使用很多的工具,结合在项目开发过程中最常用的场景,
    提供一系列的工具类,供大家使用.本工具类开发的思想是,一个工具类,仅仅做一件事情,尽可能的将一件事做到极致,避免因为
    使用工具某个小功能点,而引用其他许多可能平时用不到的类.

## 项目模块

- 日志管理模块使用方法(logs):
  - 在引用模块的build.gradle中加入依赖
  ```
  implementation 'com.zero:logs:0.0.1'
  ```
  - 配置初始化模块
  ```java
     /**
     * 初始化日志工具类.
     *
     * @param isDebug         是否开启调试模式,true 开启,false 不开启,默认 true.
     * @param isShowThread    是否显示调用线程, true 展示,false 不展示,默认 false.
     * @param isShowMethod    是否显示调用方法, true 显示,false 不显示,默认 true.
     * @param isShowClassName 是否显示方法被调用处的类名,true 显示类名 com.zero.**, false 显示文件名 ** false
     */
   Logs.initLogs(true, true, true, true);
  ```
  - 在需要的地方添加日志打印
  ```java
   Logs.v("日");
   Logs.d("志");
   Logs.i("打");
   Logs.w("印");
   Logs.e("测");
   ```
   - 开启结束事务(调试执行性能)
   ```java
   Logs.startTransaction();//开启
   // 日志在执行的耗时...
   Logs.endTransaction();//结束
   ```
   - 还原默认设置
   ```java
   Logs.restoreDefault();
   ```
## 联系方式
Email:leeyewkuan@gmail.com
   
